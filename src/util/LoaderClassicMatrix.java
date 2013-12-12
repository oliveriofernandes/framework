package util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

import core.Item;
import core.Rating;
import core.User;
import enums.TypeMatrix;

/**
 * @author Oliverio
 */

public class LoaderClassicMatrix extends AbstractLoad {
	// This cooresponds to the configuration Map<User, Map<Item, Rating>>
	// matrix;
	Map<Integer, Map<Integer, Double>> matrix;

	public LoaderClassicMatrix(String pathName, TypeMatrix type) {
		super(pathName, type);
		this.matrix = new HashMap<Integer, Map<Integer, Double>>();
	}

	/**
	 * This method receive a array of values and mount the matrix
	 * 
	 * @param arrayOfValues
	 */

	private void loadMatrix(String[] arrayOfValues) {

		Item item = new Item(Integer.parseInt(arrayOfValues[1]));
		Rating rating = new Rating();
		rating.setRating(Double.parseDouble(arrayOfValues[2]));
		rating.setIdItem(item.getId());
		User user = new User(Integer.valueOf(arrayOfValues[0]));
		rating.setIdUser(user.getId());

		int idUser = user.getId();
		int idItem = item.getId();
		double value = rating.getRating();
		if (!matrix.containsKey(idUser)) {
			Map<Integer, Double> map = new HashMap<Integer, Double>();
			map.put(idItem, value);
			matrix.put(idUser, map);
		} else {
			Map<Integer, Double> map = matrix.get(idUser);
			if (map.containsKey(idItem))
				throw new IllegalArgumentException("This item " + item.getId()
						+ " already had been rated by the user! ");
			else {
				map.put(idItem, value);
				matrix.remove(idUser);
				matrix.put(idUser, map);
			}
		}
	}

	/**
	 * From a formatted dataset, this method returns the matrix with all values
	 * for the user' ratings about items
	 * 
	 * @return matrix
	 */
	@Override
	public Map<Integer, Map<Integer, Double>> getData() {

		try {
			Scanner scanner = new Scanner(this.getDataset());
			while (scanner.hasNextLine())
				this.loadMatrix(scanner.nextLine().split("[.^,]"));
			scanner.close();
			return matrix;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "FileNotFoundException");

			return null;
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "NumberFormatException");
			throw new NumberFormatException("NumberFormatException");
		}
		
	}
}