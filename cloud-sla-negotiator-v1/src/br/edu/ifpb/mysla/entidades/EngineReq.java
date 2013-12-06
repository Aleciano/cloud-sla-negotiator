package br.edu.ifpb.mysla.entidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import br.edu.ifpb.mysla.interfaces.Context;
import br.edu.ifpb.mysla.interfaces.InteraProtocol;
import br.edu.ifpb.mysla.interfaces.Strategy;

public class EngineReq {

	private ArrayList<InteraProtocol> protocols;
	private ArrayList<Strategy> strategies;
	private Strategy strategy;
	private InteraProtocol intera;

	String login;
	char[] wkey;

	public EngineReq(ArrayList<InteraProtocol> protocol,
			ArrayList<Strategy> strategies, String login, char[] wkey) {
		super();
		this.protocols = protocol;
		this.strategies = strategies;
		this.login = login;
		this.wkey = wkey;
	}

	public EngineReq(InteraProtocol protocol, Strategy strategy, String login,
			char[] wkey) {
		super();
		this.protocols = new ArrayList<InteraProtocol>();
		this.strategies = new ArrayList<Strategy>();
		this.strategies.add(strategy);
		this.protocols.add(protocol);
		this.strategy = strategy;
		this.intera = protocol;
		this.login = login;
		this.wkey = wkey;
	}

	public EngineReq(Strategy strategy) {
		super();
		this.strategies = new ArrayList<Strategy>();

		this.strategies.add(strategy);
		this.login = "guest";
		fillKey();
	}

	public EngineReq(InteraProtocol protocol) {
		super();
		this.protocols = new ArrayList<InteraProtocol>();

		this.protocols.add(protocol);
		this.login = "guest";
		fillKey();
	}

	public EngineReq() {
		super();

		this.strategies = new ArrayList<Strategy>();
		this.protocols = new ArrayList<InteraProtocol>();
		this.login = "guest";
		fillKey();

	}

	public void setProtocols(Collection<InteraProtocol> protocols) {
		this.protocols = (ArrayList<InteraProtocol>) protocols;
	}

	public void setStrategies(Collection<Strategy> strategies) {
		this.strategies = (ArrayList<Strategy>) strategies;
	}

	public void setProtocol(InteraProtocol protocol) {
		this.protocols.add(protocol);
	}

	public void setStrategy(Strategy strategy) {
		this.strategies.add(strategy);
	}

	public void setProtocol(Class<? extends InteraProtocol> cls)
			throws InstantiationException, IllegalAccessException {

		InteraProtocol proto = cls.newInstance(); // cria instância da tal
													// classe informada (o
													// "nome" foi passado)

		this.setProtocol(proto);
	}

	/*
	 * Seta estratégia. Recebe como argumento alguma classe que estenda
	 * 'Strategy'
	 */
	public void setStrategy(Context context, Class<? extends Strategy> cls)
			throws InstantiationException, IllegalAccessException {

		Strategy strat = cls.newInstance(); // cria instância da tal classe
											// informada (o "nome" foi passado)
		strat.setContext(context); // seta contexto após a classe já criada
									// (necessita de construtor vazio)
		this.setStrategy(strat);
	}

	public ArrayList<InteraProtocol> getProtocols() {
		return protocols;
	}

	public ArrayList<Strategy> getStrategies() {
		return this.strategies;
	}

	public String getProtocolName(int index) {
		return this.protocols.get(index).getName();
	}

	public String getStrategyName(int index) {
		return this.strategies.get(index).getName();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public char[] getKey() {
		return this.wkey;
	}

	public boolean setKey(char[] key) {
		if (key.length > 0) {
			this.wkey = key;
			return true;
		}
		return false;
	}

	/*
	 * Insere qualquer dado na senha
	 */
	public void fillKey() {
		char[] tempKey = new char[128];
		Random r = new Random();
		for (int i = 0; i < 128; i++) {
			tempKey[i] = (char) r.nextInt(128 - i);
			// tempKey[i] = 0;
		}
		tempKey[127] = '\0';
		this.wkey = tempKey;

		/*
		 * for (int i = 0; i < 128; i++) {
		 * System.out.println(Character.toString(tempKey[i])+ " " + i); }
		 */
	}

	/*
	 * Verifica se a estratégia já está na EngineReq.
	 */
	public boolean strategyExists(Class<? extends Strategy> cls) {
		if (this.strategies != null)
			for (Strategy str : this.strategies) {
				if (str.getClass() == cls)
					return true;
			}

		return false;
	}

	/*
	 * Inicia o processo de requisição para obter instâncias utilizando
	 * estratégia informado pelo índice indexOfStrategy.
	 */
	public boolean startRequest(int indexOfStrategy, int indexOfProtocol)
			throws IOException {
		// TODO "logar" antes de executar estratégias
		// Após "logar", passar objetos de interação com o provedor para o
		// "play" da estratégia.

		if ((protocols == null || protocols.size() < 1)
				|| (strategies == null || strategies.size() < 1))
			return false;
		boolean result = false;
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();
		String cont = df.format(today);
		cont = cont.replace(":", "");
		cont = cont.replace("/", "");
		cont = cont.replace(" ", "_");
		// int cont = new Random().nextInt(10);
		ArrayList<String> argv = new ArrayList<String>();
		argv.add(cont);
		argv.add(this.getLogin());
		argv.add(this.getKey().toString());

		/*
		 * Se não conseguir escrever no log, então não é possível iniciar.
		 */
		String save = System.getProperty("user.home");
		File f = new File(save+"/logs-cloud_sla_negotiator");
		
		f.mkdirs();
		if (writeLog(save + "/logs-cloud_sla_negotiator/",
				indexOfStrategy, indexOfProtocol, argv)) {
			result = this.strategies.get(indexOfStrategy).play();
		}
		// TODO quando implementar as estratégias de fato, o ' return true '
		// deve ser alterado para ' return result'
		return true;
	}

	public boolean writeLog(String destination, int indexOfStrategy,
			int indexOfProtocol, ArrayList<String> argv) throws IOException {
		FileWriter fw;
		try {
			if (argv.size() > 0) {
				File file = new File(destination + argv.get(0) + ".txt");
				file.delete(); // deleta caso o arquivo exista.
				fw = new FileWriter(file, false);
			} else {
				return false;
			}
		} catch (IOException e) {
			throw e;
		}
		PrintWriter arq = new PrintWriter(fw);
		arq.write("".toCharArray());

		arq.format(
				"### Output ###\nStrategy: %s\nProtocol: %s\n### Input ###\n%s\nLogin: %s\nWkey: %s",
				this.strategies.get(indexOfStrategy).getName(), this.protocols
						.get(indexOfProtocol).getName(),
				this.strategies.get(indexOfStrategy).getContext().toString(),
				argv.get(1), argv.get(2));

		arq.close();
		try {
			fw.close();
		} catch (IOException e) {
			throw e;
		}
		return true;

	}

}
