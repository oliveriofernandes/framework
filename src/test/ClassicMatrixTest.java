package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import util.LoaderClassicMatrix;
import control.MatrixFactory;
import core.ClassicMatrix;
import core.Rating;
import enums.TypeMatrix;

/**
 * @author Oliverio
 */

public class ClassicMatrixTest {
	String path;
	LoaderClassicMatrix load;

	@Before
	public void setUp() {
		path = new String(
				"C:\\Users\\Oliverio\\workspace\\Framework\\data\\matrix.txt");

		load = new LoaderClassicMatrix(path, TypeMatrix.CLASSIC);
	}

	@Test
	public void getRandonRatesTest(){
		ClassicMatrix matrix = (ClassicMatrix) MatrixFactory.createMatrix(TypeMatrix.CLASSIC, path);
	
		Assert.assertEquals(true, matrix != null);
		
		List<Rating> rates = new ArrayList<Rating>();
		long c = System.currentTimeMillis();
		c = System.currentTimeMillis() - c;
	 System.out.println("time = "  + c);
		System.out.println("size = " + rates.size());
		
	}

}
