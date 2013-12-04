package interfaces;

import java.util.Map;

/**
 * @author Oliverio
 */
public abstract interface AnyBasedPredictionStrategy
{
  public abstract double execute(Integer paramInteger1, Integer paramInteger2, double[][] paramArrayOfDouble, Map<Integer, Map<Integer, Double>> paramMap);
}