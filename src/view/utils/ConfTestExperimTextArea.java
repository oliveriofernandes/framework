package view.utils;

import javax.swing.JTextArea;

public class ConfTestExperimTextArea extends JTextArea{
	
	private static final long serialVersionUID = 7378196514708697824L;
	private static ConfTestExperimTextArea instance;
	private ConfTestExperimTextArea(){}
	
	public static ConfTestExperimTextArea getInstance(){
		if (instance == null)
			instance = new ConfTestExperimTextArea();
		return instance;
	}
}
