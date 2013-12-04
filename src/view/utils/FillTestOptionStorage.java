package view.utils;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;

import javax.swing.JTextField;

import control.StorageSettings;

public class FillTestOptionStorage implements FocusListener {

	private StorageSettings st;
	private String componentName;

	public FillTestOptionStorage(StorageSettings st, String componentName) {
		this.st = st;
		this.componentName = componentName;
	}

	@Override
	public void focusGained(FocusEvent event) {
	}

	@Override
	public void focusLost(FocusEvent event) {
		JTextField source = (JTextField) event.getSource();

		Class<?> klass = this.st.getClass();
		if (!event.isTemporary()) {
			try {
				Field field = klass.getDeclaredField(componentName);
				klass.getField(field.getName()).setAccessible(true);
				klass.getField(field.getName()).set(st, source.getText());
				;

			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
