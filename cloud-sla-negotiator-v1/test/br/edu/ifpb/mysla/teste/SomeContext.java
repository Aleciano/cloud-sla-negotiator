package br.edu.ifpb.mysla.teste;

import java.util.ArrayList;
import java.util.Collection;

import br.edu.ifpb.mysla.interfaces.Context;

public class SomeContext implements Context {
	private boolean immediateAccess;
	private boolean shortDuration;
	private boolean uninterruptedAccess;
	private boolean minimizeCompletionTime;
	private boolean minimizeCost;
	int instancesNum;
	String instanceType;
	int geoZone;

	public SomeContext(boolean immediateAccess, boolean duration,
			boolean uninterruptedAcess, boolean minimizeCompletionTime,
			boolean minimizeCost, int instancesNum, String instanceType,
			int geoZsone) {
		super();
		this.immediateAccess = immediateAccess;
		this.shortDuration = duration;
		this.uninterruptedAccess = uninterruptedAcess;
		this.minimizeCompletionTime = minimizeCompletionTime;
		this.minimizeCost = minimizeCost;
		this.instancesNum = instancesNum;
		this.instanceType = instanceType;
		this.geoZone = geoZone;
	}

	public SomeContext() {
		super();
		this.immediateAccess = false;
		this.shortDuration = false;
		this.uninterruptedAccess = false;
		this.minimizeCompletionTime = false;
		this.minimizeCost = false;
		this.instancesNum = 0;
		this.instanceType = "";
		this.geoZone = 1;
	}

	/*
	 * Checa se o valor de geoZone está dentro da faixa de valores suportados.
	 * Pode ser acessado sem precisar de criar uma instância desta classe.
	 */

	public static boolean checkGeoZone(int geoZone) {
		if (geoZone >= 1 && geoZone <= 5)
			return true;
			
		return false;
	}

	public boolean isImmediateAccess() {
		return immediateAccess;
	}

	public void setImmediateAccess(boolean immediateAccess) {
		this.immediateAccess = immediateAccess;
	}

	public boolean isShortDuration() {
		return shortDuration;
	}

	public void setShortDuration(boolean duration) {
		this.shortDuration = duration;
	}

	public boolean isUninterruptedAccess() {
		return uninterruptedAccess;
	}

	public void setUninterruptedAccess(boolean uninterruptedAccess) {
		this.uninterruptedAccess = uninterruptedAccess;
	}

	public boolean isMinimizeCompletionTime() {
		return minimizeCompletionTime;
	}

	public void setMinimizeCompletionTime(boolean minimizeCompletionTime) {
		this.minimizeCompletionTime = minimizeCompletionTime;
	}

	public boolean isMinimizeCost() {
		return minimizeCost;
	}

	public void setMinimizeCost(boolean minimizeCost) {
		this.minimizeCost = minimizeCost;
	}

	public int getInstancesNum() {
		return instancesNum;
	}

	public void setInstancesNum(int instancesNum) {
		this.instancesNum = instancesNum;
	}

	public String getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public int getGeoZone() {
		return geoZone;
	}

	public void setGeoZone(int geoZone) {
		this.geoZone = geoZone;
	}

	@Override
	public String toString() {
		return "SomeContext [immediateAccess=" + immediateAccess
				+ ", shortDuration=" + shortDuration + ", uninterruptedAccess="
				+ uninterruptedAccess + ", minimizeCompletionTime="
				+ minimizeCompletionTime + ", minimizeCost=" + minimizeCost
				+ ", instancesNum=" + instancesNum + ", instanceType="
				+ instanceType + ", geoZone=" + geoZone + "]";
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setContext(Collection<Object> objects) {
		try {
			this.immediateAccess = (boolean) ((ArrayList) (objects)).get(0);
			this.shortDuration = (boolean) ((ArrayList) (objects)).get(1);
			this.uninterruptedAccess = (boolean) ((ArrayList) (objects)).get(2);
			this.minimizeCompletionTime = (boolean) ((ArrayList) (objects))
					.get(3);
			this.minimizeCost = (boolean) ((ArrayList) (objects)).get(4);
			this.instancesNum = (int) ((ArrayList) (objects)).get(5);
			this.instanceType = (String) ((ArrayList) (objects)).get(6);
			this.geoZone = (int) ((ArrayList) (objects)).get(7);
		} catch (ClassCastException e) {
			throw e;
					
		}
	}

}
