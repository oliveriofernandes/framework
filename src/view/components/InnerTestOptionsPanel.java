package view.components;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enums.TestOptions;

public class InnerTestOptionsPanel {

	public static void mount (JPanel settingsOptionPanel, JLabel trainPercentLabel, JLabel testPercentLabel, 
		
			
		JTextField testPercentTextField, JTextField trainPercentTextField, TestOptions t){
		
		t.setLabel(trainPercentLabel, testPercentLabel);
		
		settingsOptionPanel.removeAll();
		GroupLayout settingsOptionPanelLayout = new GroupLayout(settingsOptionPanel);
		settingsOptionPanel.setLayout(settingsOptionPanelLayout);
		settingsOptionPanelLayout.setHorizontalGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(settingsOptionPanelLayout.createSequentialGroup()
			.addContainerGap().addGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(trainPercentLabel)
			.addComponent(testPercentLabel))
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
			.addComponent(testPercentTextField)
			.addComponent(trainPercentTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			.addContainerGap(51, Short.MAX_VALUE)));
		                  
		settingsOptionPanelLayout.setVerticalGroup(
		settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(settingsOptionPanelLayout.createSequentialGroup()
		.addContainerGap().addGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(settingsOptionPanelLayout.createSequentialGroup()
		.addGap(29, 29, 29)
		.addGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		.addComponent(testPercentLabel).addComponent(testPercentTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		.addGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		.addComponent(trainPercentLabel).addComponent(trainPercentTextField, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
		.addContainerGap(21, Short.MAX_VALUE)));
		
    }
	
	

}
	

