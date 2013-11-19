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

public class MinimizeInterruption implements Strategy {
	AmazonContext amazonContext;
	String name;
		
	public MinimizeInterruption(AmazonContext amazonContext) {
		super();
		this.amazonContext = amazonContext;
		this.name = "Minimize Interruption";
	}
	
	public MinimizeInterruption() {
		super();
		
		this.name = "Minimize Interruption";
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
		
		return true;
	}

}
