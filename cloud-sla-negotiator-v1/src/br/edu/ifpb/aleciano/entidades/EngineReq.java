package br.edu.ifpb.aleciano.entidades;

import java.util.ArrayList;
import java.util.Collection;

import br.edu.ifpb.aleciano.interfaces.*;
public class EngineReq {
	
	ArrayList<InteraProtocol> protocols;
	ArrayList< Strategy> strategies;

	String login;
	char[] wkey;
	
	public EngineReq(ArrayList<InteraProtocol> protocol,
			ArrayList<Strategy> strategies, String login, char[] wkey) {
		super();
		this.protocols = protocol;
		this.strategies = strategies;
		this.login = login ;
		this.wkey = wkey ;
	}

	public EngineReq(InteraProtocol protocol, Strategy strategy) {
		super();
		this.protocols = new ArrayList<InteraProtocol>();
		this.strategies = new ArrayList<Strategy>();
		this.strategies.add(strategy);
		this.login = "guest" ;
		fillKey();
	}
	
	public EngineReq(Strategy strategy) {
		super();
		this.strategies = new ArrayList<Strategy>();
		//this.protocolo = protocolo;
		this.strategies.add(strategy);
		this.login = "guest" ;
		fillKey();
	}
	
	public EngineReq() {
		super();
		
		this.strategies = new ArrayList<Strategy>();
		this.protocols = new ArrayList<InteraProtocol>();
		this.login = "guest" ;
		fillKey();
		
	}
	
	public ArrayList< Strategy> getStrategies() {
		return this.strategies;
	}

	public void setStrategies(Collection< Strategy> strategies) {
		this.strategies = (ArrayList<Strategy>) strategies;
	}
	
	public void setStrategy( Strategy strategy){
		this.strategies.add(strategy);
	}
	/*
	 * Seta estratégia.
	 * Recebe como argumento alguma classe que estenda 'Strategy'
	 */
	public void setStrategy(Context context, Class<? extends Strategy> cls) throws InstantiationException, IllegalAccessException{
		
		Strategy strat = cls.newInstance(); // cria instância da tal classe passada (o "nome" foi passado)
		strat.setContext(context); // seta contexto após a classe já criada (necessita de construtor vazio)
		this.strategies.add(strat);
	}
	
	public ArrayList<InteraProtocol> getProtocol() {
		return protocols;
	}
	
	public String getStrategyName(int index){
		return this.strategies.get(index).getName();
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public char[] getKey(){
		return this.wkey;
	}
	
	public void setKey(char[] key){
		this.wkey = key;
	}
	public void fillKey(){
		char[] tempKey = new char[128];
		for(int i=0;i<128;i++){
			tempKey[i] = (char) 0;
		}
		wkey = tempKey;
	}

	/*
	 * Verifica se a estratégia já está na EngineReq.
	 */
	public boolean strategyExists(Class<? extends Strategy> cls){
		if(this.strategies!=null)
		for(Strategy str : this.strategies){
			if(str.getClass() == cls)
				return true;
		}
		
		return false;
	}
	/*
	 * Inicia o processo de requisição para obter instâncias utilizando estratégia
	 * informado pelo índice indexOfStrategy.
	 */
	public boolean startRequest(int indexOfStrategy){
		// TODO "logar" antes de executar estratégias
		// Após "logar", passar objetos de interação com o provedor para o "play" da estratégia.
		
		boolean result = this.strategies.get(indexOfStrategy).play();
		
		
		return result;
	}
	
}
