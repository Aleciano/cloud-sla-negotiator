package br.edu.ifpb.mysla.entidades.estrategias;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import br.edu.ifpb.mysla.entidades.AmazonContext;
import br.edu.ifpb.mysla.interfaces.Context;
import br.edu.ifpb.mysla.interfaces.Strategy;

public class Reserved implements Strategy {
	AmazonContext amazonContext;
	String name;
	
	
	public Reserved( ) {
		super();
		this.name = "Reserved";
		
	}
	
	public Reserved(AmazonContext amazonContext) {
		super();
		this.amazonContext = amazonContext;
		this.name = "Immediate Acess";
	}

	@Override
	public AmazonContext getContext() {
		// TODO Auto-generated method stub
		return this.amazonContext;
	}

	@Override
	public void setContext(Context context) {
		this.amazonContext = (AmazonContext) context;
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public boolean play() {
		
		// Faça algo...
				
		return false;
	}

}
