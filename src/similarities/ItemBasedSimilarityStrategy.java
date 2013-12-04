package similarities;

import core.Item;
import core.Rating;
import java.util.Collection;
import java.util.List;

/**
 * @author Oliverio
 */

public abstract interface ItemBasedSimilarityStrategy
{
  public abstract double execute(Item paramItem1, Item paramItem2, Collection<Rating> paramCollection);

  public abstract double execute(Integer paramInteger1, List<Double> paramList1, Integer paramInteger2, List<Double> paramList2);
}