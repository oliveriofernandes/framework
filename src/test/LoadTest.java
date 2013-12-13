package test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


import view.graphics.GraphicGenerator;
import core.ClassicMatrix;
import core.LoaderClassicMatrix;
import core.MatrixFactory;
import enums.TypeMatrix;

/**
 * @author Oliverio
 */

public class LoadTest {

	String path;
	LoaderClassicMatrix loader;

	@Before
	public void setUp() {
		path = new String(
				"C:\\Users\\Oliverio\\workspace\\Framework\\data\\matrix.txt");

		loader = new LoaderClassicMatrix(path, TypeMatrix.CLASSIC);
	}

	@Test
	public void getDataset() {

		Assert.assertEquals(loader.getDataset().exists(), true);
		
		ClassicMatrix matrix = (ClassicMatrix) MatrixFactory.createMatrix(TypeMatrix.CLASSIC, path);
	
		GraphicGenerator g = new GraphicGenerator();
		g.mountBarrGraph(matrix);
		
	//	System.out.println(matrix.getMatrixType());
	//	matrix.coratedMatrixElementsByUsers(new User(new Long(1488844)), new User(new Long(885013)));
		
		
	
	
	}
}
