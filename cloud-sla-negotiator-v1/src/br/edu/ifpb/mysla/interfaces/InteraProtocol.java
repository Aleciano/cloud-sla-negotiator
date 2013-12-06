package br.edu.ifpb.mysla.interfaces;

/*
 * Interface para um Protocolo de Interação
 */
public interface InteraProtocol {

	String getName();
	String getVersion();
	Object getData();
	void setData(Object data);
	
	boolean makeProposal();
	void rejectInteration();
	void rejectProposal();
	boolean acceptProposal();
	
}
