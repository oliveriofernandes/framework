/**
 * 
 */
package control;

import interfaces.AnyBasedPredictionStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import similaritiesPatterns.SimilarityStrategy;
import util.Visibility;
import view.graphics.GraphicGenerator;
import evaluationMetrics.BaseMetricFacade;

/**
 * @author Oliverio
 * 
 */
public class ContainerValues {

	private static ContainerValues instance;

	private boolean[] userOrItemBased;

	private int numRatings;

	private Map<Integer, Map<Integer, Double>> mapUsersToItens;

	private Map<Integer, Map<Integer, Double>> mapItensToUsers;

	private Integer idsUsers[];

	private Integer idsItens[];

	private Map<Integer, Integer> mapIdsUsers;

	private Map<Integer, Integer> mapIdsItens;

	private Map<Integer, Double> mapMeanUsers;

	private Map<Integer, Double> mapMeanItens;

	private Map<Class<? extends SimilarityStrategy>, double[][]> mapUsersSimMatix;

	private Map<Class<? extends SimilarityStrategy>, double[][]> mapItensSimMatix;

	private Map<Integer, Map<Integer, Double>>[] mapUsersSplit;

	private Map<Integer, Map<Integer, Double>>[] mapItensSplit;

	private ContainerValues() {
		cleanVariables();
	}

	public static ContainerValues getInstance() {
		if (instance == null)
			instance = new ContainerValues();

		return instance;
	}

	private void cleanVariables() {
		setUserOrItemBased(new boolean[2]);
		setMapUsersToItens(new HashMap<Integer, Map<Integer, Double>>());
		setMapItensToUsers(new HashMap<Integer, Map<Integer, Double>>());
		setIdsUsers(null);
		setIdsItens(null);
		setMapIdsUsers(new HashMap<Integer, Integer>());
		setMapIdsItens(new HashMap<Integer, Integer>());
		setMapMeanUsers(new HashMap<Integer, Double>());
		setMapMeanItens(new HashMap<Integer, Double>());
		setMapUsersSimMatix(new HashMap<Class<? extends SimilarityStrategy>, double[][]>());
		setMapItensSimMatix(new HashMap<Class<? extends SimilarityStrategy>, double[][]>());
		setMapUsersSplit(null);
		setMapItensSplit(null);
	}

	public void run(StorageSettings settings) throws FileNotFoundException,
	InstantiationException, IllegalAccessException, SecurityException,
	IllegalArgumentException, NoSuchMethodException,
	InvocationTargetException {
		
		cleanVariables();
		Collection<Comparision> comparisions = settings.getComparisions();
		for (Comparision comparision : comparisions) {
			if (isBasead(comparision, "user")) {
				getUserOrItemBased()[0] = true;
			}
			if (isBasead(comparision, "item")) {
				getUserOrItemBased()[1] = true;
			}
		}
		loadBase(settings);
		evaluateMean();
		loadSimilarities(settings);
		split(settings);
		List<Result> results = executePredictions(settings);
		evaluatingResults(settings, results);
	}

	private void evaluatingResults(StorageSettings settings,
			List<Result> results) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		
		for (EvaluationMetric evaluationMetric : settings.getEvaluationMetrics()) {
			Class<?> cls = ListClasses.getClassByPresentedAnnotation(
					evaluationMetric.getMetric(), "evaluationMetrics");
			Constructor<?> c = cls.getConstructor(List.class);
			BaseMetricFacade metric = (BaseMetricFacade) c.newInstance(results);
			Map<Comparision, Double> m = new HashMap<Comparision, Double>();
			m = metric.executeMetric();
			GraphicGenerator g = new GraphicGenerator();
			g.mountBarrGraph(m);
		}
	}

	private boolean isBasead(Comparision comparision, String userOrItem) {
		return comparision.getClsAlgorithtm().getAnnotation(Visibility.class)
				.name().toLowerCase().contains(userOrItem.toLowerCase().trim());
	}

	private void loadBase(StorageSettings settings)
			throws FileNotFoundException {
		File fileDataSet = new File(settings.dataSetPath);
		Scanner scanner = new Scanner(fileDataSet);
		int count = 0;
		while (scanner.hasNextLine()) {
			String[] lineSplit = scanner.nextLine().split("[.^,]");
			Integer idUser = Integer.valueOf(lineSplit[0]);
			Integer idItem = Integer.parseInt(lineSplit[1]);
			Double nota = Double.parseDouble(lineSplit[2]);
			addUserItemOnMaps(idUser, idItem, nota);
			count++;
		}
		setNumRatings(count);
		if (getUserOrItemBased()[0]) {
			setIdsUsers(getMapUsersToItens().keySet().toArray(new Integer[0]));
		}
		if (getUserOrItemBased()[1]) {
			setIdsItens(getMapItensToUsers().keySet().toArray(new Integer[0]));
		}
	}

	private void addUserItemOnMaps(Integer idUser, Integer idItem, Double nota) {
		if (getUserOrItemBased()[0]) {
			Map<Integer, Double> mapItensByUser = getMapUsersToItens().get(idUser);
			if (mapItensByUser == null) {
				mapItensByUser = new HashMap<Integer, Double>();
				getMapUsersToItens().put(idUser, mapItensByUser);
			}
			mapItensByUser.put(idItem, nota);
		}

		if (getUserOrItemBased()[1]) {
			Map<Integer, Double> mapUsersByItens = getMapItensToUsers().get(idItem);
			if (mapUsersByItens == null) {
				mapUsersByItens = new HashMap<Integer, Double>();
				getMapItensToUsers().put(idItem, mapUsersByItens);
			}
			mapUsersByItens.put(idUser, nota);
		}
	}

	private void evaluateMean() {
		if (getUserOrItemBased()[0]) {
			setMapMeanUsers(evalMean(getMapUsersToItens()));
		}
		if (getUserOrItemBased()[1]) {
			setMapMeanItens(evalMean(getMapItensToUsers()));
		}
	}

	private Map<Integer, Double> evalMean(Map<Integer, Map<Integer, Double>> map) {
		Map<Integer, Double> mapMean = new HashMap<Integer, Double>();
		for (Integer idEle : map.keySet()) {
			Collection<Double> notas = map.get(idEle).values();
			double somaNotas = 0;
			for (Double nota : notas) {
				somaNotas += nota;
			}
			mapMean.put(idEle, somaNotas / ((double) notas.size()));
		}
		return mapMean;
	}

	private void loadSimilarities(StorageSettings settings)	throws InstantiationException, IllegalAccessException {
		
		Collection<Comparision> comparisions = settings.getComparisions();
		//Set<Class<? extends SimilarityStrategy>> classesSimilarity = new HashSet<Class<? extends SimilarityStrategy>>();
		
		
		if (getUserOrItemBased()[0]) {
			Set<Class<? extends SimilarityStrategy>> classesSim = filter(comparisions, "user");
			setMapUsersSimMatix(evalSimilarities(classesSim, getMapUsersToItens(), getIdsUsers()));
		}
		if (getUserOrItemBased()[1]) {
			Set<Class<? extends SimilarityStrategy>> classesSim = filter(comparisions, "item");
			setMapItensSimMatix(evalSimilarities(classesSim, getMapItensToUsers(), getIdsItens()));
		}
	}

	private Set<Class<? extends SimilarityStrategy>> filter(Collection<Comparision> comparisions, String userOrItem) {
		
		Set<Class<? extends SimilarityStrategy>> classesSim = new HashSet<Class<? extends SimilarityStrategy>>();
		for ( Comparision comp : comparisions) {
			if (comp.getClsAlgorithtm().getAnnotation(Visibility.class).name().toLowerCase().contains(userOrItem.toLowerCase().trim())) {
				classesSim.add(comp.clsSimilarity);
			}
		}
		return classesSim;
	}

	private Map<Class<? extends SimilarityStrategy>, double[][]> evalSimilarities(
			Set<Class<? extends SimilarityStrategy>> classesSimilarity,
			Map<Integer, Map<Integer, Double>> map, Integer[] idsObjs)
					throws InstantiationException, IllegalAccessException {
		
		Map<Class<? extends SimilarityStrategy>, double[][]> similarities = new HashMap<Class<? extends SimilarityStrategy>, double[][]>();
		for (Class<? extends SimilarityStrategy> klass : classesSimilarity) {
			similarities.put(klass, new double[idsObjs.length][]);
		}
		
		for (int i = 0; i < idsObjs.length; i++) {
			Map<Integer, Double> mA = map.get(idsObjs[i]); // mapa com os votos e id's
			for (Class<? extends SimilarityStrategy> klass : classesSimilarity) {
				similarities.get(klass)[i] = new double[i];
			}
			for (int j = 0; j < i; j++) {
				List<Double> listA = new ArrayList<Double>();
				List<Double> listB = new ArrayList<Double>();
				Map<Integer, Double> mB = map.get(idsObjs[j]);
				for (Integer idItemB : mB.keySet()) {
					Double valueA = mA.get(idItemB);
					if (valueA != null) {
						Double valueB = mB.get(idItemB);
						listA.add(valueA);
						listB.add(valueB);
					}
				}
				for (Class<? extends SimilarityStrategy> klass : classesSimilarity) {
					similarities.get(klass)[i][j] = klass.newInstance()
							.execute(idsObjs[i], listA, idsObjs[j], listB);
				}
			}
		}
		return similarities;
	}

	private void split(StorageSettings settings) {
		int k = settings.kValue;
		int qtdByFoldU = getNumRatings() / k;
		int modK = getNumRatings() % k;

		int[] qtdByFold = new int[k];
		for (int i = 0; i < qtdByFold.length; i++) {
			int mod1 = 0;
			if (modK > 0) {
				mod1 = 1;
				modK--;
			}
			qtdByFold[i] = qtdByFoldU + mod1;
		}

		boolean[] visited = new boolean[getNumRatings()];

		setMapUsersSplit(instatiateMapsSplit(k));
		setMapItensSplit(instatiateMapsSplit(k));

		if (getUserOrItemBased()[0]) {
			split(true, getMapUsersToItens(), qtdByFold, visited);
		} else {
			split(false, getMapItensToUsers(), qtdByFold, visited);
		}

	}

	@SuppressWarnings("unchecked")
	private Map<Integer, Map<Integer, Double>>[] instatiateMapsSplit(int k) {
		Map<Integer, Map<Integer, Double>>[] mapSplit = new Map[k];
		for (int i = 0; i < mapSplit.length; i++) {
			mapSplit[i] = new HashMap<Integer, Map<Integer, Double>>();
		}
		return mapSplit;
	}

	private void split(boolean byUser, Map<Integer, Map<Integer, Double>> map, int[] qtdByFold, boolean[] visited) {
	//	double percent = ((double) 1) / ((double) qtdByFold.length);
		do {
			int countRating = 0;
			for (Integer idA : map.keySet()) {
				for (Integer idB : map.get(idA).keySet()) {
					if (!visited[countRating]) {
					//	double random = Math.random();
						for (int i = 0; i < qtdByFold.length; i++) {
						//	if (((i * percent) <= random) && (random < (i * percent) + percent)) {
								if (qtdByFold[i] > 0) {
									if (byUser) {
										addUserItemOnSplit(i, idA, idB, map.get(idA).get(idB));
									} else {
										addUserItemOnSplit(i, idB, idA, map.get(idA).get(idB));
									}
									qtdByFold[i]--;
									visited[countRating] = true;
							//		countRating++;
						//		}
							}
						}
					}
					visited[countRating] = true;
					countRating++;
					if (isFull(qtdByFold)) {
						break;
					}
				}
			}
		} while (!isFull(qtdByFold));

	}

	private boolean isFull(int[] qtdByFold) {
		for (int i = 0; i < qtdByFold.length; i++) {
			if (qtdByFold[i] > 0) {
				return false;
			}
		}
		return true;
	}

	private void addUserItemOnSplit(int i, Integer idUser, Integer idItem, Double nota) {
		if (getUserOrItemBased()[0]) {
			Map<Integer, Double> mapItensByUser = getMapUsersSplit()[i].get(idUser);
			if (mapItensByUser == null) {
				mapItensByUser = new HashMap<Integer, Double>();
				getMapUsersSplit()[i].put(idUser, mapItensByUser);
			}
			mapItensByUser.put(idItem, nota);
		}

		if (getUserOrItemBased()[1]) {
			Map<Integer, Double> mapUsersByItens = getMapItensSplit()[i].get(idItem);
			if (mapUsersByItens == null) {
				mapUsersByItens = new HashMap<Integer, Double>();
				getMapItensSplit()[i].put(idItem, mapUsersByItens);
			}
			mapUsersByItens.put(idUser, nota);
		}
	}

	@SuppressWarnings("unchecked")
	private List<Result> executePredictions(StorageSettings settings) throws InstantiationException, IllegalAccessException {
		System.out.println("Executing predictions ...");
		List<Result> resultList = new ArrayList<Result>();
		Collection<Comparision> comparasions = settings.getComparisions();
		
		for (Comparision comparision : comparasions) {
			AnyBasedPredictionStrategy predictionStrategy = 
					((Class<AnyBasedPredictionStrategy>) comparision.getClsAlgorithtm()).newInstance();
			
			double[][] matrixSimAny;
			Map<Integer, Map<Integer, Double>> mapAnySplit[];
			
			if (isBasead(comparision, "user")) {
				matrixSimAny = getMapUsersSimMatix().get(comparision.getClsSimilarity());
				mapAnySplit = getMapUsersSplit();
			} else {
				matrixSimAny = getMapItensSimMatix().get(comparision.getClsSimilarity());
				mapAnySplit = getMapItensSplit();
			}
			for (int k = 0; k < settings.kValue; k++) {
				Map<Integer, Map<Integer, Double>> mapAnyTraining = unifyTraining(mapAnySplit, k);
				for (Integer idAny : mapAnySplit[k].keySet()) {
					Map<Integer, Double> mapAny = mapAnySplit[k].get(idAny);
					for (Integer idOther : mapAny.keySet()) {
						double predicted = predictionStrategy.execute(idOther, idAny, matrixSimAny, mapAnyTraining);
						if (predicted != 0) {
							Result result = new Result();
							result.setComparision(comparision);
							result.setReal(mapAny.get(idOther));
							result.setPredicted(predicted);
							resultList.add(result);
						}
					}
				}
			}
		}
		System.out.println("Predictions executed.");
		return resultList;
	}

	private Map<Integer, Map<Integer, Double>> unifyTraining(Map<Integer, Map<Integer, Double>>[] mapAnySplit, int k) {
		
		Map<Integer, Map<Integer, Double>> mapTraining = new HashMap<Integer, Map<Integer, Double>>();
		
		for (int i = 0; i < mapAnySplit.length; i++) {
			if (i != k) {
				for (Integer idA : mapAnySplit[i].keySet()) {
					Map<Integer, Double> map = mapTraining.get(idA);
					if (map == null) {
						map = new HashMap<Integer, Double>();
						mapTraining.put(idA, map);
					}
					Map<Integer, Double> mapA = mapAnySplit[i].get(idA);
					for (Integer idB : mapA.keySet()) {
						map.put(idB, mapA.get(idB));
					}
				}
			}
		}
		return mapTraining;
	}

	/**
	 * @return the userOrItemBased
	 */
	public boolean[] getUserOrItemBased() {
		return userOrItemBased;
	}

	/**
	 * @param userOrItemBased the userOrItemBased to set
	 */
	public void setUserOrItemBased(boolean[] userOrItemBased) {
		this.userOrItemBased = userOrItemBased;
	}

	/**
	 * @return the numRatings
	 */
	public int getNumRatings() {
		return numRatings;
	}

	/**
	 * @param numRatings the numRatings to set
	 */
	public void setNumRatings(int numRatings) {
		this.numRatings = numRatings;
	}

	/**
	 * @return the mapUsersToItens
	 */
	public Map<Integer, Map<Integer, Double>> getMapUsersToItens() {
		return mapUsersToItens;
	}

	/** @param mapUsersToItens the mapUsersToItens to set */
	public void setMapUsersToItens(
			Map<Integer, Map<Integer, Double>> mapUsersToItens) {
		this.mapUsersToItens = mapUsersToItens;
	}

	/** @return the mapItensToUsers */
	public Map<Integer, Map<Integer, Double>> getMapItensToUsers() {
		return mapItensToUsers;
	}

	/** @param mapItensToUsers the mapItensToUsers to set */
	public void setMapItensToUsers(
			Map<Integer, Map<Integer, Double>> mapItensToUsers) {
		this.mapItensToUsers = mapItensToUsers;
	}

	/** @return the idsUsers */
	public Integer[] getIdsUsers() {
		return idsUsers;
	}

	/** @param idsUsers the idsUsers to set */
	public void setIdsUsers(Integer[] idsUsers) {
		this.idsUsers = idsUsers;
		if (idsUsers != null) {
			for (int i = 0; i < idsUsers.length; i++) {
				getMapIdsUsers().put(idsUsers[i], i);
			}
		}
	}

	/** @return the idsItens */
	public Integer[] getIdsItens() {
		return idsItens;
	}

	/** @param idsItens the idsItens to set */
	public void setIdsItens(Integer[] idsItens) {
		this.idsItens = idsItens;
		if (idsItens != null) {
			for (int i = 0; i < idsItens.length; i++) {
				getMapIdsItens().put(idsItens[i], i);
			}
		}
	}

	/** @return the mapIdsUsers */
	public Map<Integer, Integer> getMapIdsUsers() {
		return mapIdsUsers;
	}

	/** @param mapIdsUsers the mapIdsUsers to set
	 */
	public void setMapIdsUsers(Map<Integer, Integer> mapIdsUsers) {
		this.mapIdsUsers = mapIdsUsers;
	}

	/** @return the mapIdsItens */
	public Map<Integer, Integer> getMapIdsItens() {
		return mapIdsItens;
	}

	/** @param mapIdsItens the mapIdsItens to set */
	public void setMapIdsItens(Map<Integer, Integer> mapIdsItens) {
		this.mapIdsItens = mapIdsItens;
	}

	/** @return the mapMeanUsers */
	public Map<Integer, Double> getMapMeanUsers() {
		return mapMeanUsers;
	}

	/**
	 * @param mapMeanUsers the mapMeanUsers to set
	 */
	public void setMapMeanUsers(Map<Integer, Double> mapMeanUsers) {
		this.mapMeanUsers = mapMeanUsers;
	}

	/** @return the mapMeanItens */
	public Map<Integer, Double> getMapMeanItens() {
		return mapMeanItens;
	}
	/** @param mapMeanItens the mapMeanItens to set */
	public void setMapMeanItens(Map<Integer, Double> mapMeanItens) {
		this.mapMeanItens = mapMeanItens;
	}

	/**
	 * @return the mapUsersSimMatix */
	public Map<Class<? extends SimilarityStrategy>, double[][]> getMapUsersSimMatix() {
		return mapUsersSimMatix;
	}

	/** @param mapUsersSimMatix the mapUsersSimMatix to set */
	public void setMapUsersSimMatix(
			Map<Class<? extends SimilarityStrategy>, double[][]> mapUsersSimMatix) {
		this.mapUsersSimMatix = mapUsersSimMatix;
	}

	/** @return the mapItensSimMatix */
	public Map<Class<? extends SimilarityStrategy>, double[][]> getMapItensSimMatix() {
		return mapItensSimMatix;
	}

	/** @param mapItensSimMatix the mapItensSimMatix to set */
	public void setMapItensSimMatix(
			Map<Class<? extends SimilarityStrategy>, double[][]> mapItensSimMatix) {
		this.mapItensSimMatix = mapItensSimMatix;
	}

	/** @return the mapUsersSplit */
	public Map<Integer, Map<Integer, Double>>[] getMapUsersSplit() {
		return mapUsersSplit;
	}

	/** @param mapUsersSplit the mapUsersSplit to set */
	public void setMapUsersSplit(Map<Integer, Map<Integer, Double>>[] mapUsersSplit) {
		this.mapUsersSplit = mapUsersSplit;
	}

	/** @return the mapItensSplit */
	public Map<Integer, Map<Integer, Double>>[] getMapItensSplit() {
		return mapItensSplit;
	}

	/** @param mapItensSplit the mapItensSplit to set
	 */
	public void setMapItensSplit(
			Map<Integer, Map<Integer, Double>>[] mapItensSplit) {
		this.mapItensSplit = mapItensSplit;
	}
}
