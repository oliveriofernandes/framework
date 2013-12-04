package core;

/**
 * @author Oliverio
 */
public class User implements Comparable<User> {

	private final int id;

	public int getId() {
		return this.id;
	}

	public User(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(User other) {
		if (this.id < other.id)
			return -1;
		if (this.id > other.id)
			return 1;
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	protected User cloneUser() {
		try {
			return (User) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return (User) new Object();
		}
	}

}
