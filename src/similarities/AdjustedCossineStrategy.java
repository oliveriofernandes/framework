package similarities;

import control.ContainerValues;
import core.User;
import interfaces.Visible;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import similaritiesImpl.SimilarityStrategy;
import util.Visibility;
/**
 * @author Oliverio
 */

//@Visibility(name="Item Based Adjusted Cossine")
public class AdjustedCossineStrategy implements SimilarityStrategy, Visible
{
  public double execute(Integer idUsrA, List<Double> listA, Integer idUsrB, List<Double> listB)
  {
    if ((listA.size() == 0) || (listB.size() == 0)) {
      return 0.0D;
    }
    double dotProduct = 0.0D;
    double normActiveUser = 0.0D;
    double normOtherUser = 0.0D;

    double meanUsrA = ((Double)ContainerValues.getInstance().getMapMeanUsers().get(idUsrA)).doubleValue();
    double meanUsrB = ((Double)ContainerValues.getInstance().getMapMeanUsers().get(idUsrB)).doubleValue();

    for (int i = 0; i < listA.size(); i++) {
      Double valueA = (Double)listA.get(i);
      Double valueB = (Double)listB.get(i);
      dotProduct += (valueA.doubleValue() - meanUsrA) * (valueB.doubleValue() - meanUsrB);
      normActiveUser += Math.pow(valueA.doubleValue() - meanUsrA, 2.0D);
      normOtherUser += Math.pow(valueB.doubleValue() - meanUsrA, 2.0D);
    }

    if ((dotProduct == 0.0D) || (normActiveUser == 0.0D) || (normOtherUser == 0.0D)) {
      return 0.0D;
    }

    double result = dotProduct / (Math.sqrt(normActiveUser) * Math.sqrt(normOtherUser));

    return result;
  }

  public static void main(String[] args)
  {
    double d = 3.0199D;
    double result = Math.round(d * 100.0D) / 100.0D;
    System.out.println(result);
  }

  public double execute(User activeUser, User otherUser)
  {
    double dotProduct = 0.0D;
    double normActiveUser = 0.0D;
    double normOtherUser = 0.0D;

    if ((dotProduct == 0.0D) || (normActiveUser == 0.0D) || (normOtherUser == 0.0D))
      return 0.0D;
    System.out.println("correlation = " + dotProduct / (
      Math.sqrt(normActiveUser) * Math.sqrt(normOtherUser)));

    return dotProduct / (Math.sqrt(normActiveUser) * Math.sqrt(normOtherUser));
  }
}