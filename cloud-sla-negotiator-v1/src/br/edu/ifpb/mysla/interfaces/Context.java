package br.edu.ifpb.mysla.interfaces;

import java.util.Collection;

public interface Context {

	public Context getContext();
	public void setContext(Collection<Object> objects);
	String toString();
	//void setContext(T context);
	
}
