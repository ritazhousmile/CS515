/**
 * CS515 Assignment 6L
 *
 * Name: Huan Zhou(Rita)
 *
 * Section: 01
 *
 * Date: 06/18/2025
 *
 * Collaboration Declaration: 
 * Collaboration: None
 */


package PQueue;

import java.lang.reflect.Array;

public class PQueue<T extends Comparable<T>> {
    int _size;
    T[] _array;

    // construct an empty binary heap.
    @SuppressWarnings("unchecked")
    public PQueue(Class<T> classRef, int capacity) {
        _array = (T[]) Array.newInstance(classRef, capacity + 1);
        //TODO : Implement
        _size = 0;
    }

    // construct a binary heap from an array of element
    // assume the array size is smaller than capacity
    @SuppressWarnings("unchecked")
    public PQueue(Class<T> classRef, int capacity, T[] items){
        _array = (T[]) Array.newInstance(classRef, capacity + 1);
        //TODO: Implement
        _size = 0;
    }   


    // insert item into the priority queue; duplicates are allowed.
    public void insert(T x){
        //TODO: Implement
        _array[_size + 1] = x;
        _size++;
        moveUp();
    }

    // return the smallest item in the priority queue.
    public T findMin(){
        return _array[1];
    }

    // remove the smallest item from the priority queue
    // Would ideally throw UnderflowException if empty,
    // but not necessary for this lab.
    public void deleteMin(){
        //TODO: Implement
        _array[1] = _array[_size];
        _size--;
        moveDown(1);
    }

    // return queue size
    public int size() {
        return _size;
    }

    // test if the priority queue is logically empty.
    // return true if empty, false otherwise.
    public boolean isEmpty(){
        return _size == 0;
    }

    // private method to move up the last item in the heap.
    private void moveUp(){
        //TODO: Implement
        int curIndex = _size;
        while (curIndex > 1 && _array[curIndex].compareTo(_array[curIndex / 2]) < 0) {
            T temp = _array[curIndex];
            _array[curIndex] = _array[curIndex / 2];
            _array[curIndex / 2] = temp;
            curIndex = curIndex / 2;
        }
    }


    // Establish heap-order property from an arbitrary
    // arrangement of items. Runs in linear time.
    private void buildHeap(){
        //TODO: Implement
        for (int i = _size / 2; i >= 1; i--) {
            moveDown(i);
        }
    }

    // private method to move down in the heap.
    // curIndex is the index at which the move down begins.
    private void moveDown(int curIndex){
        //TODO: Implement
        int childIndex = curIndex * 2;
        while (childIndex <= _size) {
            // Find the smaller child
            if (childIndex < _size && _array[childIndex].compareTo(_array[childIndex + 1]) > 0) {
                childIndex++;
            }
            
            // If current node is smaller than or equal to its smallest child, we're done
            if (_array[curIndex].compareTo(_array[childIndex]) <= 0) {
                break;
            }
            
            // Swap current node with its smallest child
            T temp = _array[curIndex];
            _array[curIndex] = _array[childIndex];
            _array[childIndex] = temp;
            
            // Move down to the child
            curIndex = childIndex;
            childIndex = curIndex * 2;
        }
    }
}
