package br.edu.ifpb.aleciano.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		out.println("<!DOCTYPE HTML><html lang=\"pt-br\"><head><meta charset=\"ISO-8859-1\"><title>Novo registro: resultado</title></head><body>");

		out.println("<b>Resultados:</b><br><br>");

		Context context = createContextByArgs(request);
		try {
			// load up the knowledge base
			KnowledgeBase kbase = readKnowledgeBase();
			
			StatefulKnowledgeSession ksession = kbase
					.newStatefulKnowledgeSession();
			
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory
					.newFileLogger(ksession, "test");
			// go !
			EngineReq engine = new EngineReq();

			ksession.insert(context);
			ksession.insert(engine);
			ksession.fireAllRules(); // permanece executando enquanto as regras
										// estão trabalhando..
			// System.out.println("Após o fire das regras..");
			logger.close();

			if (engine.getStrategies().size() > 0)
				for (Strategy str : engine.getStrategies()) {
					out.println("\nEstratégia " + str.getName()
							+ " é aplicável.");
				}
			else
				out
						.println("\nNão houve estratégias ativadas com estas opções.");
			engine.setLogin(request.getParameter("login"));
			engine.setKey(request.getParameter("key").toCharArray());
			if (engine.startRequest(0))
				out.println("\nSucesso! "
						+ engine.getStrategies().get(0).getName()
						+ " adquiriu uma instância com sucesso.");
			else
				out.println("Não foi possível "
						+ engine.getStrategies().get(0).getName()
						+ " adquirir uma instância.");
		} catch (Throwable t) {
			t.printStackTrace();
		}

		/*
		 * out.println(context.toString());
		 * //System.out.println(context.toString());
		 * 
		 * out.println(request.getParameter("login"));
		 * out.println(request.getParameter("key"));
		 */
		out.println("</body></html>");

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

	private /*static*/ KnowledgeBase readKnowledgeBase() throws Exception {
		//System.out.println("Chega aqui");
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		
		kbuilder.add(ResourceFactory
				.newFileResource(cx.getRealPath("/") + /*"WEB-INF/" +*/"regras.drl"),
				ResourceType.DRL);
		/*kbuilder.add(ResourceFactory
				.newClassPathResource(cx.getRealPath("/") + "WEB-INF/" +"regras.drl"),
				ResourceType.DRL);*/
		//"estrategias-regras-v1.drl"
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
