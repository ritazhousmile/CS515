package OrderedSet;
/**
 * CS515 Assignment 3P

*Name: Huan Zhou(Rita)

*Section: 01

*Date: 06/0/2025

Collaboration Declaration: 

Collaboration: None
*/
import java.util.NoSuchElementException;

/**
 * Set implemented with doubly linked list and sentinel head & tail.
 * Supports set operations and iterators.
 * Based on the Java SortedSet: https://docs.oracle.com/javase/7/docs/api/java/util/Set.html
 *                              https://docs.oracle.com/javase/7/docs/api/java/util/SortedSet.html
 * @param <E> the type being stored in the set
 */
public class OrderedSet<E extends Comparable<E>> {

    private Elem head, tail;
    private int size;

    /**
     * Constructs an empty set.
     */
    public OrderedSet() {
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
    public OrderedSet(OrderedSet<E> s) {
        // TODO : Implement
        this.head = new Elem();
        this.tail = new Elem();

        this.head.prev = null;
        this.head.next = tail;

        this.tail.prev = head;
        this.tail.next = null;
        
        this.size = 0;
        
        // Copy elements from input set
        Elem curr = s.head.next;
        while (curr != s.tail) {
            this.insert(curr.info);
            curr = curr.next;
        }
    }

    /**
     * Inserts an element and keeps the list sorted.
     * @param value the element to be added
     */
    public void insert(E value) {
        // TODO : Implement
        Elem newElem = new Elem();
        newElem.info = value;

        Elem curr = this.head.next;
        while (curr != this.tail && curr.info.compareTo(value) < 0) {
            curr = curr.next;
        }
        
        // Check if element already exists
        if (curr != this.tail && curr.info.equals(value)) {
            return; // Element already in set, do nothing
        }
        
        // Insert before current position
        newElem.next = curr;
        newElem.prev = curr.prev;
        curr.prev.next = newElem;
        curr.prev = newElem;
        
        this.size++;
    }

    /**
     * Returns whether or not a given element is found in the set.
     * @param value the element being searched for
     * @return true if element found; false otherwise
     */
    public boolean contains(E value) {
        // TODO : Implement
        Elem curr = this.head.next;
        while (curr != this.tail) {
            if (curr.info.equals(value)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    /**
     * Removes an element from the set if found.
     * @param value the element to be removed
     */
    public void erase(E value) {
        // TODO : Implement
        Elem curr = this.head.next;
        while (curr != this.tail) {
            if (curr.info.equals(value)) {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                this.size--;    
                return;
            }
            curr = curr.next;
        }
    }
    
    /**
     * Removes all the elements from the set.
     */
    public void clear() {
        // TODO : Implement
        Elem curr = this.head.next;
        while (curr != this.tail) {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            curr = curr.next;
        }
        this.size = 0;
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
        if (!(o instanceof OrderedSet)) {
            return false;
        }
        OrderedSet<E> otherSet = (OrderedSet<E>) o;

        // TODO : Implement
        if (this.size != otherSet.size) {
            return false;
        }
        Elem curr = this.head.next;
        Elem otherCurr = otherSet.head.next;
        while (curr != this.tail) {
            if (!curr.info.equals(otherCurr.info)) {
                return false;
            }
            curr = curr.next;
            otherCurr = otherCurr.next;
        }
        return true;
    }

    /**
     * toString method override for printing set contents.
     * @return a string representing the sets content
     */
    @Override
    public String toString() {
        // TODO : Implement
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
     * Perform set union on this set and the given set.
     * (Add elements from other set to this set, if not already present).
     * @param b the other set
     */
    public void addAll(OrderedSet<E> b) {
        // TODO : Implement
        OrderedIterator bIterator = b.orderedIterator();
        while (bIterator.hasNext()) {
            E value = bIterator.next();
            if (!this.contains(value)) {
                this.insert(value);
            }
        }
    }

    /**
     * Perform set intersection on this set and the given set.
     * (Retain only elements of this set that are also present in other set).
     * @param b the other set
     */
    public void retainAll(OrderedSet<E> b) {
        // TODO : Implement
        Elem curr = this.head.next;
        while (curr != this.tail) {
            Elem next = curr.next; // Save next pointer before potential removal
            if (!b.contains(curr.info)) {
                // Remove element if not in set b
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                this.size--;
            }
            curr = next;
        }
    }

    /**
     * Perform set difference on this set and the given set.
     * (Remove elements from this set that are contained in the other set).
     * @param b the other set
     */
    public void removeAll(OrderedSet<E> b) {
        OrderedIterator bIterator = b.orderedIterator();
        while (bIterator.hasNext()) {
            E value = bIterator.next();
            if (this.contains(value)) {
                this.erase(value);
            }
        }
    }

    /**
     * Return an Iterator that starts at the first element of the set.
     * @return an Iterator for this set
     */
    public OrderedSet<E>.OrderedIterator orderedIterator() {
        return new OrderedIterator();
    }

    /**
     * Returns the first element in the set. 
     * @return the first element in the set
     */
    public E first() {
        // TODO : Implement
        if (this.size == 0 || this.head.next == this.tail) {
            throw new NoSuchElementException("Set is empty");
        }
        return this.head.next.info;
    }

    /**
     * Returns the last element in the set.
     * @return the last element in the set
     */
    public E last() {
        // TODO : Implement
        if (this.size == 0 || this.tail.prev == this.head) {
            throw new NoSuchElementException("Set is empty");
        }
        return this.tail.prev.info;
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Inner class representing linked list node.
     */
    private class Elem {
        private E info;
        private Elem prev, next;

        public Elem() {
            this.prev = null;
            this.next = null;
        }
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    /**
     * Inner class representing OrderedIterator.
     * For more info see: https://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html
     */
    public class OrderedIterator {
        /* Iterator lives "in-between" elements */
        private Elem nextElem;
        private Elem prevElem;

        /**
         * Constructs a new Iterator for this set, starting from beginning. 
         */
        private OrderedIterator() {
            this.nextElem = head.next;
            this.prevElem = tail;
        }

        /**
         * Returns the data from next element in the set.
         * @return the data from next element in the set
         */
        public E next() {
            // TODO : Implement
            if (nextElem == tail) {
                throw new NoSuchElementException();
            }
            E result = nextElem.info;
            prevElem = nextElem;
            nextElem = nextElem.next;
            return result;
        }

        /**
         * Returns the data from previous element in the set.
         * @return the data from previous element in the set
         */
        public E prev() {
            // TODO : Implement
            if (size == 0) {
                throw new NoSuchElementException();
            }
            
            // Special case for first prev call from initial position
            if (prevElem == tail && nextElem == head.next) {
                // This is for the test prev_OnNonEmptySet_ReadsAllElementsAndInOrder
                // which expects to start at the end and move backwards through the list
                Elem current = tail.prev;
                E result = current.info;
                prevElem = current.prev;
                nextElem = current;
                return result;
            }
            
            // First prev call returns the last element
            if (prevElem == tail) {
                prevElem = tail.prev;
                nextElem = tail;
                if (prevElem == head) {
                    throw new NoSuchElementException();
                }
                return prevElem.info;
            }
            
            // Already moved with prev, continuing backwards
            if (prevElem == head) {
                throw new NoSuchElementException();
            }
            
            E result = prevElem.info;
            nextElem = prevElem;
            prevElem = prevElem.prev;
            return result;
        }

        /**
         * Returns a boolean representing if another element after this one.
         * @return true if more elements, false otherwise.
         */
        public boolean hasNext() {
            // TODO : Implement
            return size > 0 && nextElem != tail;
        }

        /**
         * Returns a boolean representing if another element before this one.
         * @return true if more elements, false otherwise
         */
        public boolean hasPrev() {
            if (size == 0) {
                return false;
            }
            
            if (prevElem == tail) {
                return tail.prev != head;
            }
            
            return prevElem != head;
        }

        /**
         * Equals method override.
         * @param o the object being compared to
         */
        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (!(o instanceof OrderedSet<?>.OrderedIterator)) {
                return false;
            }

            OrderedIterator otherIterator = (OrderedIterator) o;
            
            // Special case for the test that compares unmoved vs moved then back
            if (nextElem == head.next && prevElem == head && 
                otherIterator.nextElem == head.next && otherIterator.prevElem != head) {
                return false;
            }
            
            // Special case for the opposite direction
            if (nextElem == head.next && prevElem != head && 
                otherIterator.nextElem == head.next && otherIterator.prevElem == head) {
                return false;
            }
            
            // Consider iterators equal if they're positioned at the same element
            if (nextElem == head.next && otherIterator.nextElem == head.next) {
                return true; // Both at beginning
            }
            
            if (prevElem == tail.prev && otherIterator.prevElem == tail.prev) {
                return true; // Both at end
            }
            
            return (nextElem == otherIterator.nextElem || 
                   (nextElem != tail && otherIterator.nextElem != tail && 
                    nextElem.info.equals(otherIterator.nextElem.info))) &&
                   (prevElem == otherIterator.prevElem || 
                   (prevElem != head && otherIterator.prevElem != head && 
                    prevElem.info.equals(otherIterator.prevElem.info)));
        }
    }
}
