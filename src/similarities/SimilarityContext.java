package similarities;

import java.util.List;

/**
 * @author Oliverio
 */

public class SimilarityContext {

	private SimilarityStrategy strategy;

	public SimilarityContext(SimilarityStrategy strategy) {
		this.strategy = strategy;
	}

	public double executeStrategy(Integer idUsrA, List<Double> listA,Integer idUsrB, 
			List<Double> listB) {
		return this.strategy.execute(idUsrA, listA, idUsrB, listB);
	}
}