package view.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import util.Visibility;
import control.Comparision;
import control.ListClasses;

public class InnerTableContent {

	public JPanel mountTabbleContent(final JTabbedPane innerTabbedPane, final JTable confirmTable,
			Comparision comparision) {

		JPanel panelTabContent = new JPanel();
		final JComboBox algorithmComboBox = new JComboBox();
		final JComboBox similarityComboBox = new JComboBox();
		JLabel algorithmLabel = new JLabel("Algorithm");
		JLabel similarityLabel = new JLabel("Similarity");

		List<String> similarityList = new ArrayList<String>();
		List<String> algorithmList = new ArrayList<String>();
		boolean addBlankValue;
		
		
		
		if (comparision == null) {
			similarityList.add("");
			algorithmList.add("");
			addBlankValue = true;
		} else {
			addBlankValue = false;
			similarityList.add(comparision.getSimilarity());
			algorithmList.add(comparision.getAlgorithtm());

			String similarityAnnotationName = ((Visibility) comparision.getClsSimilarity()
					.getAnnotation(Visibility.class)).name();
			String algorimtAnotationName = ((Visibility) comparision.getClsAlgorithtm()
					.getAnnotation(Visibility.class)).name();
			

			try {
				similarityComboBox.setModel(new DefaultComboBoxModel(ListClasses.getClassNamesByPkg("similarities", true, addBlankValue)));
				algorithmComboBox.setModel(new DefaultComboBoxModel(ListClasses.getClassNamesByPkg("predictions", true, addBlankValue)));
			} catch (ClassNotFoundException e) {
				System.out.println(e.getStackTrace());
			} catch (IOException e) {
				System.out.println(e.getStackTrace());
			}
			
		}

		try {

			similarityComboBox.setModel(new DefaultComboBoxModel(ListClasses.getClassNamesByPkg("similarities", true, addBlankValue)));
			algorithmComboBox.setModel(new DefaultComboBoxModel(ListClasses.getClassNamesByPkg("predictions", true, addBlankValue)));

		} catch (ClassNotFoundException e) {
			System.out.println(e.getStackTrace());
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}

		algorithmComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				confirmTable.setValueAt(algorithmComboBox.getSelectedItem().toString(),
						innerTabbedPane.getSelectedIndex(), 1);
			}
		});

		similarityComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				confirmTable.setValueAt(similarityComboBox.getSelectedItem().toString(),
						innerTabbedPane.getSelectedIndex(), 2);
			}
		});

		javax.swing.GroupLayout tabLayout = new GroupLayout(panelTabContent);
		panelTabContent.setLayout(tabLayout);
		tabLayout.setHorizontalGroup(tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						tabLayout
								.createSequentialGroup()
								.addGap(39, 39, 39)
								.addComponent(algorithmLabel, GroupLayout.DEFAULT_SIZE, 64,
										Short.MAX_VALUE)
								.addGap(18, 18, 18)
								.addComponent(algorithmComboBox, GroupLayout.PREFERRED_SIZE, 202,
										GroupLayout.PREFERRED_SIZE)
								.addGap(120, 120, 120)
								.addComponent(similarityLabel, GroupLayout.DEFAULT_SIZE, 60,
										Short.MAX_VALUE)
								.addGap(18, 18, 18)
								.addComponent(similarityComboBox, GroupLayout.PREFERRED_SIZE, 155,
										GroupLayout.PREFERRED_SIZE).addGap(100, 100, 100)));
		tabLayout
				.setVerticalGroup(tabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								tabLayout
										.createSequentialGroup()
										.addGap(31, 31, 31)
										.addGroup(
												tabLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(algorithmComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(algorithmLabel)
														.addComponent(similarityComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(similarityLabel))
										.addContainerGap(67, Short.MAX_VALUE)));

		return panelTabContent;
	}
}
