/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, Miles Chandler and Jacob Hungerford, this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 Miles Chandler
 *  UTEID: mac9325
 *  email address: miles.chandler@ichandler.net
 *  Grader name: Anthony
 *  Section number: 51345
 *
 *  Student 2 Jacob Hungerford
 *  UTEID: jdh5468
 *  email address: JHungerford1516@utexas.edu
 *  Grader name: Anthony
 *  Section number: 51345
 *
 */

import java.util.Iterator;

/**
 * Students are to complete this class. Students should implement as many
 * methods as they can using the Iterator from the iterator method and the other
 * methods.
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

	/*
	 * NO INSTANCE VARIABLES ALLOWED. NO DIRECT REFERENCE TO UnsortedSet OR
	 * SortedSet ALLOWED. (In other words the data types UnsortedSet and
	 * SortedSet will not appear any where in this class.) Also no direct
	 * references to ArrayList or other Java Collections.
	 */

	/**
	 * A union operation. Add all items of otherSet that are not already present
	 * in this set to this set. O(N^2)
	 * 
	 * @param otherSet
	 *            != null
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise.
	 */
	public boolean addAll(ISet<E> otherSet) {
		boolean changed = false;
		for (E val : otherSet) {
			if (!this.contains(val)) {
				this.add(val);
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * Make this set empty. O(N) <br>
	 * pre: none <br>
	 * post: size() = 0
	 */
	public void clear() {
		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	/**
	 * Remove the specified item from this set if it is present. O(N) pre: item
	 * != null
	 * 
	 * @param item
	 *            the item to remove from the set. item may not equal null.
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise
	 */
	public boolean remove(E item) {
		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			if (it.next().equals(item)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine if this set is equal to other. Two sets are equal if they have
	 * exactly the same elements. The order of the elements does not matter.
	 * O(N^2) <br>
	 * pre: none
	 * 
	 * @param other
	 *            the object to compare to this set
	 * @return true if other is a Set and has the same elements as this set
	 */
	public boolean equals(Object other) {
		boolean equal = false;
		if (other instanceof AbstractSet) {
			AbstractSet<E> otherSet = (AbstractSet<E>) other;
			equal = this.containsAll(otherSet)
					&& this.size() == otherSet.size();
		}
		return equal;
	}

	/**
	 * Determine if item is in this set. O(N) <br>
	 * pre: item != null
	 * 
	 * @param item
	 *            element whose presence is being tested. Item may not equal
	 *            null.
	 * @return true if this set contains the specified item, false otherwise.
	 */
	public boolean contains(E item) {
		for (E val : this) {
			if (val.equals(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine if all of the elements of otherSet are in this set. O(N^2) <br>
	 * pre: otherSet != null
	 * 
	 * @param otherSet
	 *            != null
	 * @return true if this set contains all of the elements in otherSet, false
	 *         otherwise.
	 */
	public boolean containsAll(ISet<E> otherSet) {
		for (E item : otherSet) {
			boolean contains = contains(item);
			if (!contains)
				return false;
		}
		return true;
	}

	// Returns a string form of the set in the form (n1, n2,..., ni). O(N)
	public String toString() {
		StringBuilder result = new StringBuilder();
		String seperator = ", ";
		result.append("(");

		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			result.append(it.next());
			result.append(seperator);
		}
		// get rid of extra separator
		if (this.size() > 0)
			result.setLength(result.length() - seperator.length());

		result.append(")");
		return result.toString();
	}

	/**
	 * Create a new set that is the union of this set and otherSet. O(N^2) <br>
	 * pre: otherSet != null <br>
	 * post: returns a set that is the union of this set and otherSet. Neither
	 * this set or otherSet are altered as a result of this operation. <br>
	 * pre: otherSet != null
	 * 
	 * @param otherSet
	 *            != null
	 * @return a set that is the union of this set and otherSet
	 */
	public ISet<E> intersection(ISet<E> otherSet) {
		ISet<E> result = this.union(otherSet).difference(
				this.difference(otherSet).union(otherSet.difference(this)));
		return result;
	}
}