package experimentsStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Visibility;
import experimentsStrategies.corePattern.Strategy;
/**
 * @author Oliverio
 */

@Visibility (name = "K Fold")
public class KFoldCFStrategy<Rating> implements Strategy<Rating>{
	
	
	public KFoldCFStrategy(){
	}

	@Override
	public List<Rating> [][]  execute (List<Rating> dataset, int kFold){
		
		List<Rating> selected= new ArrayList<Rating>();
		List<Rating> test = new ArrayList<Rating>();
		List<Rating> totalObj = new ArrayList<Rating>();
		totalObj.addAll(dataset);
		
		@SuppressWarnings("unchecked")
		List<Rating> [][] matrixKFold =  new ArrayList[kFold][2]; // The first column represents trainData and the second represents testData respectively  
		
		if (kFold == 0) kFold = 3; 
		double percentTest = (100/kFold);
		
		int testSize = test.size();
		int datasetSize = dataset.size();
		
		int qtd = (int) ( datasetSize * (percentTest/100) );
		
		for (int i = 0; i < kFold; i++) {
		while ((testSize < qtd) && (datasetSize > 1)) {
			Random random = new Random(System.currentTimeMillis()+1);
			
			Rating obj  = totalObj.get(random.nextInt(datasetSize - 1));
			if (!selected.contains(obj)){
				test.add(obj);
				selected.add(obj);
				datasetSize-=1;
				testSize+=1;
				totalObj.remove(obj);	
			}
			
		}
		matrixKFold[i][0] = new ArrayList<Rating>();
		matrixKFold[i][0].addAll(totalObj);
		matrixKFold[i][1] = new ArrayList<Rating>();
		matrixKFold[i][1].addAll(test);
		totalObj.addAll(test);
		test.clear();
		testSize=0;
		}
		
		for (int i = 0; i < kFold; i++) {
			List<Rating> list1 = matrixKFold[i][0];
			for (Rating t : list1) {
			//	System.out.println();
			}
			System.out.println("conjunto de treinamento "+ i + " = " +list1.size());
		}
		return matrixKFold;
		
	}

}
