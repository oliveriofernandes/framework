package view.utils;

import view.components.AbaChooseAlgorithm;
import view.components.AbaConfigureTestExperiments;
import control.StorageSettings;

public class PopulateConfigurationFields {

	public static void fillConfigurationFromProject(StorageSettings storageSettings){
		if (storageSettings.numberComparaties != 0){
			AbaChooseAlgorithm.numAlgSpinner.setValue(storageSettings.numberComparaties);
		}
	
		if (!storageSettings.comparisions.isEmpty()){
			AbaChooseAlgorithm.okButton.doClick();
			AbaChooseAlgorithm.confirmButton.doClick();
		}
	
		if (storageSettings.experimentStrategy!=null){
			AbaConfigureTestExperiments.experimentsComboBox.setSelectedItem(storageSettings.experimentStrategy);
		}
		if (!storageSettings.evaluationMetrics.isEmpty()){
			AbaConfigureTestExperiments.addButton.doClick();
			AbaConfigureTestExperiments.applyButton.doClick();
		}
	}
}
