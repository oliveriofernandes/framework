package predictions;

import interfaces.ItemBasedPredictionStrategy;
/**
 * @author Oliverio
 */

public class ItemBasedPredictionContext
{
  private ItemBasedPredictionStrategy strategy;

  public ItemBasedPredictionContext(ItemBasedPredictionStrategy strategy)
  {
    this.strategy = strategy;
  }
}