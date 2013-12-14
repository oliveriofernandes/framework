package interfaces;

import control.ContainerValues;
import java.util.Map;
import util.Visibility;
/**
 * @author Oliverio
 */

@Visibility(name="Item-Based Collaborative Filtering")
public class ItemBasedClassicStrategy implements PredictionStrategy, Visible
{
  public double execute(Integer idUser, Integer idItem, double[][] matrixSimItem, Map<Integer, Map<Integer, Double>> mapItensTraining)
  {
    double sumNumerador = 0.0;
    double sumDenominador = 0.0;

    int index = ((Integer)ContainerValues.getInstance().getMapIdsItens().get(idItem)).intValue();

    for (int i = 0; i < matrixSimItem[index].length; i++) {
      Integer idOtherItem = ContainerValues.getInstance().getIdsItens()[i];
      if (mapItensTraining.get(idOtherItem) != null) {
        Double rating = (Double)((Map)mapItensTraining.get(idOtherItem)).get(idUser);
        if ((rating != null) && (matrixSimItem[index][i] > 0.0)) {
          sumNumerador = sumNumerador + matrixSimItem[index][i] * ((Double)((Map)mapItensTraining.get(idOtherItem)).get(idUser)).doubleValue();
          sumDenominador += Math.abs(matrixSimItem[index][i]);
        }
      }
    }
    
    for (int i = index + 1; i < matrixSimItem.length; i++) {
      Integer idOtherItem = ContainerValues.getInstance().getIdsItens()[i];
      if (mapItensTraining.get(idOtherItem) != null) {
        Double rating = (Double)((Map)mapItensTraining.get(idOtherItem)).get(idUser);
        if ((rating != null) && (matrixSimItem[i][index] > 0.0D)) {
          sumNumerador = sumNumerador + matrixSimItem[i][index] * ((Double)((Map)mapItensTraining.get(idOtherItem)).get(idUser)).doubleValue();
          sumDenominador += Math.abs(matrixSimItem[i][index]);
        }
      }
    }

    if ((sumNumerador == 0.0) || (sumDenominador == 0.0)) 
      return 0.0;

    double result = sumNumerador / sumDenominador;

    return result;
  }
}