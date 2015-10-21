package toastNumber.complexsets;

public class Mandelbrot implements ComplexSet {
	public static final double MIN_X = -2.5;
	public static final double MAX_X = 1.0;
	public static final double MIN_Y = -1.0;
	public static final double MAX_Y = 1.0;
	public static final int SUGGESTED_ITERATIONS = 300;
	private int currentNumIterations;
	
	public Mandelbrot() {
		currentNumIterations = SUGGESTED_ITERATIONS;
	}
	
	public int getNumIterationsToFail(double x0, double y0, int maxIterations) {
		if (x0 < MIN_X || x0 > MAX_X || y0 < MIN_Y || y0 > MAX_Y) {
			System.out.printf("x0: %f%ny0: %f%n", x0, y0);
			throw new IllegalArgumentException("Argument out of legal bounds of Mandelbrot set");
		}
		double x = 0.0;
		double y = 0.0;
		int numIterations = 0;
		do {
			double xtemp = x*x - y*y + x0;
			y = 2*x*y + y0;
			x = xtemp;
			++numIterations;
		} while (x*x + y*y < 4 && numIterations < maxIterations);
		
		return numIterations;
	}

	@Override
	public double getMinX() {
		return MIN_X;
	}

	@Override
	public double getMaxX() {
		return MAX_X;
	}

	@Override
	public double getMinY() {
		return MIN_Y;
	}

	@Override
	public double getMaxY() {
		return MAX_Y;
	}

	@Override
	public int getSuggestedIterations() {
		return SUGGESTED_ITERATIONS;
	}
	

	@Override
	public int getCurrentNumIterations() {
		return currentNumIterations;
	}
	
	@Override
	public void setCurrentNumIterations(int numIterations) {
		this.currentNumIterations = numIterations;
	}
	
	public String getName() {
		return "Mandelbrot";
	}
	
}
