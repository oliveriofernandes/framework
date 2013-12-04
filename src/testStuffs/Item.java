package testStuffs;

/**
 * @author Oliverio
 */

public class Item implements Comparable<Item> {

	String name;
	int code;
	
	public Item(String name, int code){
		this.name = name;
		this.code = code;
		
	}

	@Override
	public int compareTo(Item other) {
		return this.code - other.code;
	}
}
