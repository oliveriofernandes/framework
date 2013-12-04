package experimentsStrategies.corePattern;

import java.util.List;
/**
 * @author Oliverio
 */

public interface Strategy<Rating> {
	
	public List<Rating> [][] execute(List<Rating> dataset, int kFold);

}
