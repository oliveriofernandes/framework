package view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

import util.SetText;
import view.utils.ConfTestExperimTextArea;
import control.EvaluationMetric;
import control.ListClasses;
import control.StorageSettings;

public class AbaConfigureTestExperiments extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel chooseStrategyLabel, evaluationMetricChooseLabel;
	private JButton subButton, declineButton;
	public static JButton applyButton, addButton;
	public static JComboBox experimentsComboBox, evaluationMetricsComboBox;
	private JPanel expStategiesPanel, evalMatricsPanel, settingsPanel, resumePanel;
	private JScrollPane settingsScrollPane, resumeScrollPane;
	public static JTextArea resumeTextArea;
	private ConfTestExperimTextArea textAreaConfTestExperim;
	private StorageSettings  storageSettings;
	
	public AbaConfigureTestExperiments(){
		try {
			initComponents();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void initComponents() throws ClassNotFoundException, IOException {

        settingsPanel = new JPanel();
        settingsScrollPane = new JScrollPane();
        textAreaConfTestExperim = ConfTestExperimTextArea.getInstance();
        expStategiesPanel = new JPanel();
        experimentsComboBox = new JComboBox();
        chooseStrategyLabel = new JLabel();
        evalMatricsPanel = new JPanel();
        evaluationMetricChooseLabel = new JLabel();
        evaluationMetricsComboBox = new JComboBox();
        addButton = new JButton();
        subButton = new JButton();
        resumePanel = new JPanel();
        resumeScrollPane = new JScrollPane();
        resumeTextArea = new JTextArea();
        applyButton = new JButton("Apply");
        declineButton = new JButton("Decline");

        settingsPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
        settingsPanel.setAutoscrolls(true);

        textAreaConfTestExperim.setColumns(20);
        textAreaConfTestExperim.setRows(5);
        settingsScrollPane.setViewportView(textAreaConfTestExperim);
        
        
        storageSettings = StorageSettings.getInstance();
        experimentsComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(experimentsComboBox.getSelectedItem().equals("K Fold")){
					
					if(StorageSettings.getInstance().isNewProject==true){
					 KValue win = new KValue();
					 win.setVisible(true);
					 win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					 
					}
					 
					 //new KValue().setVisible(true);
					
					//JOptionPane.showMessageDialog(null, "successfully saved  ", "Saved Project", JOptionPane.YES_OPTION);
				
				}
				
			}
		});
       
        addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Collection<EvaluationMetric> evalMetrics = storageSettings.getEvaluationMetrics();
				EvaluationMetric eM = new EvaluationMetric();
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Evaluation metrics selected:");
				stringBuilder.append("\n");
				eM.setMetric(evaluationMetricsComboBox.getSelectedItem().toString());
				
				if (storageSettings.isNewProject){
				if ((!evalMetrics.contains(eM))){
					evalMetrics.add(eM);
					for (EvaluationMetric evaluationMetric : evalMetrics) {
						stringBuilder.append(evaluationMetric.getMetric());
						stringBuilder.append("\n");
					}
					resumeTextArea.setText(stringBuilder.toString());
				}
				else
					JOptionPane.showMessageDialog(null, "This item was included!");
				}
				else {
					
//					if ((!evalMetrics.contains(eM))){
//						evalMetrics.add(eM);
//						for (EvaluationMetric evaluationMetric : evalMetrics) {
//							stringBuilder.append(evaluationMetric.getMetric());
//							stringBuilder.append("\n");
//						}
//						resumeTextArea.setText(stringBuilder.toString());
//					}
//					else if ((evalMetrics.contains(eM))){
//						JOptionPane.showMessageDialog(null, "This item was included!");
//					}
					if (evalMetrics.contains(eM) && (resumeTextArea.getText().contains(evalMetrics.toString()))) 
						JOptionPane.showMessageDialog(null, "This item already was included!");
					 else if ((evalMetrics.contains(eM)) && (!resumeTextArea.getText().contains(evalMetrics.toString()))){
						//evalMetrics.add(eM);
						for (EvaluationMetric evaluationMetric : evalMetrics) {
							stringBuilder.append(evaluationMetric.getMetric());
							stringBuilder.append("\n");
						}
						resumeTextArea.setText(stringBuilder.toString());
					} 
					 if (!evalMetrics.contains(eM) && !(resumeTextArea.getText().contains(evalMetrics.toString()))){
						 evalMetrics.add(eM);
							for (EvaluationMetric evaluationMetric : evalMetrics) {
								stringBuilder.append(evaluationMetric.getMetric());
								stringBuilder.append("\n");
							}
							resumeTextArea.setText(stringBuilder.toString());
					}

				}
			}

		});
        subButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Collection<EvaluationMetric> evalMetrics = storageSettings.getEvaluationMetrics();
				EvaluationMetric eM = new EvaluationMetric();
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Evaluation metrics selected:");
				stringBuilder.append("\n");
				eM.setMetric(evaluationMetricsComboBox.getSelectedItem().toString());
				if (evalMetrics.contains(eM)){
					evalMetrics.remove(eM);
					for (EvaluationMetric evaluationMetric : evalMetrics) {
						stringBuilder.append(evaluationMetric.getMetric());
						stringBuilder.append("\n");
					}
					
					resumeTextArea.setText(stringBuilder.toString());
				}
				
				else
					JOptionPane.showMessageDialog(null, "This item wasn't selected");
				
			}
		});
        applyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				storageSettings.setExperimentStrategy(experimentsComboBox.getSelectedItem().toString());
				textAreaConfTestExperim.setText("");
				
				if (storageSettings.evaluationMetrics.size()>0){
					SetText setText = new SetText(textAreaConfTestExperim,7);
					Thread thread = new Thread(setText);
					thread.start();	
				}else{
					JOptionPane.showMessageDialog(null, "You must select at least one evaluation metric at combo box above!");
					storageSettings.setExperimentStrategy(experimentsComboBox.getSelectedItem().toString());
					SetText setText = new SetText(textAreaConfTestExperim,6);
					Thread thread = new Thread(setText);
					thread.start();	
				}
			}
		});

        
        declineButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resumeTextArea.setText("");
				storageSettings.setEvaluationMetrics(new ArrayList<EvaluationMetric>());
				storageSettings.setExperimentStrategy("");
				SetText setText = new SetText(textAreaConfTestExperim,5);
				Thread thread = new Thread(setText);
				thread.start();
				
			}
		});
        GroupLayout settingsPanelLayout = new GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settingsScrollPane, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addContainerGap())
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addComponent(settingsScrollPane, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );

        expStategiesPanel.setBorder(BorderFactory.createTitledBorder("Expirements strategies"));

        experimentsComboBox.setModel(new DefaultComboBoxModel( ListClasses.getClassNamesByPkg("experimentsStrategies",true,true)));
        
        chooseStrategyLabel.setText("Choose stragegy:");

        GroupLayout expStategiesPanelLayout = new GroupLayout(expStategiesPanel);
        expStategiesPanel.setLayout(expStategiesPanelLayout);
        expStategiesPanelLayout.setHorizontalGroup(
            expStategiesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(expStategiesPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(chooseStrategyLabel)
                .addGap(18, 18, 18)
                .addComponent(experimentsComboBox, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        expStategiesPanelLayout.setVerticalGroup(
            expStategiesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, expStategiesPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(expStategiesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseStrategyLabel)
                    .addComponent(experimentsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        evalMatricsPanel.setBorder(BorderFactory.createTitledBorder("Evaluation metric"));

        evaluationMetricChooseLabel.setText("Choose Metric:");

        evaluationMetricsComboBox.setModel(new DefaultComboBoxModel(ListClasses.getClassNamesByPkg("evaluationMetrics",true,false)));

        addButton.setText("+");

        subButton.setText("-");

        GroupLayout evalMatricsPanelLayout = new GroupLayout(evalMatricsPanel);
        evalMatricsPanel.setLayout(evalMatricsPanelLayout);
        evalMatricsPanelLayout.setHorizontalGroup(
            evalMatricsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(evalMatricsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(evaluationMetricChooseLabel)
                .addGap(12, 12, 12)
                .addComponent(evaluationMetricsComboBox, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subButton)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        evalMatricsPanelLayout.setVerticalGroup(
            evalMatricsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(evalMatricsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(evalMatricsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(evaluationMetricsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(evaluationMetricChooseLabel)
                    .addComponent(subButton, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        resumePanel.setBorder(BorderFactory.createTitledBorder("Resumè"));

        resumeTextArea.setBackground(new java.awt.Color(240, 240, 240));
        resumeTextArea.setColumns(20);
        resumeTextArea.setRows(5);
        resumeScrollPane.setViewportView(resumeTextArea);

        GroupLayout resumePanelLayout = new GroupLayout(resumePanel);
        resumePanel.setLayout(resumePanelLayout);
        resumePanelLayout.setHorizontalGroup(
            resumePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(resumePanelLayout.createSequentialGroup()
                .addGroup(resumePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(resumePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(resumeScrollPane, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                    .addGroup(resumePanelLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(applyButton)
                        .addGap(18, 18, 18)
                        .addComponent(declineButton)))
                .addContainerGap())
        );
        resumePanelLayout.setVerticalGroup(
            resumePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(resumePanelLayout.createSequentialGroup()
                .addComponent(resumeScrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(resumePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(declineButton))
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(resumePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(evalMatricsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(expStategiesPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addComponent(settingsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(settingsPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(expStategiesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(evalMatricsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resumePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(189, Short.MAX_VALUE))
        );
		    }                      
	
}