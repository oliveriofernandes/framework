package similaritiesPatterns;

import control.ContainerValues;
import core.User;
import interfaces.Visible;
import java.util.List;
import java.util.Map;

import util.Visibility;

/**
 * @author Oliverio
 */
@Visibility(name="Pearson")

public class PearsonStrategy
  implements SimilarityStrategy, Visible
{
  public double execute(Integer idUsrA, List<Double> listA, Integer idUsrB, List<Double> listB)
  {
    if ((listA.size() == 0) || (listB.size() == 0)) {
      return 0.0D;
    }
    double meanA = ((Double)ContainerValues.getInstance().getMapMeanUsers().get(idUsrA)).doubleValue();
    double meanB = ((Double)ContainerValues.getInstance().getMapMeanUsers().get(idUsrB)).doubleValue();

    double covAB = 0.0;
    double varA = 0.0;
    double varB = 0.0;

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

  public double execute(User activeUser, User otherUser)
  {
    double CovXY = 0.0D;
    double varX = 0.0D;
    double varY = 0.0D;

    if ((varX == 0.0D) || (varY == 0.0D)) {
      return 0.0D;
    }

    return CovXY / Math.sqrt(varX * varY);
  }
}