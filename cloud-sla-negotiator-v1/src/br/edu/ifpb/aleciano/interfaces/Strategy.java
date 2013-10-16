package br.edu.ifpb.aleciano.interfaces;
import java.util.ArrayList;

import br.edu.ifpb.aleciano.entidades.*;
/*
 * Interface para uma Estratégia de negociação
 */

public interface Strategy {

	Context getContext();
	void setContext(Context context);
	String getName();
	boolean play();
	/*
	 * Simula os parâmetros recebidos pelas APIs públicas da Amazon.
	 */
	//boolean writeLog(String destination, ArrayList<String> argv);
}
