package cFAlgsImpl;

import control.ContainerValues;
import interfaces.Visible;
import java.util.Map;

import util.Visibility;
/**
 * @author Oliverio
 */

@Visibility(name="User-Based Collaborative Filtering")
public class UserBasedClassicStrategy
  implements PredictionStrategy, Visible
{
  public double execute(Integer idItem, Integer idUser, double[][] matrixSimUser, Map<Integer, Map<Integer, Double>> mapUsersTraining)
  {
    double sumNumerador = 0.0;
    double sumDenominador = 0.0;

    int index = ((Integer)ContainerValues.getInstance().getMapIdsUsers().get(idUser)).intValue();

    for (int i = 0; i < matrixSimUser[index].length; i++) {
      Integer idOtherUser = ContainerValues.getInstance().getIdsUsers()[i];
      if (mapUsersTraining.get(idOtherUser) != null) {
        Double rating = (Double)((Map)mapUsersTraining.get(idOtherUser)).get(idItem);
        if ((rating != null) && (matrixSimUser[index][i] > 0.0D))
        {
          sumNumerador = sumNumerador + matrixSimUser[index][i] * 
            ((Double)((Map)mapUsersTraining.get(idOtherUser)).get(idItem)).doubleValue();
          sumDenominador += Math.abs(matrixSimUser[index][i]);
        }
      }
    }
    for (int i = index + 1; i < matrixSimUser.length; i++) {
      Integer idOtherUser = ContainerValues.getInstance().getIdsUsers()[i];
      if (mapUsersTraining.get(idOtherUser) != null) {
        Double rating = (Double)((Map)mapUsersTraining.get(idOtherUser)).get(idItem);
        if ((rating != null) && (matrixSimUser[i][index] > 0.0D))
        {
          sumNumerador = sumNumerador + matrixSimUser[i][index] * 
        		  ((Double)((Map)mapUsersTraining.get(idOtherUser)).get(idItem)).doubleValue();
          sumDenominador += Math.abs(matrixSimUser[i][index]);
        }
      }
    }

    if ((sumNumerador == 0.0) || (sumDenominador == 0.0)) {
      return 0.0;
    }

    double result = sumNumerador / sumDenominador;

    return result;
  }
}