package core;

import java.util.List;
/**
 * @author Oliverio
 */
public class SplittedMatrix {
	
	private static List<Double> trainDataSet;
	private static List<Double> testDataSer;
	
	public static List<Double> getTrainDataSet() {
		return trainDataSet;
	}
	public static void setTrainDataSet(List<Double> trainDataSet) {
		SplittedMatrix.trainDataSet = trainDataSet;
	}
	public static List<Double> getTestDataSer() {
		return testDataSer;
	}
	public static void setTestDataSer(List<Double> testDataSer) {
		SplittedMatrix.testDataSer = testDataSer;
	}
}
