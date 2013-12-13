package similarities;

import control.ContainerValues;
import core.Item;
import core.Rating;
import core.User;
import interfaces.Visible;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import similaritiesImpl.SimilarityStrategy;
import util.Visibility;

/**
 * @author Oliverio
 */

//@Visibility(name="ItemBased Pearson")
public class ItemBasedPearsonStrategy
  implements SimilarityStrategy, Visible
{
  public double execute(Integer idItemA, List<Double> listA, Integer idItemB, List<Double> listB)
  {
    if ((listA.size() == 0) || (listB.size() == 0)) {
      return 0.0D;
    }
    double meanA = ((Double)ContainerValues.getInstance().getMapMeanItens().get(idItemA)).doubleValue();
    double meanB = ((Double)ContainerValues.getInstance().getMapMeanItens().get(idItemB)).doubleValue();

    double covAB = 0.0D;
    double varA = 0.0D;
    double varB = 0.0D;

    for (int i = 0; i < listA.size(); i++) {
      double desvioA = ((Double)listA.get(i)).doubleValue() - meanA;
      double desvioB = ((Double)listB.get(i)).doubleValue() - meanB;
      covAB += desvioA * desvioB;
      varA += Math.pow(desvioA, 2.0D);
      varB += Math.pow(desvioB, 2.0D);
    }

    if ((varA == 0.0D) || (varB == 0.0D) || (covAB == 0.0D)) {
      return 0.0D;
    }
    return covAB / Math.sqrt(varA * varB);
  }

  private double mean(List<Double> notas) {
    if (notas.isEmpty()) {
      return 0.0D;
    }
    double somaNotas = 0.0D;
    for (Double nota : notas) {
      somaNotas += nota.doubleValue();
    }
    return somaNotas / notas.size();
  }

  public double execute(Item itemX, Item itemY, Collection<Rating> ratings)
  {
    return 0.0D;
  }

  public double execute(User activeUser, User otherUser)
  {
    return 0.0D;
  }
}