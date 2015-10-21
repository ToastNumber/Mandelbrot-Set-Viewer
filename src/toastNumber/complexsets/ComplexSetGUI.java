package toastNumber.complexsets;

import javax.swing.JFrame;

public class ComplexSetGUI {
	public static void main(String[] theresASnakeInMyBoot) {
		Mandelbrot mandelbrot = new Mandelbrot();
		ComplexSet[] complexSets = { mandelbrot };
		ComplexSetComponent comp = new ComplexSetComponent(complexSets);

		JFrame frame = new JFrame("Complex Sets");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(50, 50);
		frame.setSize(7 * 150, 4 * 150);

		frame.add(comp);
		frame.setVisible(true);
	}
}
