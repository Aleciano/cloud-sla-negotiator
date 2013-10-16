package br.edu.ifpb.aleciano.entidades.estrategias;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import br.edu.ifpb.aleciano.entidades.AmazonContext;
import br.edu.ifpb.aleciano.interfaces.Context;
import br.edu.ifpb.aleciano.interfaces.Strategy;

public class CostOptimization implements Strategy {
	AmazonContext amazonContext;
	String name;
	
	
	public CostOptimization(AmazonContext amazonContext) {
		super();
		this.amazonContext = amazonContext;
		this.name = "Cost Optimization";
	}
	
	public CostOptimization() {
		super();
		
		this.name = "Cost Optimization";
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
