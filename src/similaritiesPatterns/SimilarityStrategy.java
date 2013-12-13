package similaritiesPatterns;

import core.User;
import java.util.List;
/**
 * @author Oliverio
 */

public abstract interface SimilarityStrategy
{
  public abstract double execute(User paramUser1, User paramUser2);

  public abstract double execute(Integer paramInteger1, List<Double> paramList1, Integer paramInteger2, List<Double> paramList2);
}