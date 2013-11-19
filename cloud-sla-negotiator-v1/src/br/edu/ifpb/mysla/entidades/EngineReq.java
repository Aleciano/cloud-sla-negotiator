package br.edu.ifpb.mysla.entidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

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

	public EngineReq(InteraProtocol protocol, Strategy strategy) {
		super();
		this.protocols = new ArrayList<InteraProtocol>();
		this.strategies = new ArrayList<Strategy>();
		this.strategies.add(strategy);
		this.protocols.add(protocol);
		this.strategy = strategy;
		this.intera = protocol;
		this.login = "guest";
		fillKey();
	}

	public EngineReq(Strategy strategy) {
		super();
		this.strategies = new ArrayList<Strategy>();
		// this.protocolo = protocolo;
		this.strategies.add(strategy);
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

	public ArrayList<Strategy> getStrategies() {
		return this.strategies;
	}

	public void setStrategies(Collection<Strategy> strategies) {
		this.strategies = (ArrayList<Strategy>) strategies;
	}

	public void setStrategy(Strategy strategy) {
		this.strategies.add(strategy);
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
		this.strategies.add(strat);
	}

	public void setProtocol(Class<? extends InteraProtocol> cls)
			throws InstantiationException, IllegalAccessException {

		InteraProtocol proto = cls.newInstance(); // cria instância da tal
													// classe informada (o
													// "nome" foi passado)

		this.protocols.add(proto);
	}

	public ArrayList<InteraProtocol> getProtocol() {
		return protocols;
	}

	public void setProtocol(Collection<InteraProtocol> protocols) {
		this.protocols = (ArrayList<InteraProtocol>) protocols;
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

	public void setKey(char[] key) {
		this.wkey = key;
	}

	public void fillKey() {
		char[] tempKey = new char[128];
		for (int i = 0; i < 128; i++) {
			tempKey[i] = (char) 0;
		}
		wkey = tempKey;
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
	public boolean startRequest(int indexOfStrategy) {
		// TODO "logar" antes de executar estratégias
		// Após "logar", passar objetos de interação com o provedor para o
		// "play" da estratégia.

		int cont = new Random().nextInt(10);
		ArrayList<String> argv = new ArrayList<String>();
		argv.add(Integer.toString(cont));
		argv.add(this.getLogin());
		argv.add(this.getKey().toString());

		writeLog(
				"/home/junior/git/cloud-sla-negotiator/cloud-sla-negotiator-v1/src/logs/",
				indexOfStrategy, argv);
		boolean result = this.strategies.get(indexOfStrategy).play();

		return result;
	}

	public boolean writeLog(String destination, int indexOfStrategy,
			ArrayList<String> argv) {
		FileWriter fw;
		try {
			if (argv.size() > 0) {
				File file = new File(destination + argv.get(0) + ".txt");
				file.delete(); // deleta caso o arquivo exista.
				fw = new FileWriter(file, false);
			} else
				fw = new FileWriter(new File(destination + "semnome" + ".txt"),
						true);
		} catch (IOException e) {
			return false;
		}
		PrintWriter arq = new PrintWriter(fw);
		arq.write("".toCharArray());
		arq.format(
				"Strategy: %s\nProtocol: %s\nInstance type: %s\nNum. of instances: %s\nGeo Zone: %s\nLogin: %s\nWkey: %s",
				this.strategies.get(indexOfStrategy).getName(),
				this.protocols.get(0).getName(),
				((AmazonContext) this.strategies.get(indexOfStrategy)
						.getContext()).getInstanceType(),
				((AmazonContext) this.strategies.get(indexOfStrategy)
						.getContext()).getInstancesNum(),
				((AmazonContext) this.strategies.get(indexOfStrategy)
						.getContext()).getGeoZone(), argv.get(1), argv.get(2));
		arq.close();
		try {
			fw.close();
		} catch (IOException e) {
			return false;
		}
		return true;

	}

}
