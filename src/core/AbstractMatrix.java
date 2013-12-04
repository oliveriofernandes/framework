package core;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import enums.TypeMatrix;

/**
 * @author Oliverio
 */
public abstract class AbstractMatrix {

	private Object content;
	protected TypeMatrix type;

	public static AbstractMatrix getMatrix() {
		return null;
	};

	public Object getContent() {
		return content;
	}

	public TypeMatrix getTypeMatrix() {
		return type;
	}

	abstract public Set<Integer> getSetUsers();

	abstract public List<Integer> getListUsers();

	abstract public Map<Integer, Double> getUserRatings(Integer idUser);

	abstract public Map<Integer, TreeMap<Integer, Double>> coRatedElementsByUsers(Integer idUserX,
			Integer idUserY);

	abstract public List<Double> getRatings();

	abstract public List<Double>[] getRandonRatings(double percentTest);
}