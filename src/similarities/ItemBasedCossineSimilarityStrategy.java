package similarities;

import core.Item;
import core.Rating;
import core.User;
import interfaces.Visible;
import java.util.Collection;
import java.util.List;

import similaritiesImpl.CossineStrategy;
import similaritiesImpl.SimilarityStrategy;
import util.Description;
import util.Visibility;

/**
 * @author Oliverio
 */

//@Visibility(name="ItemBased Cossine")
@Description(CFapproach="ItemBased", counterpart="ConcreteStrategy", pattern="Strategy")
public class ItemBasedCossineSimilarityStrategy
  implements SimilarityStrategy, Visible
{
  public double execute(Integer idItemA, List<Double> listA, Integer idItemB, List<Double> listB)
  {
    CossineStrategy cossine = new CossineStrategy();
    return cossine.execute(idItemA, listA, idItemB, listB);
  }

  public double execute(Item itemX, Item itemY, Collection<Rating> ratings)
  {
    return 0.0;
  }

  public double execute(User activeUser, User otherUser)
  {
    return 0.0D;
  }
}