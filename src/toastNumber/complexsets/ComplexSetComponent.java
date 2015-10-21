package toastNumber.complexsets;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ComplexSetComponent extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ComplexSetComponent(ComplexSet[] complexSets) {
		super();
		
		ComplexSetModel model = new ComplexSetModel(complexSets);
		ComplexSetView view = new ComplexSetView(model);
		ComplexSetControl pnlControl = new ComplexSetControl(model, view);
		
		model.addObserver(view);
		
		setLayout(new BorderLayout());
		add(view, BorderLayout.CENTER);
		add(pnlControl, BorderLayout.SOUTH);
	}
}
