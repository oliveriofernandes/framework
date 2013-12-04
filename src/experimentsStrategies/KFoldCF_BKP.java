package experimentsStrategies;

import java.util.Map;

/**
 * @author Oliverio
 */

public class KFoldCF_BKP {

	public int kFold;
	public Map<Integer, Map<Integer, Double>> dataSet;

	public KFoldCF_BKP(int k, Map<Integer, Map<Integer, Double>> dataSet) {
		this.kFold = k;
		this.dataSet = dataSet;
	}

	public void makeKFolds() {

		double percentTest = (100 / kFold);
		double percentTrain = (100 - percentTest)/100;
		
		int qtd = (int) (dataSet.size() * (percentTest / 100));

		double[][] trainArray = new double[dataSet.keySet().size()][2000];
		double[][] testArray = new double[dataSet.keySet().size()][2000];

		for (Map.Entry<Integer, Map<Integer, Double>> entry : dataSet.entrySet()) {
			for (Map.Entry<Integer, Double> subEntry : entry.getValue().entrySet()) {
				trainArray[entry.getKey().intValue()][subEntry.getKey().intValue()] = subEntry
						.getValue();
			}
			}
				int qtdTest = 0; 
				
				while (qtdTest<= 1) {
					
				for (int i = 0; i < trainArray.length; i++) {
					for (int j = 0; j < trainArray.length; j++) {
						if (trainArray[i][j] != 0) {
							double coin = Math.random();
							if (coin < percentTrain ){
								testArray[i][j] = trainArray[1][j];
								trainArray[1][j] = 0;
								                                
							}
						}

					}
				}
				
				}
				
				
	}
	
	public static void main(String[] args) {
		while (true) {
			System.out.println(Math.random());
		}
	}
}
