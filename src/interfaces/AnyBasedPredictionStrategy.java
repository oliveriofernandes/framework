package interfaces;

import java.util.Map;

/**
 * @author Oliverio
 */
public abstract interface AnyBasedPredictionStrategy
{
  public abstract double execute(Integer paramIdX, Integer paramIdY, double[][] paramArrayOfSimilarities, Map<Integer, Map<Integer, Double>> paramMap);
}