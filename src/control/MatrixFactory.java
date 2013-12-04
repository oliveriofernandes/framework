package control;

import java.util.Map;

import util.LoaderClassicMatrix;
import core.AbstractMatrix;
import core.ClassicMatrix;
import enums.TypeMatrix;
/**
 * @author Oliverio
 * 
 */
public class MatrixFactory {
	
	public static AbstractMatrix createMatrix(TypeMatrix matrixType, String pathName) {

		switch (matrixType) {
		case CLASSIC:
			LoaderClassicMatrix loader = new LoaderClassicMatrix(pathName, matrixType);
			Map<Integer, Map<Integer, Double>> content = loader.getData();
			return  ClassicMatrix.getMatrix(content);
		case CONFIDENCE:
			LoaderClassicMatrix loader2 = new LoaderClassicMatrix(pathName, matrixType);
			Map<Integer, Map<Integer, Double>> content2 = loader2.getData();
			return  ClassicMatrix.getMatrix(content2);
			// return new ClassicMatrix();
			//break;
		default : 
		break;
		}

		
		throw new IllegalArgumentException("The matrix type " + matrixType
				+ " is not recognized");
	}
	
	public static ClassicMatrix createClassicMatrix (String pathName){
		return ClassicMatrix.getMatrix(new LoaderClassicMatrix(pathName, TypeMatrix.CLASSIC).getData());
	}
}