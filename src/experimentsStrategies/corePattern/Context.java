package experimentsStrategies.corePattern;

import java.util.List;
/**
 * @author Oliverio
 */

@SuppressWarnings("hiding")
public class Context<Double> {

	private Strategy<Double> strategy;
	
	/** Constructor
	 */
	public Context(Strategy<Double> strategy){
		this.strategy = strategy;
	}
	
	public  List<Double> [][] executeStrategy(List<Double> dataset, int parameter ){
		return strategy.execute(dataset, parameter);
	}
}
