package enums;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Oliverio
 */

public enum TypeMatrix {

	CLASSIC("Classic"), CONFIDENCE("Confidence");

	private String value;

	private TypeMatrix(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static TypeMatrix byValue(String str) {
		for (TypeMatrix t : TypeMatrix.values()) {
			if (t.value.equals(str))
				return t;
		}
		return null;
	}
	
	private static List<String> getValuesAsList() {
		List<String> list = new ArrayList<String>();

		for (Object obj : TypeMatrix.values())
			list.add(TypeMatrix.valueOf(obj.toString()).getValue());
		return list;
	}

	public static Object[] getValuesAsArray() {
		return TypeMatrix.getValuesAsList().toArray();
	}
}
