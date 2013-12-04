package util;

public class Statistics {

	public static double aritmeticMean(double... values) {
		double sum = 0;

		for (double value : values)
			sum = sum + value;

		return sum / values.length;
	}

	public static double variance(double... list) {
		double mean = 0;
		double sumSquaredDifference = 0;
		
		mean = mean + Statistics.aritmeticMean(list);
		for (double value : list)
			sumSquaredDifference = sumSquaredDifference + ( Math.pow((value - mean), 2) );
		
		return sumSquaredDifference;
	}
	
	public static double covariance (double[] y, double[] x){
		double meanX = 0;
		double meanY = 0;
		
		meanX = meanX + (Statistics.aritmeticMean(x));
		meanY = meanY + (Statistics.aritmeticMean(y));
		
		double result = 0;

		if (x.length == y.length){
			for(int i = 0; i<x.length;i++)
				result = result + ( ( x[i] - (meanX) ) * (y[i] - (meanY)) );				
		}

		return result;
	}
}