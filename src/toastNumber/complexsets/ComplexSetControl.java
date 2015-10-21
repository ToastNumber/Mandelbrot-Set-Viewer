package toastNumber.complexsets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ComplexSetControl extends JPanel {
	private static final long serialVersionUID = 1L;

	public ComplexSetControl(final ComplexSetModel model, final ComplexSetView view) {
		super();

		JLabel lblCalculationAccuracy = new JLabel("Calculation Accuracy");
		lblCalculationAccuracy.setFocusable(false);
		add(lblCalculationAccuracy);

		JSlider sldrCalculationAccuracy = new JSlider(100, 1200, 200);
		sldrCalculationAccuracy.setFocusable(false);
		sldrCalculationAccuracy.setMajorTickSpacing(100);
		sldrCalculationAccuracy.setMinorTickSpacing(50);
		sldrCalculationAccuracy.setSnapToTicks(true);
		sldrCalculationAccuracy.setPaintLabels(true);
		sldrCalculationAccuracy.setLabelTable(sldrCalculationAccuracy.createStandardLabels(200));
		sldrCalculationAccuracy.setPaintTicks(true);
		sldrCalculationAccuracy.addChangeListener(e -> model.setCurrentNumIterations(sldrCalculationAccuracy.getValue()));
		add(sldrCalculationAccuracy);

		String[] names = model.getNames();

		JButton[] buttons = new JButton[names.length];
		for (int i = 0; i < names.length; ++i) {
			final int index = i;

			buttons[i] = new JButton(names[index]);
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(e -> model.setComplexSetIndex(index));

			add(buttons[i]);
		}

		JButton btnResetView = new JButton("Reset View");
		btnResetView.setFocusable(false);
		btnResetView.addActionListener(e -> view.resetView());
		
		add(btnResetView);

		JCheckBox checkBoxColorful = new JCheckBox("Colorful");
		checkBoxColorful.setFocusable(false);
		checkBoxColorful.addActionListener(e -> view.setColorful(!view.isColorful()));
		add(checkBoxColorful);

	}
}
