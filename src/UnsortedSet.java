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

	public UnsortedSet() {
		myCon = new ArrayList<E>();
	}

	public boolean add(E item) {
		if (this.contains(item))
			return false;
		else {
			myCon.add(item);
			size++;
			return true;
		}
	}

	public boolean addAll(ISet<E> otherSet) {
		boolean changed = false;
		for (E item : otherSet) {
			boolean added = add(item);
			if (added)
				changed = true;
		}
		return changed;
	}

	public void clear() {
		size = 0;
		Iterator<E> removeIt = this.iterator();
		while (removeIt.hasNext()) {
			removeIt.next();
			removeIt.remove();
		}
	}

	public ISet<E> difference(ISet<E> otherSet) {
		ISet<E> result = new UnsortedSet<E>();
		for (E item : myCon) {
			if (!otherSet.contains(item))
				result.add(item);
		}
		return result;
	}
	
	public ISet<E> intersection(ISet<E> otherSet) {
		ISet<E> result = new UnsortedSet<E>();
		for (E item : myCon) {
			if (otherSet.contains(item))
				result.add(item);
		}
		return result;
	}

	public Iterator<E> iterator() {
		return myCon.iterator();
	}

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

	public int size() {
		return size;
	}

	public ISet<E> union(ISet<E> otherSet) {
		ISet<E> result = new UnsortedSet<>();
		for (E item : myCon)
			result.add(item);
		for (E item : otherSet)
			result.add(item);
		return null;
	}



}