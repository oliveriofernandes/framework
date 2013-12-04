package testStuffs;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Oliverio
 */

public class TestSortedSet {

	Collection<Item> items;

	@Before
	public void setUp(){
		this.items = new TreeSet<Item>(new Comparator<Item>(){
			@Override
			public int compare(Item a, Item b) {
				
				String descrA = a.name;
				String descrB = b.name;
				
				return descrA.compareTo(descrB);
				
			}
		});
		items.add(new Item("Item1",3));
		items.add(new Item("Item2",2));
		items.add(new Item("Item3",1));
		

}
	@Test
	public void run(){

		for (Item i : this.items)
		System.out.println("Item: " + i.code + " " + i.name);
			
	}
}