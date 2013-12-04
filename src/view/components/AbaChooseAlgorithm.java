package view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

import util.SetText;
import view.utils.ChooseAlgTextArea;
import view.utils.ConfTestExperimTextArea;
import view.utils.InnerTableContent;
import control.Comparision;
import control.ListClasses;
import control.StorageSettings;

public class AbaChooseAlgorithm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel numAlgorithmsLabel, algorithmLabel;
	private JTabbedPane innerTabbedPane;
	private JPanel numAlgPanel, settingsPanel, confirmPanel;
	public static JButton okButton, confirmButton, declineButton;
	private JScrollPane settingsScrollPane;
	private ChooseAlgTextArea settingsTextArea; 
	public static JSpinner numAlgSpinner;
	private JTable confirmTable;
	private JScrollPane confirmScrollPanel;
	private StorageSettings storageSettings;
	
	public AbaChooseAlgorithm(){
		initComponents();
	}
	
	private void initComponents(){
		numAlgorithmsLabel = new JLabel("Number of comparisons: ");
        innerTabbedPane = new JTabbedPane();
        algorithmLabel = new JLabel("Algorithm");
        new JLabel("Similarity");
        declineButton = new JButton("Decline");
        okButton = new JButton("Ok");
        settingsPanel = new JPanel();
        settingsScrollPane = new JScrollPane();
        settingsTextArea = ChooseAlgTextArea.getInstance();
        numAlgSpinner = new JSpinner(new SpinnerNumberModel(2,StorageSettings.numMinComparisons,StorageSettings.numMaxComparisons,1));
        numAlgSpinner.setSize(3, 5);
        numAlgPanel = new JPanel();
        confirmPanel = new JPanel();
        algorithmLabel.setText("Algorithm");
        confirmTable = new JTable(); 
        confirmButton = new JButton();
        confirmScrollPanel = new JScrollPane();
        
        JPanel dummyPanel = new JPanel();
        dummyPanel.setBackground(new Color(240,240,240));
        dummyPanel.add(new JLabel("Comparison among algorithms and similarities meansures"));
        innerTabbedPane.add(dummyPanel, "Chooses");
      
        settingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));
        settingsPanel.setAutoscrolls(true);

        settingsTextArea.setColumns(20);
        settingsTextArea.setRows(8);
        
        settingsScrollPane.setViewportView(settingsTextArea);
    
        //Storage the settings...
        storageSettings = StorageSettings.getInstance();
        GroupLayout settingsPanelLayout = new GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settingsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addContainerGap())
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addComponent(settingsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        numAlgPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		
        okButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int numberOfAlgorithms = Integer.parseInt(numAlgSpinner.getValue().toString());				
				innerTabbedPane.removeAll();
				storageSettings.setNumberComparaties(numberOfAlgorithms);
        
				List<Comparision> comparisions = new ArrayList<Comparision>();
				if (!storageSettings.comparisions.isEmpty())
					comparisions.addAll(storageSettings.comparisions);
				
				for (int i = 0; i < numberOfAlgorithms; i++) {
					InnerTableContent tableContent = new InnerTableContent();
					if (storageSettings.comparisions.isEmpty()){
						JPanel panel;
						panel = tableContent.mountTabbleContent(innerTabbedPane, confirmTable, null);
						innerTabbedPane.addTab("comparison: " + (i+1), panel);
					}
				
					else if ((!storageSettings.comparisions.isEmpty())){
						if (i<storageSettings.comparisions.size()){
							JPanel panel = tableContent.mountTabbleContent(innerTabbedPane, confirmTable, comparisions.get(i));
							innerTabbedPane.addTab("comparison: " + (i+1), panel);
						} else{
							JPanel panel = tableContent.mountTabbleContent(innerTabbedPane, confirmTable, null);
							innerTabbedPane.addTab("comparison: " + (i+1), panel);
						}
					}
				}
			
				confirmPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Confirm"));
				confirmPanel.setAutoscrolls(true);

				confirmTable.setFont(new java.awt.Font("Tahoma", 0, 11));
				Object[][] objVector = new Object[numberOfAlgorithms][3];

				for (int i = 0; i < numberOfAlgorithms; i++)
					for (int j = 0; j < 3; j++)
						if (j == 0)
							objVector[i][j] = i + 1;
						else
							objVector[i][j] = null;

				confirmTable.setModel(new javax.swing.table.DefaultTableModel(
						objVector, new String[] { "Tab number", "Algorithm",
								"Similarity measure" }));
			
				
				
				if (!storageSettings.isNewProject){
				
					List<Comparision> list = new ArrayList<Comparision>();
					list.addAll(storageSettings.comparisions);
					
					

					storageSettings = StorageSettings.getInstance();
					for (int i = 1; i < confirmTable.getColumnCount(); i++) {
						for (int j = 0; j < confirmTable.getRowCount(); j++) {
							if ((i == 1) && (j)<(list.size()))
								confirmTable.setValueAt(list.get(j).getAlgorithtm(), j, i);
							else if (((i == 2) && (j)<(list.size())))
								confirmTable.setValueAt(list.get(j).getSimilarity(), j, i);
						}
					}
					
				}
			
			}
        
        });
        
        confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Comparision> comparisions = new ArrayList<Comparision>();
				boolean canFoward = false;
				if (verifyFieldNotNull()){
						for (int i = 0; i < confirmTable.getRowCount(); i++) {
							Comparision c = new Comparision();
							c.setAlgorithtm(confirmTable.getValueAt(i,1).toString());
							c.setSimilarity(confirmTable.getValueAt(i,2).toString());
							c.setClsAlgorithtm(ListClasses.getClassByPresentedAnnotation(confirmTable.getValueAt(i,1).toString(),"predictions"));
							c.setClsSimilarity(ListClasses.getClassByPresentedAnnotation(confirmTable.getValueAt(i,2).toString(),"similarities"));
//							c.setClsContext(ListClasses.getClassByPresentedAnnotation(),);
							
							
							if (comparisions.contains(c)){
								JOptionPane.showMessageDialog(null, "You must select diferents parameters!", "Incomplete entry", JOptionPane.WARNING_MESSAGE);
								canFoward = false;
							}else{
								comparisions.add(c);
								canFoward = true;
							}
						}
				}
				else {
					JOptionPane.showMessageDialog(null, "You need to fill all the fields into the table!");
					canFoward = false;
					}
				if (canFoward){
					if (comparisions.size()>1){
					storageSettings.setComparisions(comparisions);
					fillNextTextArea(settingsTextArea, 5);
					ConfTestExperimTextArea x = ConfTestExperimTextArea.getInstance();
					fillNextTextArea(x, 5);
					if (storageSettings.isNewProject)
						JOptionPane.showMessageDialog(null, "Step completed!", "Status", JOptionPane.INFORMATION_MESSAGE);
					
					}
				}
			
				List<Comparision> list = new ArrayList<Comparision>();
				
				list.addAll(storageSettings.comparisions);
				
				if (!storageSettings.isNewProject){
					storageSettings = StorageSettings.getInstance();
					for (int i = 1; i < confirmTable.getColumnCount(); i++) {
						for (int j = 0; j < confirmTable.getRowCount(); j++) {
							if (i == 1)
								confirmTable.setValueAt(list.get(j).getAlgorithtm(), j, i);
							else
								confirmTable.setValueAt(list.get(j).getSimilarity(), j, i);
						}
					}
				}
			
			}
				
			private boolean verifyFieldNotNull(){
				for (int i = 1; i < confirmTable.getColumnCount(); i++) {
					for (int j = 0; j < confirmTable.getRowCount(); j++) {
						if (confirmTable.getValueAt(j,i) == null || confirmTable.getValueAt(j,i)=="")
							return false;
					}
				}
			return true;
			}
        });

		declineButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				storageSettings.setComparisions(new ArrayList<Comparision>());
				SetText setText = new SetText(settingsTextArea,4);
				Thread thread = new Thread(setText);
				thread.start();
	
				innerTabbedPane.removeAll();
				JPanel dummyPanel = new JPanel();
		        dummyPanel.setBackground(new Color(240,240,240));
		        dummyPanel.add(new JLabel("Comparison among algorithms and similarities meansures"));
		        innerTabbedPane.add(dummyPanel, "Chooses");
		        storageSettings.setComparisions(new HashSet<Comparision>());
		        
		        confirmPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Confirm"));
		        confirmPanel.setAutoscrolls(true);
		        confirmTable.setFont(new java.awt.Font("Tahoma", 0, 11));
				Object[][] objVector = new Object[0][3];

				confirmTable.setModel(new javax.swing.table.DefaultTableModel(
							objVector, new String[] { "Tab number", "Algorithm",
							"Similarity measure" }));
					}
		        });

        GroupLayout numAlgPanelLayout = new javax.swing.GroupLayout(numAlgPanel);
        numAlgPanel.setLayout(numAlgPanelLayout);
        numAlgPanelLayout.setHorizontalGroup(
            numAlgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(numAlgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numAlgorithmsLabel)
                .addGap(10, 10, 10)
                .addComponent(numAlgSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(okButton)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        numAlgPanelLayout.setVerticalGroup(
            numAlgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(numAlgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(numAlgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numAlgSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okButton)
                    .addComponent(numAlgorithmsLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        confirmScrollPanel.setViewportView(confirmTable);

        confirmButton.setText("Confirm");

        declineButton.setText("Decline");

        javax.swing.GroupLayout confirmPanelLayout = new javax.swing.GroupLayout(confirmPanel);
        confirmPanel.setLayout(confirmPanelLayout);
        confirmPanelLayout.setHorizontalGroup(
            confirmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmPanelLayout.createSequentialGroup()
                .addGroup(confirmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(confirmPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(confirmScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE))
                    .addGroup(confirmPanelLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(confirmButton)
                        .addGap(18, 18, 18)
                        .addComponent(declineButton)))
                .addContainerGap())
        );
        confirmPanelLayout.setVerticalGroup(
            confirmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(confirmScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(confirmPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(declineButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        confirmButton.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(numAlgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(innerTabbedPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                    .addComponent(confirmPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(135, 135, 135))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(numAlgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(innerTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
        
	}
	
	protected void fillNextTextArea(JTextArea j, int num) {
		SetText setText = new SetText(j, num);
		Thread thread = new Thread(setText);
		thread.start();
		
	}

}
