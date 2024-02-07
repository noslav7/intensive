package com.astondev.hw1;

import java.util.*;

/**
 * Implementation of List interface in shape of resizable array. Capable of storing elements of any single type,
 * including null. In addition to implementing the List interface, this class provides methods to
 * manipulate the size of the array that is used internally to store the list.
 * Methods size() and get() run in constant time. The add() method runs in amortized constant time, which means,
 * adding n elements requires O(n) time. All other public methods approximately run in
 * linear time O(n).
 * Each instance of ArrayList has a capacity. The capacity is the size of the array used to store the elements
 * in the list. It is always at least as large as the list size. As elements are added to an ArrayList, its
 * capacity grows automatically. Adding an element has constant amortized time cost O(n).
 */
public class ArrayList<E> extends AbstractList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    public static final String MSG_PATTERN = "Illegal Capacity: %d";
    Object[] elementData;
    private int size;

    /**
     * Constructs an empty list with an initial capacity of ten elements.
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     * Throws: IllegalArgumentException if the specified initial capacity is negative
     * @param initialCapacity – the initial capacity of the list
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException(String.format(MSG_PATTERN, initialCapacity)); // так лучше
        }
    }

    /**
     * Constructs a list containing the elements of the specified collection, in the order they are
     * returned by the collection's iterator.
     * Throws: NullPointerException if the specified collection is null.
     * @param c – the collection whose elements are to be placed into this list.
     */
    public ArrayList(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == java.util.ArrayList.class) {
                elementData = a;
            } else {
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            elementData = EMPTY_ELEMENTDATA;
        }
    }


    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the element at the specified position in this list.
     * Throws: IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     * @param index - index of the element to return
     * @return the element at the specified position in this list
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) elementData[index];
    }

    /**
     * Appends the specified element to the end of this list.
     * @param e – element to be appended to this list
     * @return true (as specified by Collection.add)
     */
    public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently
     * at that position (if any) and any subsequent elements to the right (adds one to their indices).
     * Throws: IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, s - index);
        elementData[index] = element;
        size = s + 1;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if the element is present.
     * If the list does not contain the element, it is unchanged. More formally, removes the element with
     * the lowest index i such that Objects.equals(o, get(i)) (if such an element exists). Returns true if
     * this list contained the specified element (or equivalently, if this list changed as a result of the
     * method call).
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    public boolean remove(Object o) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        found: {
            for (; i < size; i++) {
                if (Objects.equals(es[i], o)) {
                    break found;
                }
            }
            return false;
        }
        fastRemove(es, i);
        return true;
    }

    /**
     * Removes all elements from this list. The list will be empty after this call returns.
     */
    public void clear() {
        modCount++;
        final Object[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = null;
    }

    /**
     * Trims the capacity of this ArrayList instance to be the list's current size.
     * An application can use this operation to minimize the storage of an ArrayList instance.
     */
    public void trimToSize() {
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * Sorts the ArrayList in an increasing order.
     * @param low - the lowest index in the ArrayList to be sorted
     * @param high - the highest index in the ArrayList to be sorted
     */
    public void quickSort(int low, int high) {
        if (elementData.length == 0 || low >= high) return;

        int middle = low + (high - low) / 2;
        int border = (int) elementData[middle];

        int i = low, j = high;
        while (i <= j) {
            while ((int) elementData[i] < border) i++;
            while ((int) elementData[j] > border) j--;
            if (i <= j) {
                int swap = (int) elementData[i];
                elementData[i] = elementData[j];
                elementData[j] = swap;
                i++;
                j--;
            }
        }

        if (low < j) quickSort(low, j);
        if (high > i) quickSort(i, high);
    }



    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length) // не забывай про {} это улучшает читаемость
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = oldCapacity * 3 / 2 + 1;
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private void fastRemove(Object[] es, int i) {
        modCount++;
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+ index + ", Size: " + size();
    }
}
