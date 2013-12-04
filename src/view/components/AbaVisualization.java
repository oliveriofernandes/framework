package view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.thoughtworks.xstream.XStream;

import control.ContainerValues;
import control.ListClasses;
import control.StorageSettings;

public class AbaVisualization extends JPanel {

	private static final long serialVersionUID = 1L;
	private javax.swing.JButton runButton;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JButton saveButton;

	public AbaVisualization() {
		initComponents();
	}

	private void initComponents() {

		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		saveButton = new JButton("Save Project");
		runButton = new JButton("Run");

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Visualization"));

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGap(0, 670, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGap(0, 285, Short.MAX_VALUE));

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				XStream xstream = new XStream();
				xstream.alias("storageSettings", StorageSettings.class);
				String xml = xstream.toXML(StorageSettings.getInstance());

				JFileChooser fileChooser = new JFileChooser(".");
				int returned = fileChooser.showSaveDialog(null);
				if (returned == JFileChooser.APPROVE_OPTION) {

					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					try {
						OutputStream outputStream = new FileOutputStream(file);
						OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
						BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
						bufferedWriter.write(xml);
						bufferedWriter.close();
						JOptionPane.showMessageDialog(null, "successfully saved!");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				StorageSettings storage = StorageSettings.getInstance();

				Class clazz = ListClasses.getClassByPresentedAnnotation(storage.experimentStrategy, "experimentsStrategies");

				ContainerValues container = ContainerValues.getInstance();
				try {
					container.run(StorageSettings.getInstance());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(runButton).addComponent(saveButton))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(20, 20, 20)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(22,
																										22,
																										22)
																								.addComponent(
																										runButton)
																								.addGap(18,
																										18,
																										18)
																								.addComponent(
																										saveButton))
																				.addComponent(
																						jPanel2,
																						GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		jPanel1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(62, Short.MAX_VALUE)));

	}
}