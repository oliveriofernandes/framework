package view.utils;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class ColorFocusTextEdit implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {

		if (!e.isTemporary()) {
			JTextField source = (JTextField) e.getSource();
			source.setBackground(new Color(255, 255, 210));
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (!e.isTemporary()) {
			JTextField source = (JTextField) e.getSource();
			source.setBackground(Color.WHITE);
		}
	}
}
