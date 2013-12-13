package predictions;

import cFAlgsImpl.PredictionStrategy;
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