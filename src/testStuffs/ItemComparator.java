package testStuffs;

import java.util.Comparator;

/**
 * @author Oliverio
 */

public class ItemComparator implements Comparator<Item>{

	@Override
	public int compare(Item a, Item b) {
		
		String descrA = a.name;
		String descrB = b.name;
		
		return descrA.compareTo(descrB);
		
	}

}
