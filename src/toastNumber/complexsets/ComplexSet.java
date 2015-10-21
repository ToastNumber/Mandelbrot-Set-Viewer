package toastNumber.complexsets;

public interface ComplexSet {
	public int getNumIterationsToFail(double x0, double y0, int maxIterations);
	public int getSuggestedIterations();
	public int getCurrentNumIterations();
	public void setCurrentNumIterations(int numIterations);
	public double getMinX();
	public double getMaxX();
	public double getMinY();
	public double getMaxY();
	public String getName();
}
