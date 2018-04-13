/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, Miles Chandler and Jacob Hungerford), this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used: 0
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
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 * <p>
 * The data type for E must be a type that implements Comparable.
 * <p>
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must be
 * used as the internal storage container. For methods involving two set, if
 * that method can be done more efficiently if the other set is also a SortedSet
 * do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

	private ArrayList<E> myCon;

	/**
	 * create an empty SortedSet
	 */
	public SortedSet() {
		myCon = new ArrayList<>();
	}

	/**
	 * create a SortedSet out of an unsorted set. O(NlogN)<br>
	 *
	 * @param other
	 *            != null
	 */
	public SortedSet(ISet<E> other) {
		if (other == null) {
			throw new IllegalArgumentException("Parameter must not be null");
		}
		myCon = new ArrayList<>();
		ArrayList<E> temp = new ArrayList(myCon.size());
		for (E val : other) {
			myCon.add(val);
			temp.add(null);

		}
		mergeSort(myCon, temp, 0, myCon.size() - 1);
	}

	// Returns a new Iterator object for the set. This iterator iterates
	// over the internal arraylist storage container. O(1)
	public Iterator<E> iterator() {
		return myCon.iterator();
	}

	/**
	 * Add an item to this set. O(N) <br>
	 * item != null
	 *
	 * @param item
	 *            the item to be added to this set. item may not equal null.
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise.
	 */
	public boolean add(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Parameter must not be null.");
		}
		if (!contains(item)) {
			int index = 0;
			while (index < myCon.size()
					&& item.compareTo(myCon.get(index)) > 0) {
				index++;
			}
			myCon.add(index, item);
			return true;
		}
		return false;
	}

	/**
	 * Make this set empty. O(1) <br>
	 * pre: none <br>
	 * post: size() = 0
	 */
	public void clear() {
		myCon = new ArrayList<E>();
	}

	/**
	 * Return the number of elements of this set. O(1) pre: none
	 *
	 * @return the number of items in this set
	 */
	public int size() {
		return myCon.size();
	}

	/**
	 * A union operation. Add all items of otherSet that are not already present
	 * in this set to this set. O(N)
	 *
	 * @param otherSet
	 *            != null
	 * @return true if this set changed as a result of this operation, false
	 *         otherwise.
	 */
	public boolean addAll(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("OtherSet must not == null.");
		if (otherSet instanceof SortedSet) {
			SortedSet<E> otherSortedSet = (SortedSet<E>) otherSet;
			ArrayList<E> temp = new ArrayList<>(this.size() + otherSet.size());
			int newSize = merge(otherSortedSet, temp);
			int oldSize = myCon.size();
			myCon = temp;
			return newSize != oldSize;
		} else {
			return super.addAll(otherSet);
		}
	}

	// Helper method for the addAll method. Merges the contents of two sorted
	// sets.
	private int merge(SortedSet<E> otherSortedSet, ArrayList<E> temp) {
		int indexThis = 0;
		int indexOther = 0;
		while (indexThis < this.size() && indexOther < otherSortedSet.size()) {
			E thisItem = myCon.get(indexThis);
			E otherItem = otherSortedSet.myCon.get(indexOther);
			int compare = thisItem.compareTo(otherItem);
			if (compare < 0) {
				temp.add(thisItem);
				indexThis++;
			} else if (compare > 0) {
				temp.add(otherItem);
				indexOther++;
			} else {
				temp.add(thisItem);
				indexThis++;
				indexOther++;
			}
		}
		for (int i = indexThis; i < size(); i++) {
			temp.add(myCon.get(i));
		}
		for (int i = indexOther; i < otherSortedSet.size(); i++) {
			temp.add(otherSortedSet.myCon.get(i));
		}
		return temp.size();
	}

	/**
	 * Determine if item is in this set. O(logN) <br>
	 * pre: item != null
	 *
	 * @param item
	 *            element whose presence is being tested. Item may not equal
	 *            null.
	 * @return true if this set contains the specified item, false otherwise.
	 */
	public boolean contains(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Parameter can't be null");
		}
		return binSearch(item, 0, myCon.size() - 1);
	}

	// Helper method for the contains method, searches for an element by
	// eliminating half of the list at a time until the element or its location
	// if it were to exist is found. Returns true if the element is found and
	// false otherwise.
	private boolean binSearch(E target, int low, int high) {
		if (low <= high) {
			int mid = low + ((high - low) / 2);
			if (myCon.get(mid).equals(target))
				return true;
			else if (myCon.get(mid).compareTo(target) > 0)
				return binSearch(target, low, mid - 1);
			else
				return binSearch(target, mid + 1, high);
		}
		return false;
	}

	// merge sort alg altered from Mike Scott's PDF.
	private void mergeSort(ArrayList<E> list, ArrayList<E> temp, int start,
			int stop) {
		if (start < stop) {
			int center = (start + stop) / 2;
			mergeSort(list, temp, start, center);
			mergeSort(list, temp, center + 1, stop);
			mergeSub(list, temp, start, center + 1, stop);
		}
	}

	// Helper method for the mergesort algorithm.
	private void mergeSub(ArrayList<E> list, ArrayList<E> temp, int leftPos,
			int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tempPos = leftPos;
		int numElements = rightEnd - leftPos + 1;
		while (leftPos <= leftEnd && rightPos <= rightEnd) {
			if (list.get(leftPos).compareTo(list.get(rightPos)) <= 0) {
				temp.set(tempPos, list.get(leftPos));
				leftPos++;
			} else {
				temp.set(tempPos, list.get(rightPos));
				rightPos++;
			}
			tempPos++;
		}
		// copy rest of left half
		while (leftPos <= leftEnd) {
			temp.set(tempPos, list.get(leftPos));
			tempPos++;
			leftPos++;
		}
		// copy rest of right half
		while (rightPos <= rightEnd) {
			temp.set(tempPos, list.get(rightPos));
			tempPos++;
			rightPos++;
		}
		// Copy temp back into list
		for (int i = 0; i < numElements; i++, rightEnd--)
			list.set(rightEnd, temp.get(rightEnd));
	}

	/**
	 * Return the smallest element in this SortedSet. O(1) <br>
	 * pre: size() != 0
	 *
	 * @return the smallest element in this SortedSet.
	 */
	public E min() {
		if (size() == 0) {
			throw new IllegalStateException("Set has zero elements.");
		} else {
			return myCon.get(0);
		}

	}

	/**
	 * Create a new set that is the union of this set and otherSet. O(N) if
	 * otherSet instanceof SortedSet, O(N^2) otherwise. <br>
	 * pre: otherSet != null <br>
	 * post: returns a set that is the union of this set and otherSet. Neither
	 * this set or otherSet are altered as a result of this operation. <br>
	 * pre: otherSet != null
	 *
	 * @param otherSet
	 *            != null
	 * @return a set that is the union of this set and otherSet
	 */
	public ISet<E> union(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet can't be null");
		}
		ISet<E> newSet = new SortedSet<>();
		newSet.addAll(this);
		newSet.addAll(otherSet);
		return newSet;
	}

	/**
	 * Return the largest element in this SortedSet. O(1) <br>
	 * pre: size() != 0
	 *
	 * @return the largest element in this SortedSet.
	 */
	public E max() {
		if (size() == 0) {
			throw new IllegalStateException("Set has zero elements.");
		} else {
			return myCon.get(myCon.size() - 1);
		}
	}

	/**
	 * Determine if this set is equal to other. Two sets are equal if they have
	 * exactly the same elements. The order of the elements does not matter.
	 * O(N) if otherSet instanceof SortedSet else O(N^2) <br>
	 * pre: none
	 *
	 * @param other
	 *            the object to compare to this set
	 * @return true if other is a Set and has the same elements as this set
	 */
	public boolean equals(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("Parameter can not be null.");
		}
		// Not same size return false.
		if (size() != otherSet.size()) {
			return false;
		}
		// Elements should be in same order if both sorted.
		if (otherSet instanceof SortedSet) {
			boolean same = true;
			int index = 0;
			while (index < size() && same) {
				same = myCon.get(index)
						.equals(((SortedSet) otherSet).myCon.get(index));
				index++;
			}
			return same;
			// Call parent method if not sorted.
		} else {
			return super.equals(otherSet);
		}
	}

	/**
	 * Create a new set that is the difference of this set and otherSet. Return
	 * an ISet of elements that are in this Set but not in otherSet. Also called
	 * the relative complement. O(N) if otherSet instanceof SortedSet, O(N^2)
	 * otherwise <br>
	 * Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] then
	 * A.difference(B) would return an ISet with elements [X, Y] while
	 * B.difference(A) would return an ISet with elements [W]. <br>
	 * pre: otherSet != null <br>
	 * post: returns a set that is the difference of this set and otherSet.
	 * Neither this set or otherSet are altered as a result of this operation.
	 * <br>
	 * pre: otherSet != null
	 *
	 * @param otherSet
	 *            != null
	 * @return a set that is the difference of this set and otherSet
	 */
	public ISet<E> difference(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("Parameter can't be null");
		}
		ISet<E> result = new SortedSet<>();
		if (otherSet instanceof SortedSet) {
			SortedSet<E> otherSortedSet = (SortedSet<E>) otherSet;
			int thisIndex = 0;
			int otherIndex = 0;
			while (thisIndex < myCon.size()
					&& otherIndex < otherSortedSet.myCon.size()) {
				E thisVal = myCon.get(thisIndex);
				E otherVal = otherSortedSet.myCon.get(otherIndex);
				int comparison = thisVal.compareTo(otherVal);
				if (comparison == 0) {
					// found an element, move on
					otherIndex++;
					thisIndex++;
				} else if (comparison < 0) {
					// havent found it yet, but still has a chance
					thisIndex++;
				} else {
					// element does not exist
					result.add(thisVal);
					otherIndex++;
				}
			}
			while (thisIndex < myCon.size()) {
				result.add(myCon.get(thisIndex));
				thisIndex++;
			}
		} else {
			for (E item : myCon) {
				if (!otherSet.contains(item))
					result.add(item);
			}
		}
		return result;
	}

	/**
	 * Determine if all of the elements of otherSet are in this set. O(N) if
	 * otherSet instance of SortedSet, O(N^2) otherwise <br>
	 * pre: otherSet != null
	 *
	 * @param otherSet
	 *            != null
	 * @return true if this set contains all of the elements in otherSet, false
	 *         otherwise.
	 */
	public boolean containsAll(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("Parameter can't be null");
		}
		if (otherSet instanceof SortedSet) {
			SortedSet<E> otherSortedSet = (SortedSet<E>) otherSet;
			int thisIndex = 0;
			int otherIndex = 0;
			while (thisIndex < myCon.size()
					&& otherIndex < otherSortedSet.myCon.size()) {
				E thisVal = myCon.get(thisIndex);// 0 1 3 4
				E otherVal = otherSortedSet.myCon.get(otherIndex);// 2 4 6 8
				if (thisVal.getClass().equals(otherVal.getClass())) {
					int comparison = thisVal.compareTo(otherVal);
					if (comparison == 0) {
						// found an element, move on
						otherIndex++;
						thisIndex++;
					} else if (comparison < 0) {
						// havent found it yet, but still has a chance
						thisIndex++;
					} else {
						// element does not exist
						return false;
					}
				} else {
					return false;
				}

			}
			// check one last time if we run out of elements
			return otherIndex == otherSortedSet.myCon.size();
		} else {
			// otherSet is a unsorted set so just use the parent code
			return super.containsAll(otherSet);
		}
	}

	/**
	 * create a new set that is the intersection of this set and otherSet. O(N)
	 * if otherSet instanceof SortedSet, O(N^2) otherwise <br>
	 * pre: otherSet != null<br>
	 * <br>
	 * post: returns a set that is the intersection of this set and otherSet.
	 * Neither this set or otherSet are altered as a result of this operation.
	 * <br>
	 * pre: otherSet != null
	 *
	 * @param otherSet
	 *            != null
	 * @return a set that is the intersection of this set and otherSet
	 */
	public ISet<E> intersection(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("Parameter can't be null");
		}
		ISet<E> result = new SortedSet<>();
		if (otherSet instanceof SortedSet) {
			SortedSet<E> otherSortedSet = (SortedSet<E>) otherSet;
			int thisIndex = 0;
			int otherIndex = 0;
			while (thisIndex < myCon.size()
					&& otherIndex < otherSortedSet.myCon.size()) {
				E thisVal = myCon.get(thisIndex);// 0 1 3 4
				E otherVal = otherSortedSet.myCon.get(otherIndex);// 2 4 6 8
				int comparison = thisVal.compareTo(otherVal);
				if (comparison == 0) {
					// found an element, add it to new set
					otherIndex++;
					thisIndex++;
					result.add(thisVal);
				} else if (comparison < 0) {
					// havent found it yet, but still has a chance
					thisIndex++;
				} else {
					// element does not exist
					otherIndex++;
					thisIndex++;
				}
			}
		} else {
			for (E item : myCon) {
				if (otherSet.contains(item))
					result.add(item);
			}
		}
		return result;
	}
}