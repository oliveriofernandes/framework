package view.tests;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import core.ClassicMatrix;
import core.MatrixFactory;
import enums.TypeMatrix;

public class DualAxisDemo5 {

	private static final long serialVersionUID = 1L;
//	final JFreeChart chart ;
	final ChartPanel chartPanel ;
	 CategoryDataset dataset1;
	 CategoryDataset dataset2;

	public DualAxisDemo5(final String title) {

		
	//	 super(title);
	        dataset1 = createDataset1();
	        dataset2 = createDataset2();
	        chartPanel = createChartPanel(dataset1, dataset2);
	      //  chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	 //       setContentPane(chartPanel);
		
		
//        super(title);
//        dataset1 = createDataset1();
//        dataset2 = createDataset2();
//        chart = createChart(dataset1, dataset2);
//        chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
//        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return  The dataset.
     */
    private static CategoryDataset createDataset1() {

        // row keys...
        final String series1 = "Series 1";
        final String series2 = "Dummy 1";

        // column keys...
        final String category1 = "Category 1";
        final String category2 = "Category 2";
        final String category3 = "Category 3";
        final String category4 = "Category 4";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, series1, category1);
        dataset.addValue(4.0, series1, category2);
        dataset.addValue(3.0, series1, category3);
        dataset.addValue(5.0, series1, category4);

        dataset.addValue(null, series2, category1);
        dataset.addValue(null, series2, category2);
        dataset.addValue(null, series2, category3);
        dataset.addValue(null, series2, category4);

        return dataset;

    }

    /**
     * Creates a sample dataset.
     *
     * @return  The dataset.
     */
    private static CategoryDataset createDataset2() {

        // row keys...
        final String series1 = "Dummy 2";
        final String series2 = "Series 2";

        // column keys...
        final String category1 = "Category 1";
        final String category2 = "Category 2";
        final String category3 = "Category 3";
        final String category4 = "Category 4";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(null, series1, category1);
        dataset.addValue(null, series1, category2);
        dataset.addValue(null, series1, category3);
        dataset.addValue(null, series1, category4);

        dataset.addValue(75.0, series2, category1);
        dataset.addValue(87.0, series2, category2);
        dataset.addValue(96.0, series2, category3);
        dataset.addValue(68.0, series2, category4);

        return dataset;

    }

    /**
     * Creates a chart.
     * 
     * @param dataset1  the first dataset.
     * @param dataset2  the second dataset.
     * 
     * @return A chart.
     */

    private ChartPanel createChartPanel(final CategoryDataset dataset1, final CategoryDataset dataset2) {

        final CategoryAxis domainAxis = new CategoryAxis("Rates");
        final NumberAxis rangeAxis = new NumberAxis("Value");
        final BarRenderer renderer1 = new BarRenderer();
        final CategoryPlot plot = new CategoryPlot(dataset1, domainAxis, rangeAxis, renderer1) {
			private static final long serialVersionUID = 1L;

			/**	Override the getLegendItems() method to handle special case.
             *  @return the legend items.
             */
            public LegendItemCollection getLegendItems() {

                final LegendItemCollection result = new LegendItemCollection();

                final CategoryDataset data = getDataset();
                if (data != null) {
                    final CategoryItemRenderer r = getRenderer();
                    if (r != null) {
                        final LegendItem item = r.getLegendItem(0, 0);
                        result.add(item);
                    }
                }
                final CategoryDataset dset2 = getDataset(1);
                if (dset2 != null) {
                    final CategoryItemRenderer renderer2 = getRenderer(1);
                    if (renderer2 != null) {
                        final LegendItem item = renderer2.getLegendItem(1, 1);
                        result.add(item);
                    }
                }

                return result;

            }
            
        };
        
        final JFreeChart chart = new JFreeChart("Dual Axis Bar Chart", plot);
        chart.setBackgroundPaint(Color.white);
        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);
        final ValueAxis axis2 = new NumberAxis("Secondary");
        plot.setRangeAxis(1, axis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        final BarRenderer renderer2 = new BarRenderer();
        plot.setRenderer(1, renderer2);
        
        
        return new ChartPanel(chart);
    }
    
    public static void main(final String[] args) {

    	String path = new String("C:\\Users\\Oliverio\\workspace\\Framework\\data\\matrix.txt");
    	ClassicMatrix matrix = (ClassicMatrix) MatrixFactory.createMatrix(TypeMatrix.CLASSIC, path);
    			
    			DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
    			DefaultCategoryDataset dataSet2 = new DefaultCategoryDataset();
    			Map<String, Integer> axisProduct = new HashMap<String, Integer>();

    			Map<Integer, Double> rates;

    			for (Integer usr : matrix.getSetUsers()) {
    				rates = matrix.getUserRatings(usr);
    				for (Map.Entry<Integer, Double> entry : rates.entrySet()) {
    					if (!axisProduct.containsKey(String.valueOf(entry.getValue()))) {
    						axisProduct.put(String.valueOf(entry.getValue()), new Integer(1));
    					} 
    					else {
    						axisProduct.put(String.valueOf(entry.getValue()), axisProduct.get(String.valueOf(entry.getValue())) + 1 );
    					}

    				}
    			}
    			
    			for (Map.Entry<String, Integer> entry : axisProduct.entrySet()) {
    				dataSet.addValue(entry.getValue(), "Item", entry.getKey());
    				dataSet2.addValue(entry.getValue(), "User", entry.getKey());
    			System.out.println("bla ble ble");
    			}

    	
    	
    	
    	final DualAxisDemo5 demo = new DualAxisDemo5("Teste");

    	JPanel jPanel = new JPanel();
    	
    	jPanel.add((demo.createChartPanel(DualAxisDemo5.createDataset1(),DualAxisDemo5.createDataset2())));

    	JFrame jFrame = new JFrame("Teste");
    	jFrame.add(jPanel);
    	jFrame.setVisible(true);
    	jFrame.pack();
    	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }

}