
/** CS515 Lab 1

File: Queue.java

Name: Huan Zhou(Rita Rand)

Section: X

Date: 05/21/2025
*/
package queue;

import exceptions.EmptyQueueException;

/*
 * Circular array implementation of int queue.
 */
public class Queue {

    final int INIT_CAP = 10; // Max number of elements in queue to start
    
    private int[] item;      // Array of queue elements 
    private int front;       // Index of queue front
    private int rear;        // Index of queue rear
    private int size;        // Number of elements in queue
    private int capacity;    // Current capacity of the queue
    
    /**
     * Constructor creates queue with default capacity.
     */ 
    public Queue() {
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.capacity = INIT_CAP;
        this.item = new int[INIT_CAP];
    }

    /**
     * Constructor creates queue with the given capacity.
     */
    public Queue(int capacity) {
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.capacity = capacity;
        this.item = new int[capacity];
    }
    
    /**
     * Creates a queue identical to the one given.
     * @param q the queue to make a copy of
     */
    public Queue(Queue q) {
        this.front = 0;  // Reset front to 0 in the new queue
        this.rear = q.size - 1;  // Set rear based on size
        this.size = q.size;
        this.capacity = q.capacity;
        this.item = new int[q.capacity];
        
        // Copy elements, handling circular array
        for (int i = 0; i < q.size; i++) {
            this.item[i] = q.item[(q.front + i) % q.capacity];
        }
    }

    /**
     * Adds an element to the end of queue.
     * Precondition: queue is not full.
     * @param data an integer to add to the end of the queue
     */
    public void enqueue(int data) {
        if (this.full()) {
            // Double the capacity
            int newCapacity = this.capacity * 2;
            int[] newItems = new int[newCapacity];
            
            // Copy elements to new array
            for (int i = 0; i < this.size; i++) {
                newItems[i] = this.item[(this.front + i) % this.capacity];
            }
            
            this.item = newItems;
            this.front = 0;
            this.rear = this.size - 1;
            this.capacity = newCapacity;
        }
        
        this.rear = (this.rear + 1) % this.capacity;
        this.item[this.rear] = data;
        this.size++;
    }

    /**
     * Removes an element from the front of the queue.
     * Throws EmptyQueueException if no elems in queue.
     * @return the integer removed from the front of the queue
     */
    public int dequeue() throws EmptyQueueException {
        if (this.empty()) {
            throw new EmptyQueueException("Cannot dequeue from empty queue");
        }
        
        int value = this.item[this.front];
        this.front = (this.front + 1) % this.capacity;
        this.size--;
        return value;
    }

    /**
     * Builds a String based off the state of the queue.
     */
    public String toString() {
        if (this.empty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(this.item[(this.front + i) % this.capacity]);
        }
        return sb.toString();
    }

    /**
     * Returns the number of elements currently in the queue.
     * @return an integer representing the size of the queue
     */
    public int size() {
        //return this.size;
        //return (this.rear- this.front +1);
        return this.size;
    }

    /**
     * Returns whether or not the queue is empty.
     * @return true if queue is empty, false otherwise
     */
    public boolean empty() {
        return this.size == 0;
        
    }

    /**
     * Returns whether or not the queue is full.
     * @return true if queue is full, false otherwise
     */ 
    public boolean full() {

        //return this.size() >= this.INIT_CAP;
        return this.size >= this.capacity;
    }

    /**
     * Returns the current capacity of the queue.
     * @return an int representing the current queue capacity
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * Returns the element at the front of the queue (does not remove it).
     * Throws EmptyQueueException if no elements in queue.
     * @return the int at the front of the queue
     */
    public int front() throws EmptyQueueException {
        if (this.empty()) {
            throw new EmptyQueueException("Cannot get front of empty queue");
        }
        return this.item[this.front];
    }
}