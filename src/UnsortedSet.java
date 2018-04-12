/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, Miles Chandler and Jacob Hungerford), this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Section number:
 *
 *  Student 2
 *  UTEID: jdh5468
 *  email address: JHungerford1516@utexas.edu
 *  Grader name: Anthony
 *  Section number: 51350
 *
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet. Elements are not in any particular order.
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must be
 * used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {
	private ArrayList<E> myCon;
	private int size;

	// Creates a new Unsorted Set object with an empty container.
	public UnsortedSet() {
		myCon = new ArrayList<E>();
	}

	// Adds the object to the Unsorted Set.
	// Pre: item != null
	// Post: Adds the item to the set if not already in the set. Returns true
	// if the set is changed by this method, false otherwise.
	public boolean add(E item) {
		if (item == null)
			throw new IllegalArgumentException("Item must be != null.");
		if (this.contains(item))
			return false;
		else {
			myCon.add(item);
			size++;
			return true;
		}
	}

	// Removes all elements from the set and resets the size to 0.
	public void clear() {
		size = 0;
		Iterator<E> removeIt = this.iterator();
		while (removeIt.hasNext()) {
			removeIt.next();
			removeIt.remove();
		}
	}

	// Returns a new Unsorted Set object that consists of the items that are in
	// the calling set but not in the parameter set.
	// Pre: otherSet != null.
	public ISet<E> difference(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("OtherSet must be != null.");
		ISet<E> result = new UnsortedSet<E>();
		for (E item : myCon) {
			if (!otherSet.contains(item))
				result.add(item);
		}
		return result;
	}

	// Returns a new Unsorted Set object that consists only of items that are in
	// both the calling set and the parameter set.
	// Pre: otherSet != null.
	public ISet<E> intersection(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("OtherSet must be != null.");
		ISet<E> result = new UnsortedSet<E>();
		for (E item : myCon) {
			if (otherSet.contains(item))
				result.add(item);
		}
		return result;
	}

	// Returns a new Iterator object for the set. This iterator iterates
	// over the internal arraylist storage container.
	public Iterator<E> iterator() {
		return myCon.iterator();
	}

	// Removes an item from the set if the item exists in the set.
	// Pre: item != null.
	// Post: item removed from the set, returns true if the set is changed by
	// the method.
	public boolean remove(E item) {
		Iterator<E> removeIt = this.iterator();
		boolean removed = false;
		while (removeIt.hasNext() && !removed) {
			if (removeIt.next().equals(item)) {
				removeIt.remove();
				removed = true;
				size--;
			}
		}
		return removed;
	}

	// Returns the size of the Unsorted Set.
	public int size() {
		return size;
	}

	// Returns a new Unsorted Set object that consists of elements from both
	// the calling set and the parameter set.
	// Pre: otherSet != null
	public ISet<E> union(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("OtherSet must be != null.");
		ISet<E> result = new UnsortedSet<>();
		for (E item : myCon)
			result.add(item);
		for (E item : otherSet)
			result.add(item);
		return result;
	}
}