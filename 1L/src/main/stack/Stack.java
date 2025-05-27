/** CS515 Lab 1

File: Stack.java

Name: Huan Zhou(Rita Rand)

Section: 1

Date: 05/21/2025
*/
package stack;

import exceptions.*;

public class Stack {
    
    private final int INIT_CAP = 8; // Max number of elements in stack to start

    private int[] item;             // Dynamic integer array
    private int top;                // Index of stack top
    private int capacity;           // Current capacity of the stack

    /**
     * Constructor creates stack with default capacity.
     */
    public Stack() {
        this.capacity = INIT_CAP;
        this.top = 0;                   // Index to add to next
        this.item = new int[INIT_CAP];
    }

    /**
     * Constructor creates stack with the given capacity.
     */
    public Stack(int capacity) {
        this.capacity = capacity;
        this.top = 0;                   // Initialize top to 0, not capacity
        this.item = new int[capacity];
    }

    /**
     * Copy consructor creates a new stack which is a deep copy of given stack.
     * In other words, you can't just say item = s.item, you need to create
     * item as a separate array and then copy each int from s.item into item
     * @param s the stack to copy
     */
    public Stack(Stack s) {
        Stack newStack = new Stack(s.capacity());
        for (int i = 0; i < s.size(); i++) {
            newStack.push(s.item[i]);
        }
        this.item = newStack.item;
        this.top = newStack.top;
        this.capacity = newStack.capacity;
    }

    /**
     * Pushes an element on the stack. If stack full, double in size.
     * @param data the element to be pushed on stack
     */
    public void push(int data) {
        // Check if we need to resize the array
        if (this.top == this.capacity){
            this.capacity *= 2;
            int[] newArray = new int[this.capacity];
            for (int i = 0; i < this.top; i++) {
                newArray[i] = this.item[i];
            }
            this.item = newArray;
        }
        this.item[this.top] = data;
        this.top++;
    }

    /**
     * Pops the top element from the stack and returns it.
     * Throws EmptyStackException if no element to pop.
     * @return the top element in the stack
     */
    public int pop() throws EmptyStackException {
        if (this.empty()) { 
            throw new EmptyStackException("the stack is empty");
        } else {
            int topElement = this.item[this.top-1];
            this.top--;
            return topElement;
        }
    }

    /**
     * Returns the top element from the stack (not removed).
     * Throws EmptyStackException if no element to return.
     * @return the integer at the top of the stack
     */
    public int top() throws EmptyStackException {
        if (this.empty()) {
            throw new EmptyStackException("the stack is empty");
        } else {
            return this.item[this.top-1];
        }
    }

    /**
     * Returns whether or not the stack is empty.
     * @return true if stack is empty, false otherwise
     */
    public boolean empty() {
        return this.top == 0;
    }

    /**
     * Returns the number of elements currently on the top of the stack.
     * @return an integer repsenting how many elements are on stack
     */
    public int size() {
        if (this.empty()) {
            return 0;
        } else {
            return this.top;
        }
    }

    /**
     * Returns the capacity of the stack.
     * @return an integer representing the capcity of the stack
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * Equality operator override compares two stacks for equality.
     * @param otherStack the stack being compared to
     * @return true if stacks are equal, false otherwise
     */
    @Override
    public boolean equals(Object s) {
        if (!(s instanceof Stack)) {
            return false;
        }
        Stack otherStack = (Stack) s;
        if (this.size() != otherStack.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.item[i] != otherStack.item[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Builds a string based on the contents of the stack.
     * @return the string representing the stack
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Build string from stack top to bottom
        for (int i = this.top - 1; i >= 0; i--) {
            sb.append(this.item[i]);
            sb.append("\n");
        }
        
        return sb.toString();
    }

}
