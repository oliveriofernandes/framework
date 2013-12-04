package view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import control.StorageSettings;

public class KValue extends javax.swing.JFrame {
    
	private static final long serialVersionUID = 1L;
	
	
	 private javax.swing.JLabel kValueLabel;
	    private javax.swing.JPanel kValuePanel;
	    private javax.swing.JTextField kValueTextField;
	    private javax.swing.JButton okButton;
	
	public KValue() {
        initComponents();
        okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				StorageSettings storageSettings = StorageSettings.getInstance();
				storageSettings.kValue = Integer.parseInt(kValueTextField.getText());
			
			}
		});
	}
	
    @SuppressWarnings("unchecked")
    private void initComponents() {

        kValuePanel = new javax.swing.JPanel();
        kValueLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton("OK");
        kValueTextField = new javax.swing.JTextField();

        kValuePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("K Value"));

        this.setLocation(525, 100);
        kValueLabel.setText("Enter k value:");

        kValueTextField.setColumns(3);
        

        javax.swing.GroupLayout kValuePanelLayout = new javax.swing.GroupLayout(kValuePanel);
        kValuePanel.setLayout(kValuePanelLayout);
        kValuePanelLayout.setHorizontalGroup(
            kValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kValuePanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(kValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kValuePanelLayout.createSequentialGroup()
                        .addComponent(kValueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kValuePanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(okButton)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        kValuePanelLayout.setVerticalGroup(
            kValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kValuePanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(kValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kValueLabel)
                    .addComponent(kValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(okButton)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kValuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kValuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    
        public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	  
                new KValue().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
            }
        });
    }

        
   

}
