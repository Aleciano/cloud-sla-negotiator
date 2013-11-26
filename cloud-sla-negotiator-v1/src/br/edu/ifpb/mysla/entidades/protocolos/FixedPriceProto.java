package br.edu.ifpb.mysla.entidades.protocolos;

import br.edu.ifpb.mysla.interfaces.InteraProtocol;

public class FixedPriceProto implements InteraProtocol {
	String name;
	String version;
	Object data;
	
	public FixedPriceProto() {
		super();
		this.name = "fixedpriceprotocol";
		this.version = "0.1" ; 
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return this.version;
	}

	@Override
	public boolean makeProposal(Object data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void rejectInteration() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectProposal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean acceptProposal() {
		// TODO Auto-generated method stub
		return false;
	}

}
