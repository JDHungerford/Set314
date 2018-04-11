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

    }

    private ArrayList<E> mergeSort(ArrayList<E> other,int start, int end) {
        if (end - start > 1) {
            ArrayList<E> ar1 = mergeSort(other, start, end / 2);
            ArrayList<E> ar2 = mergeSort(other, end / 2, end);
        } else
    }

    private void merge(ArrayList<E> con, int start, int med, int end){
        
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



}