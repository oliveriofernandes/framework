package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import enums.TypeMatrix;

/**
 * @author Oliverio
 */
public final class ClassicMatrix extends AbstractMatrix {

	private static ClassicMatrix instance;

	final Map<Integer, Map<Integer, Double>> content;

	public static ClassicMatrix getMatrix(Map<Integer, Map<Integer, Double>> content) {
		// if (instance == null) {
		// synchronized (ClassicMatrix.class) {
		// if (instance == null)
		instance = new ClassicMatrix(content);
		// }
		// }
		return instance;
	}

	/*
	 * TODO refatorar esse código pois abre brechas a erros de
	 * NullPointerException
	 */
	public static ClassicMatrix getMatrix() {
		if (instance != null)
			return instance;

		return null;
	}

	private ClassicMatrix(Map<Integer, Map<Integer, Double>> content) {

		this.content = content;
		this.type = TypeMatrix.CLASSIC;
	}

	public final Map<Integer, Map<Integer, Double>> getContent() {
		return content;
	}

	// public Map<Integer, Map<Integer, Rating>> coRatedElementsByUsers(User
	// usrX, User usrY) {

	// User activeUser = new User(usrX.getId());
	// activeUser.setRating(usrX.getRatings());
	//
	// User otherUser = new User(usrY.getId());
	// otherUser.setRating(usrY.getRatings());
	//
	// TreeMap<Item, Rating> mapUserX = new TreeMap<Item, Rating>();
	// TreeMap<Item, Rating> mapUserY = new TreeMap<Item, Rating>();
	//
	// if (this.content.containsKey(usrX))
	// mapUserX.putAll(this.content.get(usrX));
	//
	// if (this.content.containsKey(usrY))
	// mapUserY.putAll(this.content.get(usrY));
	//
	// mapUserX.keySet().retainAll(mapUserY.keySet());
	// mapUserY.keySet().retainAll(mapUserX.keySet());
	//
	// List<Rating> activeUserRates = new ArrayList<Rating>();
	// List<Rating> otherUserRates = new ArrayList<Rating>();
	//
	// activeUserRates.addAll(activeUser.getRatings());
	// otherUserRates.addAll(otherUser.getRatings());
	// for (Rating rate : activeUserRates) {
	// if (!mapUserX.containsKey(rate.getItem())){
	// activeUser.getRatings().remove(rate);
	// }
	// }
	//
	// for (Rating rate : otherUserRates) {
	// if (!mapUserY.containsKey(rate.getItem())){
	// otherUser.getRatings().remove(rate);
	// }
	// }

	// Map<Integer, Map<Integer, Rating>> coRatedElements = new HashMap<Integer,
	// Map<Integer, Rating>>();

	// Collections.sort(activeUser.getRatings());
	// Collections.sort(idOtherUser.getRatings());

	// coRatedElements.put(activeUser, mapUserX);
	// coRatedElements.put(otherUser, mapUserY);

	// return coRatedElements;
	// }

	/*
	 * TODO Refatorar esse código pra pegar trabalhar com essa exceção de modo
	 * mais elegante
	 */

	// public boolean hasSpecificIdUser(Long id){
	// User user = new User(id);
	// return this.getContent().containsKey(user);
	// }

	// public Set<Item> getSetProducts(){
	// Set<Item> products = new HashSet<Item>();
	// Map<User,Map<Item, Rate> > map = this.getContent();
	//
	// for (Map.Entry<User, Map<Item, Rate>> entry : map.entrySet())
	// for(Entry<Item, Rate> subMap : entry.getValue().entrySet())
	// products.add(subMap.getKey());
	//
	// return products;
	// }

	public List<Integer> getListItems() {
		List<Integer> items = new ArrayList<Integer>();
		Map<Integer, Map<Integer, Double>> map = this.getContent();

		for (Map.Entry<Integer, Map<Integer, Double>> entry : map.entrySet())
			for (Entry<Integer, Double> subMap : entry.getValue().entrySet())
				if (!items.contains(subMap.getKey()))
					items.add(subMap.getKey());

		return items;
	}

	// public Map<Item, Rate> getRatesByUser(User user) {
	// if (this.content.containsKey(user))
	// return this.content.get(user);
	// return new HashMap<Item, Rate>();
	// }

	public Set<Integer> getSetUsers() {

		return this.getContent().keySet();
	}

	public List<Integer> getListUsers() {
		List<Integer> users = new ArrayList<Integer>();

		Map<Integer, Map<Integer, Double>> map = this.getContent();

		for (Map.Entry<Integer, Map<Integer, Double>> entry : map.entrySet())
			users.add(entry.getKey());
		return users;
	}

	public List<Integer> getPercentRandomUsers(double percent) {

		List<Integer> users = new ArrayList<Integer>();
		List<Integer> newListUsers = new ArrayList<Integer>();
		users.addAll(this.getSetUsers());

		int qtd = (int) (users.size() * (percent / 100));

		while (newListUsers.size() <= qtd) {
			Random random = new Random(System.currentTimeMillis() + 1);

			Integer user = users.get(random.nextInt(users.size()));
			if (!newListUsers.contains(user))
				newListUsers.add(user);
		}

		return newListUsers;
	}

	public List<Double> getRatings() {
		List<Double> rates = new ArrayList<Double>();
		Set<Integer> users = this.getSetUsers();

		for (Integer usr : users)
			for (Map.Entry<Integer, Double> entry : this.getContent().get(usr).entrySet())
				rates.add(entry.getValue());
		return rates;
	}

	@SuppressWarnings("unchecked")
	public List<Double>[] getRandonRatings(double percentTest) {
		List<Double>[] vectorRates = new List[2];
		int qtd = (int) (this.getRatings().size() * (percentTest / 100));

		List<Double> testRatings = new ArrayList<Double>();
		List<Double> trainRatings = new ArrayList<Double>();
		trainRatings = this.getRatings();

		int sizeList = trainRatings.size();
		while ((testRatings.size() < qtd) && (sizeList > 1)) {
			Random random = new Random(System.currentTimeMillis() + 1);

			Double rate = trainRatings.get(random.nextInt(sizeList - 1));
			testRatings.add(rate);
			sizeList -= 1;
			trainRatings.remove(rate);
		}
		vectorRates[0] = testRatings;
		vectorRates[1] = trainRatings;

		return vectorRates;
	}

	@Override
	public Map<Integer, java.lang.Double> getUserRatings(Integer idUser) {
		Map<Integer, Double> ratings = new HashMap<Integer, Double>();
		if (this.getContent().containsKey(idUser)) {
			for (Map.Entry<Integer, Double> entry : this.getContent().get(idUser).entrySet()) {
				ratings.put(entry.getKey(), entry.getValue());
			}
		}
		return ratings;
	}

	@Override
	public Map<Integer, TreeMap<Integer, Double>> coRatedElementsByUsers(Integer idUserX,
			Integer idUserY) {
		// TODO Auto-generated method stub
		return null;
	}

	// public static void main(String[] args) throws FileNotFoundException {
	//
	// String path2 = new String(
	// "C:\\Users\\Oliverio\\workspace\\Framework\\data\\matrix.txt");
	// ClassicMatrix matrix = (ClassicMatrix) MatrixFactory.createMatrix(
	// TypeMatrix.CLASSIC, path2);
	//
	// Map<User, TreeMap<Item, Rating>> u =
	// matrix.coRatedElementsByUsers(new User(new Long(3)),
	// new User(new Long(5)));
	//
	//
	// for (Entry<User, TreeMap<Item, Rating>> entry : u.entrySet()) { {
	// System.out.println("user id = " + entry.getKey().getId());
	// for (Map.Entry<Item, Rating> entry2 : entry.getValue().entrySet() ) {
	// System.out.println("rate of item = " +
	// entry2.getValue().rating + " - item = " +
	// entry2.getValue().product.getId());
	// }
	//
	// }
	//
	// List<Rating> rates = new ArrayList<Rating>();
	// long c = System.currentTimeMillis();
	// List<Rating>[] x = matrix.getRandonRatings(30.0);
	// c = System.currentTimeMillis() - c;
	//
	// Map<Item, Rating> map = new HashMap<Item, Rating>();
	//
	// List<Rating> [] vector;
	//
	// vector = matrix.getRandonRatings(100);
	//
	// }
	// }
}