package similarities;

import core.Item;
import core.Rating;
import java.util.Collection;
import util.Description;

//@Description(CFapproach="ItemBased", counterpart="Context", pattern="Strategy")
public class ItemBasedSimilarityContext
{
  private ItemBasedSimilarityStrategy strategy;

  public ItemBasedSimilarityContext(ItemBasedSimilarityStrategy strategy)
  {
    this.strategy = strategy;
  }

  public double executeStrategy(Item itemX, Item itemY, Collection<Rating> ratings) {
    return this.strategy.execute(itemX, itemY, ratings);
  }
}