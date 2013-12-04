package view.utils;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CompletePercentage implements FocusListener {

	JTextField otherTextField;

	public CompletePercentage(JTextField otherTextField) {
		this.otherTextField = otherTextField;
	}

	@Override
	public void focusGained(FocusEvent event) {
		if(!event.isTemporary()){  
				JTextField source = (JTextField)event.getSource();  
				if ( (!otherTextField.getText().equals("")) && (!source.getText().isEmpty()) ) {
					double valueEntried = Double.parseDouble(source.getText());
					if (valueEntried > 100) {
						source.setText(null);
						otherTextField.setText(null);
						JOptionPane.showMessageDialog(null,"the value "
								+ valueEntried + " not correspond a desirable percentage range, enter another value!",
								"Value out of percent bound ",JOptionPane.ERROR_MESSAGE);
					} else {
							double value = 100 - valueEntried;
							otherTextField.setText(String.valueOf(value));
							source.setText(String.valueOf(Double.parseDouble(source.getText())));
					}
				}
			}
		}	

	@Override
	public void focusLost(FocusEvent event) {
		if (!event.isTemporary()) {
			JTextField source = (JTextField) event.getSource();
			try {
				double valueEntried = Double.parseDouble(source.getText());
				if (valueEntried > 100) {
					source.setText(null);
					otherTextField.setText(null);
					JOptionPane.showMessageDialog(null, "the value " + valueEntried 
								+ " not correspond a desirable percentage range, enter another value!",
								"Value out of percent bound ", JOptionPane.ERROR_MESSAGE);
				} else {
					double value = 100 - valueEntried;
					otherTextField.setText(String.valueOf(value));
					source.setText(String.valueOf(Double.parseDouble(source.getText())));
				}
			} catch (java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.getMessage()
						+ ", enter another value!", "Number format error", JOptionPane.ERROR_MESSAGE);
				source.setText(null);
				otherTextField.setText(null);
			}
		}
	}
}