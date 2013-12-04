package view.graphics;

import java.awt.Color;
import java.awt.GradientPaint;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import control.Comparision;
import core.AbstractMatrix;

public class GraphicGenerator {

	public JPanel mountBarrGraph(AbstractMatrix matrix) {

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		Map<String, Integer> valuesPloted = new TreeMap<String, Integer>();

		Map<Integer, Double> tuples;

		Set<Integer> users = matrix.getSetUsers();

		for (Integer usr : users) {
			tuples = matrix.getUserRatings(usr);
			for (Map.Entry<Integer, Double> entry : tuples.entrySet()) {
				if (!valuesPloted.containsKey(String.valueOf(entry.getValue())))
					valuesPloted.put(String.valueOf(entry.getValue()), new Integer(1));
				else
					valuesPloted.put(String.valueOf(entry.getValue()),
							valuesPloted.get(String.valueOf(entry.getValue())) + 1);
			}
		}

		for (Map.Entry<String, Integer> entry : valuesPloted.entrySet())
			dataSet.addValue(entry.getValue(), "Ratings", entry.getKey());

		JFreeChart chart = ChartFactory.createBarChart3D("Distribution of ratings",
				"Label ratings", "Number of Rating", dataSet, PlotOrientation.VERTICAL, true, true,
				false);

		return new ChartPanel(chart);
	}

	public JPanel mountBarrGraphByPercent(List<Double>[] listRates, int totalOfRates) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		Map<String, Double> testValuesPloted = new TreeMap<String, Double>();

		for (Double rating : listRates[0]) {
			if (!testValuesPloted.containsKey(String.valueOf(rating)))
				testValuesPloted.put(String.valueOf(rating), new Double(1));
			else
				testValuesPloted.put(String.valueOf(rating),
						testValuesPloted.get(String.valueOf(rating)) + 1);
		}

		for (Map.Entry<String, Double> entry : testValuesPloted.entrySet()) {
			entry.setValue((entry.getValue() * 100.0) / totalOfRates); // calculate percentage and set value
			dataSet.addValue(entry.getValue(), "Test set", entry.getKey());

		}

		testValuesPloted.clear();

		for (Double rating : listRates[1]) {
			if (!testValuesPloted.containsKey(String.valueOf(rating)))
				testValuesPloted.put(String.valueOf(rating), new Double(1));
			else
				testValuesPloted.put(String.valueOf(rating),
						testValuesPloted.get(String.valueOf(rating)) + 1);
		}

		for (Map.Entry<String, Double> entry : testValuesPloted.entrySet()) {
			entry.setValue((entry.getValue() * 100.0) / totalOfRates); // calculate percentage and set value
			dataSet.addValue(entry.getValue(), "Train set", entry.getKey());
		}

		JFreeChart chart = ChartFactory.createBarChart("Distribuition between sets ",
				"Label rating", "Percent by total (%)", dataSet, PlotOrientation.VERTICAL, true,
				true, false);

		// set the background color for the chart...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setDomainGridlinePaint(Color.white);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.white);

		// set the range axis to display integers only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setLowerBound(0);

		// disable bar outlines...
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// set up gradient paints for series...
		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.CYAN, 0.0f, 0.0f, new Color(0, 0,
				64));
		GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, new Color(0, 64,
				0));
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);

		return new ChartPanel(chart);

	}

	public JPanel mountBarrGraph(Map<Comparision, Double> ds) {

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		Map<String, Double> valuesPloted = new TreeMap<String, Double>();

		for (Map.Entry<Comparision, Double> entry : ds.entrySet()) {

			valuesPloted.put(entry.getKey().getAlgorithtm() + " " + entry.getKey().getSimilarity(),
					entry.getValue());
		}

		for (Map.Entry<String, Double> entry : valuesPloted.entrySet())
			dataSet.addValue(entry.getValue(), "Tests", entry.getKey());

		JFreeChart chart = ChartFactory.createBarChart3D("Result", "Comparisions", "MAE", dataSet,
				PlotOrientation.VERTICAL, true, true, false);

		JFrame j = new JFrame();
		j.add(new ChartPanel(chart));
		j.pack();
		j.setVisible(true);

		return new ChartPanel(chart);
	}

}
