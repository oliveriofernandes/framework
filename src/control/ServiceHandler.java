package control;

import java.io.File;
import java.util.List;

import javax.swing.JPanel;

import view.graphics.GraphicGenerator;
import core.AbstractMatrix;
import core.SplittedMatrix;
import enums.TypeMatrix;
/**
 * @author Oliverio
 */
public class ServiceHandler {

	public AbstractMatrix loadMatrix (File file, String typeMatrix){
		return MatrixFactory.createMatrix(TypeMatrix
				.valueOf(typeMatrix), file.getAbsolutePath());
	}
	
	public JPanel plotBarrGraph(AbstractMatrix matrix) {

		JPanel panel = new JPanel();
		panel = new GraphicGenerator().mountBarrGraph(matrix);
		panel.setVisible(true);

		return panel;
	}

	public JPanel plotPercentBarrGraph(AbstractMatrix matrix, double percentageTestSet){

		JPanel panel = new JPanel();
		GraphicGenerator graphicGenerator = new GraphicGenerator();

		List<Double>[] listRatings = matrix.getRandonRatings(percentageTestSet);
		int totalOfRates = matrix.getRatings().size();

		panel = graphicGenerator.mountBarrGraphByPercent(listRatings, totalOfRates);
		panel.setVisible(true);

		splitData(matrix,percentageTestSet);
		return panel;
	}

	public void splitData(AbstractMatrix matrix, double percentageTestSet){
		List<Double>[] listRatings = matrix.getRandonRatings(percentageTestSet);
		
		SplittedMatrix.setTestDataSer(listRatings[0]);
		SplittedMatrix.setTrainDataSet(listRatings[1]);
		
	}

}
