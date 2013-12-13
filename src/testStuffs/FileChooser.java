package testStuffs;

import java.io.FileNotFoundException;

import javax.swing.JFileChooser;


import core.AbstractMatrix;
import core.MatrixFactory;
import enums.TypeMatrix;

/**
 * @author Oliverio
 */

public class FileChooser {
	JFileChooser fileChooser;
	
	public FileChooser(){
		this.fileChooser = new JFileChooser();
	}
	
	public AbstractMatrix getDataSet(String typeMatrix) throws FileNotFoundException {
		JFileChooser fileChooser = new JFileChooser();
		int returnStatus = fileChooser.showOpenDialog(null);

		if (returnStatus == JFileChooser.APPROVE_OPTION) {
			return MatrixFactory.createMatrix(TypeMatrix.valueOf(typeMatrix),
					fileChooser.getSelectedFile().getAbsolutePath());
		}
		return null;
	}
	
	
}
