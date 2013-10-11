package br.edu.ifpb.aleciano.entidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import br.edu.ifpb.aleciano.interfaces.Context;
import br.edu.ifpb.aleciano.interfaces.Strategy;

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
		
		ArrayList<String> argv = new ArrayList<String>(); argv.add("15"); argv.add("lars"); argv.add("wkeyx");
		writeLog("/home/junior/workspace/jee_workspace_juno/regras1/src/main/logs criados/", argv);
		
		return true;
	}

	@Override
	public boolean writeLog(String destination, ArrayList<String> argv) {
		FileWriter fw;
		try {
			if(argv.size()>0){
				File file = new File(destination + new Random().nextInt(10) + ".txt");
				file.delete(); //deleta caso o arquivo exista.
				fw = new FileWriter(file, false);
			}
			else
				fw = new FileWriter(new File(destination + "??" + ".txt"),
						true);
		} catch (IOException e) {
			return false;
		}
		PrintWriter arq = new PrintWriter(fw);
		arq.write("".toCharArray());
		arq.format("Strategy: %s\nInstance type: %s\nNum. of instances: %s\nGeo Zone: %s\nLogin: %s\nWkey: %s", this.getName(), 
				amazonContext.getInstanceType(),
				amazonContext.getInstancesNum(), amazonContext.getGeoZone(),
				argv.get(1), argv.get(2));
		arq.close();
		try {
			fw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
		
	}

	

}
