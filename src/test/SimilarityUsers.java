package test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oliverio
 */

public class SimilarityUsers {
	
	static Map<String,double[][]> totalSimilarities;
	
	
	static private SimilarityUsers instance = null;
	
	static public SimilarityUsers getInstance(int tam, String similarityType) {
	      if(null == instance) {
	         instance = new SimilarityUsers(tam, similarityType);
	      }
	      return instance;
	   }
	
	private SimilarityUsers(int tam, String similarityType){
		double[][] simMatrix = new double[tam][tam];
		totalSimilarities = new HashMap<String, double[][]>();
		totalSimilarities.put(similarityType, simMatrix);
		
	}
	
}