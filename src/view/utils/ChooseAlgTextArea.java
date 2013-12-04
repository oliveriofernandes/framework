package view.utils;

import javax.swing.JTextArea;

public class ChooseAlgTextArea extends JTextArea{
	
	private static final long serialVersionUID = 7378196514708697824L;
	private static ChooseAlgTextArea instance;
	private ChooseAlgTextArea(){}
	
	public static ChooseAlgTextArea getInstance(){
		if (instance == null)
			instance = new ChooseAlgTextArea();
		return instance;
	}
}
