package br.edu.ifpb.aleciano.interfaces;

/*
 * Interface para um Protocolo de Interação
 */
public interface InteraProtocol {

	String getName();
	String getVersion();
	
	boolean makeProposal(Object data);
	void rejectInteration();
	void rejectProposal();
	boolean acceptProposal();
	
	
	
	
	

}
