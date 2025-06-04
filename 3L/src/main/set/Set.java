package set;
/**
 * CS515 Assignment 3L

*Name: Huan Zhou(Rita)

*Section: 01

*Date: 06/04/2025

Collaboration Declaration: 

Collaboration: None
*/

/**
 * Set implemented with doubly linked list and sentinel head & tail.
 * @param <T> the type being stored in the set
 */
public class Set<T extends Comparable<T>> {

    private Elem head, tail;
    private int size;

    /**
     * Constructs an empty set.
     */
    public Set() {
        this.head = new Elem();
        this.tail = new Elem();

        this.head.prev = null;
        this.head.next = tail;

        this.tail.prev = head;
        this.tail.next = null;

        this.size = 0;
    }

    /**
     * Copy constructor.
     * @param s the set to be copied
     */
    public Set(Set<T> s) {
        this.head = new Elem();
        this.tail = new Elem();

        this.head.prev = null;
        this.head.next = tail;

        this.tail.prev = head;
        this.tail.next = null;

        this.size = 0;

        for (Elem curr = s.head.next; curr != s.tail; curr = curr.next) {
            this.insert(curr.info);
        }
    }

    /**
     * Inserts an element and keeps the list sorted.
     * @param value the element to be added
     */
    public void insert(T value) {
        // Check if value already exists
        if (find(value)) {
            return;  // Value already exists, don't insert
        }
        
        Elem newElem = new Elem();
        newElem.info = value;
        
        // Find the position to insert (sorted order)
        Elem curr = head;
        while (curr.next != tail && curr.next.info.compareTo(value) < 0) {
            curr = curr.next;
        }
        
        // Insert the new element between curr and curr.next
        newElem.next = curr.next;
        newElem.prev = curr;
        curr.next.prev = newElem;
        curr.next = newElem;
        
        size++;
    }

    /**
     * Returns whether or not a given element is found in the set.
     * @param value the element being searched for
     * @return true if element found; false otherwise
     */
    public boolean find(T value) {
        for (Elem curr = head.next; curr != tail; curr = curr.next) {
            if (curr.info.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes an element from the set if found.
     * @param value the element to be removed
     */
    public void erase(T value) {
        for (Elem curr = head.next; curr != tail; curr = curr.next) {
            if (curr.info.equals(value)) {
                // Remove the element by adjusting the links
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                size--;
                return;
            }
        }
    }
    
    /**
     * Removes all the elements from the set.
     */
    public void clear() {
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * Returns the size of the set.
     * @return the size of the set
     */
    public int size() {
        return this.size;
    }

    /**
     * Equals method override.
     * @param o the object being compared to
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (!(o instanceof Set)) {
            return false;
        }  
        Set<T> otherSet = (Set<T>) o;
        
        if (this.size != otherSet.size) {
            return false;
        }
        
        for (Elem curr = head.next; curr != tail; curr = curr.next) {
            if (!otherSet.find(curr.info)) {
                return false;
            }
        }
        return true;
    }

    /**
     * toString method override for printing set contents.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Elem curr = this.head.next;
        while (curr != this.tail) {
            sb.append(curr.info);
            sb.append(" ");
            curr = curr.next;
        }
        return sb.toString();
    }

    /**
     * Inner class representing linked list node
     */
    private class Elem {
        private T info;
        private Elem prev, next;

        public Elem() {
            this.prev = null;
            this.next = null;
        }
    }
}
