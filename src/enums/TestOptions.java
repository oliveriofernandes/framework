package enums;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import control.StorageSettings;
/**
 * @author Oliverio
 */
public enum TestOptions {

	TOTALY_RANDON("Totaly randon") {
		@Override
		public boolean doVerify(StorageSettings strSettings) {
			return (!(strSettings.percentTest == null || strSettings.percentTrain == null
					|| strSettings.percentTest.isEmpty() || strSettings.percentTrain.isEmpty()));
		}

		@Override
		public void setLabel(JLabel trainPercentLabel, JLabel testPercentLabel) {
			trainPercentLabel.setText("Train percent: ");
			testPercentLabel.setText("Test percent: ");
		}
	},
	
	USER_RANDON("User randon") {
		@Override
		public boolean doVerify(StorageSettings strSettings) {
			return (!(strSettings.percentTest == null || strSettings.percentTrain == null
					|| strSettings.percentTest.isEmpty() || strSettings.percentTrain.isEmpty()));
		}

		@Override
		public void setLabel(JLabel trainPercentLabel, JLabel testPercentLabel) {
			trainPercentLabel.setText("Train percent(User): ");
			testPercentLabel.setText("Test percent(User): ");
		}
	},
	
	NON_TEST("Non test set") {
		@Override
		public boolean doVerify(StorageSettings strSettings) {
			return (!(strSettings.percentTest == null || strSettings.percentTrain == null
					|| strSettings.percentTest.isEmpty() || strSettings.percentTrain.isEmpty()));
		}
		
		@Override public void setLabel(JLabel trainPercentLabel, JLabel testPercentLabel){
        	
        }
	};
	private String value;
	public StorageSettings storageSettings;

	private TestOptions(String value) {
		this.value = value;
		storageSettings = StorageSettings.getInstance();
	}

	public String getValue() {
		return value;
	}

	public static TestOptions byValue(String str) {
		for (TestOptions t : TestOptions.values()) {
			if (t.value.equals(str))
				return t;
		}
		return null;
	}

	public boolean validateEntry(String str) {
		if ((str == null))
			throw new IllegalArgumentException("Text or labels cannot be null!");

		if (verify(str))
			return true;
		else
			return false;
	}

	public static List<String> getValuesAsList() {
		List<String> list = new ArrayList<String>();

		for (TestOptions obj : TestOptions.values())
			list.add(obj.value);
		return list;
	}

	public static Object[] getValuesAsArray() {
		return TestOptions.getValuesAsList().toArray();
	}

	private final boolean verify(String str) {
		StorageSettings s = StorageSettings.getInstance();
		return doVerify(s);
	}

	public abstract boolean doVerify(StorageSettings strSettings);

	public  abstract void setLabel(JLabel trainPercentLabel, JLabel testPercentLabel);
}