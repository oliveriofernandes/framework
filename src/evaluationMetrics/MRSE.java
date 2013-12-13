package evaluationMetrics;
/**
 * @author Oliverio
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control.Comparision;
import control.Result;
import interfaces.Visible;
import util.Visibility;

@Visibility (name = "MRSE")
public class MRSE implements Visible{
	
	List<Result> results;
	Map<Comparision, Double> maes;

	public MRSE(List<Result> results) {
		this.results = results;
		maes = new HashMap<Comparision, Double>();
	}

	public Map<Comparision, Double> executeMetric() {
		Comparision comp = this.results.get(0).getComparision();

		double deviations = 0;
		int N = 0;
		for (Result result : results) {
			if (comp.equals(result.getComparision())) {
				deviations += Math.sqrt(Math.abs((result.getReal() - result.getPredicted())));
				N += 1;
			} else {
				maes.put(comp, (deviations / N));
				deviations = 0;
				N = 0;
				comp = result.getComparision();
				deviations += Math.sqrt(Math.abs((result.getReal() - result.getPredicted())));
				N += 1;
			}
		}
		
		maes.put(comp, (deviations / N));
      return maes;
	}


}