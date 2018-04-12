/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, <NAME1> and <NAME2), this programming assignment is <MY|OUR> own work
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
 *  Student 2
 *  UTEID:
 *  email address:
 *  Grader name:
 *  Section number:
 *
 */

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are 
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Students are to implement methods that were not implemented in AbstractSet 
 * and override methods that can be done more efficiently. An ArrayList must 
 * be used as the internal storage container. For methods involving two set, 
 * if that method can be done more efficiently if the other set is also a 
 * SortedSet do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;
    private int size;

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
        myCon = new ArrayList<>();
    }

    public Iterator<E> iterator() {
        return myCon.iterator();
    }

    /**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise
     */
    public boolean remove(E item) {
        Iterator<E> removeIt = this.iterator();
        boolean removed = false;
        while (removeIt.hasNext() && !removed) {
            if (removeIt.next().equals(item)) {
                removed = true;
                size--;
            }
        }
        return removed;
    }

    /**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    public boolean add(E item){
        if (item == null){
            throw new IllegalArgumentException("Parameter must not be null.");
        }
        if (!contains(item)){
            int index = 0;
            while (item.compareTo(myCon.get(index)) < 0 && index < myCon.size()){
                index++;
            }
            myCon.add(index, item);
            size++;
            return true;
        }
        return false;
    }

    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    public void clear(){
        myCon =  new ArrayList<>();
        size = 0;
    }

    /**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    public int size(){
        return size;
    }




    /**
     * A union operation. Add all items of otherSet that are not already present in this set
     * to this set.
     * @param otherSet != null
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    public boolean addAll(ISet<E> otherSet){
       if (otherSet == null)
           throw new IllegalArgumentException("OtherSet must not == null.");
       if (otherSet instanceof SortedSet){
           SortedSet<E> otherSortedSet = (SortedSet<E>) otherSet;
           ArrayList<E> temp = new ArrayList<>(this.size() + otherSet.size());
           int newSize = merge(otherSortedSet, temp);
           myCon = temp;
           if (size() != newSize) {
               size = newSize;
               return true;
           } else {
               return false;
           }
       } else {
           int oldSize = size;
           for (E item : otherSet) {
               add(item);
           }
           return oldSize != size;
       }
    }


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
     * Determine if item is in this set.
     * <br>pre: item != null
     * @param item element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item){
        return binSearch(0, myCon.size() - 1, item);
    }

    private boolean binSearch(int min, int max, E val){
        if (max < min){
            return false;
        }
        int mid = (min + max) / 2;
        if (val == myCon.get(mid)){
            return true;
        }
        if (val.compareTo(myCon.get(mid)) < 0){
            return binSearch(min, mid - 1, val);
        }else{
            return binSearch(mid + 1, max, val);
        }
    }

    /**
     * create a SortedSet out of an unsorted set. <br>
     * @param other != null
     */
    public SortedSet(ISet<E> other) {
        if (other == null){
            throw new IllegalArgumentException("Parameter must not be null");
        }
        if (other instanceof UnsortedSet){
            SortedSet<E> otherSortedSet = (SortedSet<E>) other;
            ArrayList<E> copy = new ArrayList<>(otherSortedSet.myCon);
            mergeSort(copy, new ArrayList<>(), 0, copy.size() - 1);
            myCon = copy;

        }

    }

    //merge sort alg altered from Mike Scott's PDF
    private void mergeSort(ArrayList<E> list, ArrayList<E> temp,
                             int start, int stop) {
        if( start < stop) {
            int center = (start + stop) / 2;
            mergeSort(list, temp, start, center);
            mergeSort(list, temp, center + 1, stop);
            merge(list, temp, start, center + 1, stop);
        }
    }

    private void merge(ArrayList<E> list, ArrayList<E> temp,
                               int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while( leftPos <= leftEnd && rightPos <= rightEnd){
            if( list.get(leftPos).compareTo(list.get(rightPos)) <= 0) {
                temp.set(tempPos, list.get(leftPos));
                leftPos++;
            }
            else{
                temp.set(tempPos, list.get(rightPos));
                rightPos++;
            }
            tempPos++;
        }
        //copy rest of left half
        while( leftPos <= leftEnd){
            temp.set(tempPos, list.get(leftPos));
            tempPos++;
            leftPos++;
        }
        //copy rest of right half
        while( rightPos <= rightEnd){
            temp.set(tempPos, list.get(rightPos));
            tempPos++;
            rightPos++;
        }
        //Copy temp back into list
        for(int i = 0; i < numElements; i++, rightEnd--)
            list.set(rightEnd, temp.get(rightEnd));
    }

    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        if (size == 0){
            throw new IllegalStateException("Set has zero elements.");
        }else{
            return myCon.get(0);
        }

    }

    /**
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    public ISet<E> union(ISet<E> otherSet){
        if (otherSet == null){
            throw new IllegalArgumentException("otherSet can't be null");
        }
        SortedSet<E> newSet = new SortedSet<>();
        newSet.addAll(this);
        newSet.addAll(otherSet);
        return newSet;
    }

    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     */
    public E max() {
        if (size == 0){
            throw new IllegalStateException("Set has zero elements.");
        }else{
            return myCon.get(myCon.size() - 1);
        }
    }

    /**
     * Create a new set that is the difference of this set and otherSet. Return an ISet of
     * elements that are in this Set but not in otherSet. Also called
     * the relative complement.
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] then
     * A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W].
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    public ISet<E> difference(ISet<E> otherSet){
        return new SortedSet<>();
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet,
     * false otherwise.
     */
    public boolean containsAll(ISet<E> otherSet){
        if (otherSet instanceof SortedSet){
            SortedSet<E> otherSortedSet = (SortedSet<E>) otherSet;
            int thisIndex = 0;
            int otherIndex = 0;
            while(thisIndex < myCon.size() && otherIndex < otherSortedSet.myCon.size()){
                E thisVal = myCon.get(thisIndex);// 0 1 3 4
                E otherVal = otherSortedSet.myCon.get(otherIndex);//2 4 6 8
                int comparison = thisVal.compareTo(otherVal);
                if (comparison == 0){
                    //found an element, move on
                    otherIndex++;
                    thisIndex++;
                }else if (comparison < 0){
                    //havent found it yet, but still has a chance
                    thisIndex++;
                } else{
                    //element does not exist
                    return false;
                }
            }
            //check one last time if we run out of elements
            return otherIndex == otherSortedSet.myCon.size();
        }else{
            //otherSet is a unsorted set so just use the parent code
            return super.containsAll(otherSet);
        }
    }





}