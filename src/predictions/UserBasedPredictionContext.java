package predictions;

import interfaces.PredictionStrategy;
/**
 * @author Oliverio
 */

public class UserBasedPredictionContext
{
  private PredictionStrategy strategy;

  public UserBasedPredictionContext(PredictionStrategy strategy)
  {
    this.strategy = strategy;
  }
}