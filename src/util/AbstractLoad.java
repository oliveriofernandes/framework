package util;

import java.io.File;

import enums.TypeMatrix;
/**
 * @author Oliverio
 */

public abstract class AbstractLoad {

	String pathName;
	TypeMatrix type;
	
	public AbstractLoad(String pathName, TypeMatrix type) {
		this.pathName = pathName;
		this.type = type;
	}
	
	public File getDataset() {
		return new File(pathName);
	}

	public abstract Object getData();
}