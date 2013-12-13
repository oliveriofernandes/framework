package view.tests;

import java.awt.Button;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import core.ClassicMatrix;
import core.MatrixFactory;
import enums.TypeMatrix;

public class TestGrafico {

	public ChartPanel mountGraphic() {

		String path = new String(
				"C:\\Users\\Oliverio\\workspace\\Framework\\data\\matrix.txt");
		ClassicMatrix matrix = (ClassicMatrix) MatrixFactory.createMatrix(
				TypeMatrix.CLASSIC, path);

		Set<Double> setOfValues = new HashSet<Double>();

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		Map<String, Integer> axisUser = new HashMap<String, Integer>();

		Map<String, Integer> axisProduct = new HashMap<String, Integer>();

		Map<Integer, Double> rates;

		/*
		 * USER: OK PRODUCT: MAYBE
		 */
		for (Integer usr : matrix.getSetUsers()) {
			setOfValues.clear();
			rates = matrix.getUserRatings(usr);
			for (Map.Entry<Integer, Double> entry : rates.entrySet())
				setOfValues.add(entry.getValue());
			for (Double value : setOfValues) {
				if (!axisUser.containsKey(value.toString()))
					axisUser.put(value.toString(), new Integer(1));
				else
					axisUser.put(value.toString(), axisUser.get(value
							.toString()) + 1);
			}
		}

		for (Integer usr : matrix.getSetUsers()) {
			setOfValues.clear();
			rates = matrix.getUserRatings(usr);
			for (Map.Entry<Integer, Double> entry : rates.entrySet()) {

				if (!axisProduct.containsKey(String.valueOf(entry.getValue()))) {
					axisProduct.put(String.valueOf(entry.getValue()),
							new Integer(1));
				} else {
					axisProduct.put(String.valueOf(entry.getValue()),
							axisProduct.get(String.valueOf(entry.getValue())) + 1);
				}

			}
		}

		for (Map.Entry<String, Integer> entry : axisProduct.entrySet())
			dataSet.addValue(entry.getValue(), "Item", entry.getKey());

		for (Map.Entry<String, Integer> entry : axisUser.entrySet())
			dataSet.addValue(entry.getValue(), "User", entry.getKey());

		JFreeChart chart = ChartFactory.createBarChart3D(
				"Distribuition of rates" + "", "Rates", "Quantity", dataSet,
				PlotOrientation.VERTICAL, true, true, false);

		// ChartPanel jPanel = new ChartPanel(chart);

		// chart.setBackgroundPaint(Color.WHITE);
		// (new Color(0xEE, 0xEE, 0xFF));
		return new ChartPanel(chart);// jPanel;

	}

	public ChartPanel mountGraphic(String strCommand) {
		String path = new String(
				"C:\\Users\\Oliverio\\workspace\\Framework\\data\\matrix.txt");
		ClassicMatrix matrix = (ClassicMatrix) MatrixFactory.createMatrix(
				TypeMatrix.CLASSIC, path);

		Set<Double> setOfValues = new HashSet<Double>();
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		Map<String, Integer> axisUser = new HashMap<String, Integer>();
		Map<String, Integer> axisProduct = new HashMap<String, Integer>();
		Map<Integer, Double> rates;

		List<Integer> users = matrix.getPercentRandomUsers(Double.parseDouble(strCommand));

		/*
		 * USER: OK PRODUCT: MAYBE
		 */
		for (Integer usr : users) {
			setOfValues.clear();
			rates = matrix.getUserRatings(usr);
			for (Map.Entry<Integer, Double> entry : rates.entrySet())
				setOfValues.add(entry.getValue());
			for (Double value : setOfValues) {
				if (!axisUser.containsKey(value.toString()))
					axisUser.put(value.toString(), new Integer(1));
				else
					axisUser.put(value.toString(), axisUser.get(value
							.toString()) + 1);
			}
		}

		for (Integer usr : users) {
			setOfValues.clear();
			rates = matrix.getUserRatings(usr);
			for (Map.Entry<Integer, Double> entry : rates.entrySet()) {

				if (!axisProduct.containsKey(String.valueOf(entry.getValue()))) {
					axisProduct.put(String.valueOf(entry.getValue()),
							new Integer(1));
				} else {
					axisProduct.put(String.valueOf(entry.getValue()),
							axisProduct.get(String.valueOf(entry.getValue())) + 1);
				}

			}
		}

		for (Map.Entry<String, Integer> entry : axisProduct.entrySet())
			dataSet.addValue(entry.getValue(), "Item", entry.getKey());

		for (Map.Entry<String, Integer> entry : axisUser.entrySet())
			dataSet.addValue(entry.getValue(), "User", entry.getKey());

		JFreeChart chart = ChartFactory.createBarChart3D(
				"Distribuition of rates" + "", "Rates", "Quantity", dataSet,
				PlotOrientation.VERTICAL, true, true, false);

		// for (User user : users) {
		// System.out.println("user id = " + user.getId());
		// }
		//		

		return new ChartPanel(chart);

	}

	public void mountGraficPercent(String percentTrain, String percentTest) {

		String path = new String(
				"C:\\Users\\Oliverio\\workspace\\Framework\\data\\matrix.txt");
		ClassicMatrix matrix = (ClassicMatrix) MatrixFactory.createMatrix(
				TypeMatrix.CLASSIC, path);

		Map<Integer, Double> rates = new HashMap<Integer, Double>();

		for (Integer usr : matrix.getSetUsers()) {
			rates = matrix.getUserRatings(usr);
			rates.putAll(rates);

		}
	}

	public ChartPanel mountGraphicTest() {

		String path = new String(
				"C:\\Users\\Oliverio\\workspace\\Framework\\data\\matrix.txt");
		ClassicMatrix matrix = (ClassicMatrix) MatrixFactory.createMatrix(
				TypeMatrix.CLASSIC, path);

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		Map<String, Integer> valuesPloted = new HashMap<String, Integer>();

		Map<Integer, Double> rates;

		for (Integer usr : matrix.getSetUsers()) {
			rates = matrix.getUserRatings(usr);
			for (Map.Entry<Integer, Double> entry : rates.entrySet()) {
				if (!valuesPloted.containsKey(String.valueOf(entry.getValue())))
					valuesPloted.put(String.valueOf(entry.getValue()),
							new Integer(1));
				else
					valuesPloted.put(String.valueOf(entry.getValue()),
							valuesPloted.get(String.valueOf(entry.getValue())) + 1);
			}
		}

		for (Map.Entry<String, Integer> entry : valuesPloted.entrySet())
			dataSet.addValue(entry.getValue(), "Rates", entry.getKey());

		JFreeChart chart = ChartFactory.createBarChart3D(
				"Distribuition of rates", "Label rates", "Number of Rates",
				dataSet, PlotOrientation.VERTICAL, true, true, false);
		return new ChartPanel(chart);

	}

	public static void main(String[] args) {

		TestGrafico t = new TestGrafico();
		JPanel jPanel = t.mountGraphicTest();

		JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
		j.add(new Button("sdfgd"));
		j.add(jPanel);
		j.pack();
		j.setBounds(new Rectangle(110, 110, 800, 600));

		// Set<Integer> set = new HashSet<Integer>();
		// set.clear();
		// set.add(new Integer(5));
		// set.add(new Integer(4));
		// set.add(new Integer(3));
		// set.add(new Integer(2));
		// set.add(new Integer(1));
		// set.add(new Integer(5));
		// set.add(new Integer(5));
		// set.add(new Integer(5));
		// set.add(new Integer(5));
		// set.add(new Integer(5));

		//		
		// System.out.println("The size: " + set.size());
		// for (Integer integer : set) {
		//	
		// System.out.println(integer.toString());
		// }
		//		
		//		
		//		
		// System.out.println("The size: " + set.size());
		// set.clear();
		// System.out.println("The size: " + set.size());

		// Random random = new Random(System.currentTimeMillis());

		// System.out.println(random.nextInt(10));

	}
}
