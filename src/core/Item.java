package core;

/**
 * @author Oliverio
 */
public class Item implements Comparable<Item> {

	final int id;
	String name;
	String code;
	
	public Item (int id){
	this.id = id;	
	}
	
	public Integer getId() {
		return id;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Item other) {
	
		if (this.id < other.id){
			return -1;
		}
		if (this.id > other.id){
			return 1;
	   }
	        return 0;
	}
}