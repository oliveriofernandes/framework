package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.Field;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.LineBorder;

import util.SetText;
import view.utils.AllowedCharsPlainDocument;
import view.utils.ChooseAlgTextArea;
import view.utils.ColorFocusTextEdit;
import view.utils.CompletePercentage;
import view.utils.PopulateConfigurationFields;

import com.thoughtworks.xstream.XStream;

import control.Comparision;
import control.ServiceHandler;
import control.StorageSettings;
import core.AbstractMatrix;
import enums.TestOptions;
import enums.TypeMatrix;

public class SelectDataSet extends JPanel {

	private static final long serialVersionUID = 1L;
	private AbstractMatrix matrix;
	private JPanel chartRatingsDistributionPanel, chartOptionPanel, viewDataSetPanel, 
	datasetPanel, testOptionPanel, settingsOptionPanel, projectPanel;
    
	private JLabel chooseMatrixTypelabel, testOptionLabel, trainPercentLabel, testPercentLabel;
    private JButton openFileButton, applyButton;
    private JRadioButton startNewProjectRadio, loadProjectRadio; 
    private ButtonGroup projectButtonGroup;
    private JTextField trainPercentTextField, testPercentTextField; 
    private JComboBox<?> chooseMatrixTypeComboBox, testOptionComboBox ;
    private StorageSettings storageSettings;
    private ChooseAlgTextArea settingsTextArea;
    public SelectDataSet(){
    	mountAba();
    }

    
    
    private void mountAba() {
    	initComponents();
    	setComponents();
    	configurelisteners();
        mountSettingsOptionPanel ();
        mountChartOptionPanelLayout();
        mountViewDataSetPanel();
        mountTestOptionPanel(); 
        mountProjectPanelLayout();
    }
    
    private void mountSettingsOptionPanel() {
    		
    	GroupLayout settingsOptionPanelLayout = new GroupLayout(settingsOptionPanel);
        settingsOptionPanel.setLayout(settingsOptionPanelLayout);
        settingsOptionPanelLayout.setHorizontalGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(settingsOptionPanelLayout.createSequentialGroup()
        .addContainerGap().addGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING) )
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false) )
        .addContainerGap(45, Short.MAX_VALUE)));
                        
        settingsOptionPanelLayout.setVerticalGroup(
        settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(settingsOptionPanelLayout.createSequentialGroup()
        .addContainerGap().addGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(settingsOptionPanelLayout.createSequentialGroup()
        .addGap(29, 29, 29).addGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)))
        .addGroup(settingsOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)))
        .addContainerGap(52, Short.MAX_VALUE)));
        
        GroupLayout chartRatingsDistributionPanelLayout = new GroupLayout(chartRatingsDistributionPanel);
        chartRatingsDistributionPanel.setLayout(chartRatingsDistributionPanelLayout);
        chartRatingsDistributionPanelLayout.setHorizontalGroup(
        chartRatingsDistributionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 641, Short.MAX_VALUE));
        chartRatingsDistributionPanelLayout.setVerticalGroup(
        chartRatingsDistributionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 205, Short.MAX_VALUE));
    }
    
    private void mountTestOptionPanel (){
    	GroupLayout configureTestOptionPanelLayout = new GroupLayout(testOptionPanel);
    	testOptionPanel.setLayout(configureTestOptionPanelLayout);
    	configureTestOptionPanelLayout.setHorizontalGroup(
        configureTestOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(configureTestOptionPanelLayout.createSequentialGroup()
        .addGroup(configureTestOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(configureTestOptionPanelLayout.createSequentialGroup()
        .addGap(99, 99, 99).addComponent(applyButton))
        .addGroup(configureTestOptionPanelLayout.createSequentialGroup()
        .addGap(56, 56, 56).addGroup(configureTestOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(settingsOptionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(configureTestOptionPanelLayout.createSequentialGroup()
                            .addComponent(testOptionLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(testOptionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
            .addContainerGap(58, Short.MAX_VALUE)));
    
    	configureTestOptionPanelLayout.setVerticalGroup(
        configureTestOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(configureTestOptionPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(configureTestOptionPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(testOptionLabel)
                .addComponent(testOptionComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
            .addComponent(settingsOptionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18).addComponent(applyButton).addContainerGap()));
 
    	TestOptions a = TestOptions.byValue(testOptionComboBox.getSelectedItem().toString());
    	
    	InnerTestOptionsPanel.mount(settingsOptionPanel, trainPercentLabel, 
				testPercentLabel, testPercentTextField, trainPercentTextField,a);
    	
    }

    private void mountViewDataSetPanel (){
    	GroupLayout viewDataSetPanelLayout = new GroupLayout(viewDataSetPanel);
    	viewDataSetPanel.setLayout(viewDataSetPanelLayout);
    	viewDataSetPanelLayout.setHorizontalGroup(
    	viewDataSetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    	.addGroup(viewDataSetPanelLayout.createSequentialGroup().addContainerGap()
        .addGroup(viewDataSetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(chartRatingsDistributionPanel, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        .addComponent(chartOptionPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE))
        .addContainerGap()));
    	
    	viewDataSetPanelLayout.setVerticalGroup(
    	viewDataSetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    	.addGroup(viewDataSetPanelLayout.createSequentialGroup()
        .addComponent(chartRatingsDistributionPanel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18).addComponent(chartOptionPanel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    	datasetPanel.setBorder(BorderFactory.createTitledBorder("Dataset"));
    	chooseMatrixTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(TypeMatrix.getValuesAsArray()));

    	GroupLayout datasetPanelLayout = new GroupLayout(datasetPanel);
    	datasetPanel.setLayout(datasetPanelLayout);
    	datasetPanelLayout.setHorizontalGroup(
    	datasetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(datasetPanelLayout.createSequentialGroup()
	        .addGroup(datasetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(datasetPanelLayout.createSequentialGroup()
	        .addContainerGap().addComponent(chooseMatrixTypelabel)
	        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	        .addComponent(chooseMatrixTypeComboBox, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
	        .addGroup(datasetPanelLayout.createSequentialGroup()
	        .addGap(93, 93, 93).addComponent(openFileButton))).addContainerGap(72, Short.MAX_VALUE)));
 
    	datasetPanelLayout.setVerticalGroup(
        datasetPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(datasetPanelLayout.createSequentialGroup()
	        .addContainerGap().addGroup(datasetPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		        .addComponent(chooseMatrixTypelabel)
		        .addComponent(chooseMatrixTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
		        .addComponent(openFileButton)
		        .addContainerGap()));
}

	private void mountChartOptionPanelLayout() {
		GroupLayout chartOptionPanelLayout = new GroupLayout(chartOptionPanel);
		chartOptionPanel.setLayout(chartOptionPanelLayout);
		chartOptionPanelLayout.setHorizontalGroup(chartOptionPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
						641, Short.MAX_VALUE));
		
		chartOptionPanelLayout.setVerticalGroup(chartOptionPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
						205, Short.MAX_VALUE));
	}	


	private void mountProjectPanelLayout(){
		 projectPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Project"));
	
	     projectButtonGroup.add(startNewProjectRadio);
	     startNewProjectRadio.setSelected(true);
	     startNewProjectRadio.setText("Start a new project");
	
	     projectButtonGroup.add(loadProjectRadio);
	     loadProjectRadio.setText("Load an existing project");
	
	     GroupLayout projectPanelLayout = new GroupLayout(projectPanel);
	     projectPanel.setLayout(projectPanelLayout);
	     projectPanelLayout.setHorizontalGroup(projectPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    		 .addGroup(projectPanelLayout.createSequentialGroup().addContainerGap()
	        		 .addGroup(projectPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                 .addComponent(startNewProjectRadio)
	                 .addComponent(loadProjectRadio))
	                 .addContainerGap(134, Short.MAX_VALUE))
	     );
	     
	     projectPanelLayout.setVerticalGroup(projectPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	     .addGroup(projectPanelLayout.createSequentialGroup()
	    		 .addContainerGap().addComponent(startNewProjectRadio)
	             .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	             .addComponent(loadProjectRadio).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	     );
	
     GroupLayout layout = new GroupLayout(this);
     this.setLayout(layout);
     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    		 .addGroup(layout.createSequentialGroup()
    		 .addContainerGap()
	         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	        		 .addComponent(projectPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                 .addComponent(datasetPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                 .addComponent(testOptionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                 .addComponent(viewDataSetPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                 .addContainerGap())
	);
	
     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
	        .addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	        .addGroup(layout.createSequentialGroup()
	        		.addComponent(projectPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(datasetPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(testOptionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addComponent(viewDataSetPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(51, Short.MAX_VALUE)));
	}

	private void loadDataset() {
		
		ServiceHandler serviceHandler = new ServiceHandler();
		JFileChooser fileChooser = new JFileChooser(".");
		int returned = fileChooser.showOpenDialog(null);
		File dataSet;
		
		if (returned == JFileChooser.APPROVE_OPTION) {
			dataSet = fileChooser.getSelectedFile();
			matrix = serviceHandler.loadMatrix(dataSet, chooseMatrixTypeComboBox.getSelectedItem().toString().toUpperCase());
			chartOptionPanel.removeAll();
    		chartOptionPanel.repaint();
    		storageSettings.setDataSetPath(fileChooser.getSelectedFile().getAbsolutePath());
		}
		
		if (returned == JFileChooser.CANCEL_OPTION){
			if (storageSettings.percentTest != null)
				testPercentTextField.setText(storageSettings.percentTest); 
			
			if (storageSettings.percentTrain != null)
				trainPercentTextField.setText(storageSettings.percentTrain);
		}
	}
    
	private void mountChart(AbstractMatrix matrix){
		ServiceHandler serviceHandler = new ServiceHandler();
		JPanel panelChartBar = new JPanel();
		panelChartBar = serviceHandler.plotBarrGraph(matrix);
		panelChartBar.setBorder(new LineBorder(Color.BLACK));
		
		chartRatingsDistributionPanel.add(panelChartBar);
		
			GroupLayout panelChartProductsLayout = new GroupLayout(chartRatingsDistributionPanel);
			chartRatingsDistributionPanel.setLayout(panelChartProductsLayout);
			panelChartProductsLayout.setHorizontalGroup(panelChartProductsLayout.createParallelGroup(GroupLayout.Alignment.LEADING,true)
					.addComponent(panelChartBar)
					.addGap(0, 439, Short.MAX_VALUE) );
        
			panelChartProductsLayout.setVerticalGroup(panelChartProductsLayout.createParallelGroup(GroupLayout.Alignment.LEADING,true)
					.addComponent(panelChartBar)
					.addGap(0, 194, Short.MAX_VALUE) );
    }
    
	private void fillTextArea(){
		SetText setText;
		Thread thread;
		setText = new SetText(settingsTextArea,1);
        thread = new Thread(setText);
        thread.start();
    }
	
	
	
	private void openFileChoose() {
		JFileChooser fileChooser = new JFileChooser(".");
		int returned = fileChooser.showOpenDialog(null);
		if (returned == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
				
				XStream xStream = new XStream();
				xStream.alias("storageSettings", StorageSettings.class);
				xStream.alias("comparision", Comparision.class);
				
				Object obj = (StorageSettings)xStream.fromXML(file);
				
				try {
				
					for (Field field : storageSettings.getClass().getDeclaredFields()) {  
						field.setAccessible(true);
						for (Field fieldObj : obj.getClass().getDeclaredFields()) {
				         fieldObj.setAccessible(true);  
				         if (field.equals(fieldObj))
							field.set(storageSettings,fieldObj.get(obj));
				        }
				    }
				              
				}catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}   
			
			storageSettings.setIsNewProject(false);
				
			ServiceHandler serviceHandler = new ServiceHandler();
			
			if (storageSettings.matrixType != null){
				chooseMatrixTypeComboBox.setSelectedItem(storageSettings.matrixType);	
				if (storageSettings.dataSetPath!= null){
					File dataSet = new File(storageSettings.dataSetPath);
					
					matrix = serviceHandler.loadMatrix(dataSet, chooseMatrixTypeComboBox.getSelectedItem().toString().toUpperCase());
					chartOptionPanel.removeAll();
					chartOptionPanel.repaint();
					
					if (matrix !=null){
		        		mountChart(matrix);
		        		fillTextArea();
				}
			}
			
			if (storageSettings.testOption!=null){
				testOptionComboBox.setSelectedItem(storageSettings.testOption);
				testOptionComboBox.setEnabled(true);
			
				if (storageSettings.testOption.equals("Totaly randon")){
					if (storageSettings.percentTrain!= null){
						trainPercentTextField.setText(storageSettings.percentTrain);
						trainPercentTextField.setEditable(true);
					}
					
					if (storageSettings.percentTest != null){
						testPercentTextField.setText(storageSettings.percentTest);
						testPercentTextField.setEditable(true);
					}
					double percentageTestSet = Double.parseDouble(testPercentTextField.getText());
    				JPanel panelChartBar =  serviceHandler.plotPercentBarrGraph(matrix,percentageTestSet );
    				GroupLayout panelChartUsersLayout = new GroupLayout(chartOptionPanel);
                	SetText setText;
            		Thread thread;
    				setText = new SetText(settingsTextArea,4);
    		  		thread = new Thread(setText);
    		  		thread.start();
    		   		panelChartBar.setBorder(new LineBorder(Color.BLACK));
		      		
    		   		chartOptionPanel.removeAll();
		      		chartOptionPanel.repaint();
    		
		      		chartOptionPanel.add(panelChartBar);
		      		chartOptionPanel.setLayout(panelChartUsersLayout);
		      		panelChartUsersLayout.setHorizontalGroup(panelChartUsersLayout.createParallelGroup(GroupLayout.Alignment.LEADING,true).addComponent(panelChartBar)
		      		.addGap(0, 439, Short.MAX_VALUE));
        
		      		panelChartUsersLayout.setVerticalGroup(panelChartUsersLayout.createParallelGroup(GroupLayout.Alignment.LEADING,true).addComponent(panelChartBar)
		      		.addGap(0, 194, Short.MAX_VALUE));
				}
			}
		}
			PopulateConfigurationFields.fillConfigurationFromProject(storageSettings);
		}
    	
	}
    	
    	private void openFileButtonAddListener (){
    	openFileButton.addActionListener(new ActionListener() {

        	@Override
			public void actionPerformed(ActionEvent e) {
		
        		applyButton.setEnabled(false);
                testOptionComboBox.setEnabled(false);
                
                trainPercentTextField.setEditable(false);
                testPercentTextField.setEditable(false);
                
                chartRatingsDistributionPanel.removeAll();
        		chartRatingsDistributionPanel.repaint();
        		
        		trainPercentTextField.setBackground(new Color(240,240,240));
                testPercentTextField.setBackground(new Color(240,240,240));
        		
        		loadDataset();
        		storageSettings.setMatrixType(chooseMatrixTypeComboBox.getSelectedItem().toString());
        		        		
        		
        		if (matrix !=null){
        		mountChart(matrix);
        		fillTextArea();
                testOptionComboBox.setEnabled(true);
                trainPercentTextField.setEditable(true);
                testPercentTextField.setEditable(true);
                trainPercentTextField.setBackground(Color.WHITE);
                testPercentTextField.setBackground(Color.WHITE);
                applyButton.setEnabled(true);
        		}
			}
			
		});
    	}
    	
    	private void applyButtonAddListener(){
    		applyButton.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent event) {
            	
            	GroupLayout panelChartUsersLayout = new GroupLayout(chartOptionPanel);
            	SetText setText;
        		Thread thread;
        		
            	if(event.getActionCommand()!=null){
            		ServiceHandler serviceHandler = new ServiceHandler();
            		
            		String selectedTestOption = testOptionComboBox.getSelectedItem().toString();
            		storageSettings.setTestOption(selectedTestOption);
            		storageSettings.setPercentTest(testPercentTextField.getText());
            		storageSettings.setPercentTrain(trainPercentTextField.getText());
            		
            		TestOptions testOptions = TestOptions.byValue(selectedTestOption);
            		chartOptionPanel.removeAll();
            		chartOptionPanel.repaint();
            		
            		if (!testOptions.validateEntry(selectedTestOption) )  {
            			try {	
            				Double.parseDouble(storageSettings.percentTest);
            				Double.parseDouble(storageSettings.percentTrain);
						} catch (NumberFormatException e) {
							
							JOptionPane.showMessageDialog(null, "You must select a test option and then fill the text fields! ",
							"Incomplete entry", JOptionPane.WARNING_MESSAGE);
							}
            		}
            		
            		else{
            			
            				double percentageTestSet = Double.parseDouble(testPercentTextField.getText());
            				JPanel panelChartBar =  serviceHandler.plotPercentBarrGraph(matrix,percentageTestSet );
            				
            				serviceHandler.splitData(matrix, percentageTestSet);
            				
            				setText = new SetText(settingsTextArea,4);
            		  		thread = new Thread(setText);
            		  		thread.start();
            		   		panelChartBar.setBorder(new LineBorder(Color.BLACK));
				      		
            		   		chartOptionPanel.removeAll();
				      		chartOptionPanel.repaint();
            		
				      		chartOptionPanel.add(panelChartBar);
				      		chartOptionPanel.setLayout(panelChartUsersLayout);
				      		panelChartUsersLayout.setHorizontalGroup(panelChartUsersLayout.createParallelGroup(GroupLayout.Alignment.LEADING,true).addComponent(panelChartBar)
				      		.addGap(0, 439, Short.MAX_VALUE));
		        
				      		panelChartUsersLayout.setVerticalGroup(panelChartUsersLayout.createParallelGroup(GroupLayout.Alignment.LEADING,true).addComponent(panelChartBar)
				      		.addGap(0, 194, Short.MAX_VALUE));
            			}
            		}
            }
        });
    	}
 
    	private void testOptionComboBoxAddListener(){
    		testOptionComboBox.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent event) {
    				String value = testOptionComboBox.getSelectedItem().toString();
    				TestOptions a = TestOptions.byValue(value);
    				applyButton.setEnabled(true);
    				settingsOptionPanel.removeAll();
    				settingsOptionPanel.repaint();
    				
    				InnerTestOptionsPanel.mount(settingsOptionPanel, trainPercentLabel, 
    						testPercentLabel, testPercentTextField, trainPercentTextField,a);			
    				}
    		});
    	}
    	
    	private void trainPercentTextFieldAddListener(){
    		trainPercentTextField.addKeyListener(new java.awt.event.KeyAdapter() {  
              	 public void keyTyped(java.awt.event.KeyEvent e) {  
              	    if (trainPercentTextField.getText().length() <= 4 - 1) {  
              	    	//let to leave  
              	    } else {  
              	       e.setKeyChar((char) KeyEvent.VK_CLEAR);  
              	    }
              	 }  
              	 });
    	}
    	
    	private void testPercentTextFieldAddListener(){
    		testPercentTextField.addKeyListener(new KeyAdapter() {  
              	 public void keyTyped(java.awt.event.KeyEvent e) {  
              	    if (testPercentTextField.getText().length() <= 4 - 1) {  
              	    //let to leave  
              	    } else {  
              	       e.setKeyChar((char) KeyEvent.VK_CLEAR);  
              	    }  
              	 }  
              	 });
    	}
    	
    	private void loadProjectRadioAddListener(){
        
        loadProjectRadio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {

				if (loadProjectRadio.isEnabled())
					openFileChoose();
			}
        });    
    	}
    	
    	public void initComponents(){
    		projectButtonGroup = new ButtonGroup();
            viewDataSetPanel = new JPanel();
            chartRatingsDistributionPanel = new JPanel();
            chartOptionPanel = new JPanel();
            datasetPanel = new JPanel();
            chooseMatrixTypeComboBox = new JComboBox();
            chooseMatrixTypelabel = new JLabel("Choose Matrix Type");
            openFileButton = new JButton("Open File");
            testOptionPanel = new JPanel();
            testOptionComboBox = new JComboBox();
            testOptionLabel = new JLabel("Test option");
            settingsOptionPanel = new JPanel();
            trainPercentTextField = new JTextField(new AllowedCharsPlainDocument("0123456789."), "", 4);
            trainPercentLabel = new JLabel();//new JLabel("Train percent:");
            testPercentTextField = new JTextField(new AllowedCharsPlainDocument("0123456789."), "", 4);
            testPercentLabel = new JLabel();//new JLabel("Test Percent:");
            applyButton = new JButton("Apply");
            projectPanel = new JPanel();
            startNewProjectRadio = new javax.swing.JRadioButton();
            loadProjectRadio = new JRadioButton();
            settingsTextArea = ChooseAlgTextArea.getInstance();
            storageSettings = StorageSettings.getInstance();
    	}
    	
    	public void setComponents(){
    		viewDataSetPanel.setBorder(BorderFactory.createTitledBorder("View Dataset"));
            applyButton.setEnabled(false);
            testOptionComboBox.setEnabled(false);
            trainPercentTextField.setEditable(false);
            testPercentTextField.setEditable(false);
            chartRatingsDistributionPanel.setBackground(Color.white);
            chartRatingsDistributionPanel.setPreferredSize(new Dimension(450, 200));
            chartOptionPanel.setBackground(java.awt.Color.white);
            chartOptionPanel.setPreferredSize(new java.awt.Dimension(450, 200));
            testPercentTextField.addFocusListener(new ColorFocusTextEdit());
            trainPercentTextField.addFocusListener(new ColorFocusTextEdit());
            testPercentTextField.addFocusListener(new CompletePercentage(trainPercentTextField));
            trainPercentTextField.addFocusListener(new CompletePercentage(testPercentTextField));
            testOptionPanel.setBorder(BorderFactory.createTitledBorder("Test options"));
            testOptionComboBox.setModel(new DefaultComboBoxModel(TestOptions.getValuesAsArray()));
            settingsOptionPanel.setBorder(BorderFactory.createEtchedBorder());
            trainPercentTextField.setColumns(3);
            testPercentTextField.setColumns(3);
    	}
    	
    	public void configurelisteners(){
    		openFileButtonAddListener ();
    		applyButtonAddListener();
            testOptionComboBoxAddListener();
            trainPercentTextFieldAddListener();
            testPercentTextFieldAddListener();
            loadProjectRadioAddListener();
    	}
}