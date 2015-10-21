package toastNumber.complexsets;

import java.util.Observable;

public class ComplexSetModel extends Observable {
	private ComplexSet[] complexSets;
	private int complexSetIndex;
	
	public ComplexSetModel (ComplexSet[] complexSets) {
		this.complexSets = complexSets;
		complexSetIndex = 0;
	}
	
	public int getNumIterationsToFail(double x0, double y0, int maxIterations) {
		return complexSets[complexSetIndex].getNumIterationsToFail(x0, y0, maxIterations);
	}

	public double getMinX() {
		return complexSets[complexSetIndex].getMinX();
	}

	public double getMaxX() {
		return complexSets[complexSetIndex].getMaxX();
	}

	public double getMinY() {
		return complexSets[complexSetIndex].getMinY();
	}

	public double getMaxY() {
		return complexSets[complexSetIndex].getMaxY();
	}

	public int getSuggestedIterations() {
		return complexSets[complexSetIndex].getSuggestedIterations();
	}

	public int getCurrentNumIterations() {
		return complexSets[complexSetIndex].getCurrentNumIterations();
	}
	
	public String[] getNames() {
		String[] names = new String[complexSets.length];
		for (int i = 0; i < names.length; ++i) {
			names[i] = complexSets[i].getName();
		}
		
		return names;
	}
	
	public void setComplexSetIndex(int i) {
		this.complexSetIndex = i;
		setChanged();
		notifyObservers();
	}
	
	public int getComplexSetIndex() {
		return complexSetIndex;
	}
	
	public void setCurrentNumIterations(int n) {
		complexSets[complexSetIndex].setCurrentNumIterations(n);
		setChanged();
		notifyObservers();
	}
	
	public int getNumComplexSets() {
		return complexSets.length;
	}
}
