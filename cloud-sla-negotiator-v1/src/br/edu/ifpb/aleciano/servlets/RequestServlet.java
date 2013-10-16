package br.edu.ifpb.aleciano.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.event.*;

import br.edu.ifpb.aleciano.entidades.AmazonContext;
import br.edu.ifpb.aleciano.entidades.EngineReq;
import br.edu.ifpb.aleciano.interfaces.Context;
import br.edu.ifpb.aleciano.interfaces.Strategy;

import com.google.gson.Gson;

/**
 * Servlet implementation class RequestServlet
 */
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext cx;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestServlet() {
		super();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		this.cx = request.getSession().getServletContext();
		String flag = request.getParameter("flag");
		switch (flag) {
		case "login": {
			HttpSession session = request.getSession();
			
			EngineReq engine = new EngineReq();
			session.setAttribute("engine", engine);

			Context context = createContextByArgs(request);
			if (context==null){
				RequestDispatcher r = request
						.getRequestDispatcher("WEB-INF/results.jsp");
				request.setCharacterEncoding("UTF-8");
				
				request.setAttribute("strategy", null);
				r.forward(request, response);
			}
			else{
			try {
				// load up the knowledge base
				KnowledgeBase kbase = readKnowledgeBase();

				StatefulKnowledgeSession ksession = kbase
						.newStatefulKnowledgeSession();

				KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory
						.newFileLogger(ksession, "test");
				// go !

				ksession.insert(context);
				ksession.insert(engine);
				ksession.fireAllRules(); // permanece executando enquanto as
											// regras
											// estão trabalhando..
				// System.out.println("Após o fire das regras..");
				logger.close();

				if (engine.getStrategies().size() > 0) {
					// for (Strategy str : engine.getStrategies()) {
					// TODO add array de estratégias e passa pro JSP de
					// seleção

					/*
					 * out.println("\nEstratégia " + str.getName() +
					 * " é aplicável.");
					 */
					// }

					request.setAttribute("conflict", engine.getStrategies()
							.size() > 1 ? "yes" : "no");
					engine.setLogin(request.getParameter("login"));
					engine.setKey(request.getParameter("key").toCharArray());
					request.setAttribute("strategies", engine.getStrategies());
					session.setAttribute("engine", engine);
					// request.setAttribute("code", 1);
					RequestDispatcher r = request
							.getRequestDispatcher("WEB-INF/strategy_selection.jsp");
					request.setCharacterEncoding("UTF-8");
					r.forward(request, response);

				} else {
					session.setAttribute("strategies", null);
					request.setAttribute("strategy", null);
					RequestDispatcher r = request
							.getRequestDispatcher("WEB-INF/results.jsp");
					request.setCharacterEncoding("UTF-8");
					r.forward(request, response);

				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
			}
			break;
		}

		/*
		 * Recebe 'yes' ou 'no'. Caso 'yes', lê o atributo da estratégia
		 * ativada. Pode ser o index do Array ou o nome da estratégia.
		 */
		// TODO
		case "selection": {
			HttpSession session = request.getSession();
			EngineReq engine = (EngineReq) session.getAttribute("engine");
			int index = Integer
					.parseInt(request.getParameter("strategy_index"));
			if (engine != null && engine.startRequest(index)) {
				// TODO manda pro JSP de resultados a estratégia escolhida e
				// ativada.
				session.setAttribute("strategy",
						engine.getStrategies().get(index));
				request.setAttribute("strategy",
						engine.getStrategies().get(index));
				
				RequestDispatcher r = request
						.getRequestDispatcher("WEB-INF/results.jsp");
				request.setCharacterEncoding("UTF-8");
				r.forward(request, response);
			} else {
				// TODO manda pro JSP de resultados falando que não houve
				// sucesso ao utilizar a estratégia.
				/*
				 * out.println("Não foi possível " +
				 * engine.getStrategies().get(0).getName() +
				 * " adquirir uma instância.");
				 */
				engine.toString(); // nada
			}
		}
			break;
		}
		// out.println("</body></html>");

	}

	/*
	 * Cria objeto Contexto de acordo com os parâmetros recebidos pela interface
	 * web.
	 */
	private Context createContextByArgs(HttpServletRequest request) {

		String contextValues[] = request.getParameterValues("context"); // Recebe
																		// parâmetros
																		// "marcáveis"
																		// via
																		// caixa
																		// de
																		// marcação
																		// (checkbox)
																		// html.
		if (contextValues==null) return null;
		String json = "{"; // Inicia String do Json

		/*
		 * Varre os parâmetros que foram marcados na página html e atribui a
		 * string 'true'. Posteriormente, o Json irá atribuir o valor booleano
		 * true ao atributo do objeto.
		 */
		for (String value : contextValues) {
			json += " '" + value + "' : 'true',";
		}

		/*
		 * Atribui o restante dos parâmetros.
		 */
		json += " 'instancesNum' : '"
				+ Integer.parseInt(request.getParameter("numinstancias"))
				+ "'," + " 'instanceType' : '"
				+ request.getParameter("instancias") + "' ," + " 'geoZone' : '"
				+ Integer.parseInt(request.getParameter("locgeografica")) + "'"
				+ "}";

		/*
		 * Parte que faz o parser convertendo a String formatada para o objeto
		 * da classe informada.
		 */
		AmazonContext context = new Gson().fromJson(json, AmazonContext.class);

		return context;
	}

	private/* static */KnowledgeBase readKnowledgeBase() throws Exception {

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();

		/* Ler de um arquivo */
		kbuilder.add(
				ResourceFactory.newFileResource(cx.getRealPath("/")
						+ "WEB-INF/" + "regras.drl"), ResourceType.DRL);

		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}
}
