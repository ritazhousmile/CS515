package funlist;

import java.util.NoSuchElementException;
import java.util.Objects;

import static funlist.List.cons;
import static funlist.List.nil;

public final class Cons<T> implements List<T> {

    private final T head;
    private List<T> tail; // non-final because of take and join

    Cons(T head, List<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public String toString() {
        if (tail.isEmpty()) {
            return "List(" + head + ")";
        }
        
        // Special handling for nested lists to get the format List(1,2,3,4,5)
        StringBuilder sb = new StringBuilder("List(");
        sb.append(head);
        
        List<T> current = tail;
        while (!current.isEmpty()) {
            sb.append(",");
            sb.append(((Cons<T>)current).head);
            current = ((Cons<T>)current).tail;
        }
        
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object any) {
        if (this == any) return true;
        if (!(any instanceof Cons<?> that)) return false;
        // Compare head values for equality
        if (!Objects.equals(this.head, that.head)) return false;      
        // Compare tail lists for equality
        return Objects.equals(this.tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }

    public T head() {
        return head;
    }

    public List<T> tail() {
        return tail;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean nonEmpty() {
        return true;
    }

    public int length() {
        int count = 1; // Count this node
        List<T> current = tail;
        
        // Count the rest of the nodes
        while (!current.isEmpty()) {
            count++;
            current = ((Cons<T>)current).tail;
        }
        
        return count;
    }

    public List<T> drop(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Cannot drop negative number of elements: " + count);
        }
        
        if (count == 0) {
            return this;
        }
        
        if (count >= length()) {
            return nil();
        }
        
        // Drop count elements
        List<T> current = this;
        for (int i = 0; i < count; i++) {
            current = ((Cons<T>)current).tail;
        }
        
        return current;
    }

    public List<T> reverse() {
        List<T> result = nil();
        List<T> current = this;
        
        // Reverse the list by adding each element to the front of the result
        while (!current.isEmpty()) {
            result = cons(((Cons<T>)current).head, result);
            current = ((Cons<T>)current).tail;
        }
        
        return result;
    }

    public T getAt(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative: " + index);
        }
        
        if (index == 0) {
            return head;
        }
        
        List<T> current = tail;
        int currentIndex = 1;
        
        // Find the element at the specified index
        while (!current.isEmpty()) {
            if (currentIndex == index) {
                return ((Cons<T>)current).head;
            }
            current = ((Cons<T>)current).tail;
            currentIndex++;
        }
        
        throw new NoSuchElementException("Index out of bounds: " + index);
    }

    public List<T> take(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Cannot take negative number of elements: " + count);
        }
        
        if (count == 0) {
            return nil();
        }
        
        if (count >= length()) {
            return this;
        }
        
        // Create a new list with the first count elements
        List<T> result = nil();
        List<T> current = this;
        
        for (int i = 0; i < count; i++) {
            result = cons(((Cons<T>)current).head, result);
            current = ((Cons<T>)current).tail;
        }
        
        // Reverse the result to maintain the original order
        return result.reverse();
    }

    public List<T> join(List<T> other) {
        if (other.isEmpty()) {
            return this;
        }
        
        // Create a new list by appending other to this
        List<T> result = nil();
        List<T> current = this;
        
        // Add all elements from this list
        while (!current.isEmpty()) {
            result = cons(((Cons<T>)current).head, result);
            current = ((Cons<T>)current).tail;
        }
        
        // Add all elements from the other list
        current = other;
        while (!current.isEmpty()) {
            result = cons(((Cons<T>)current).head, result);
            current = ((Cons<T>)current).tail;
        }
        
        // Reverse the result to maintain the original order
        return result.reverse();
    }

    public List<T> append(T value) {
        // Append is equivalent to joining with a single-element list
        return join(cons(value, nil()));
    }

    public T last() {
        if (tail.isEmpty()) {
            return head;
        }
        
        // Traverse to the last element
        List<T> current = this;
        while (!((Cons<T>)current).tail.isEmpty()) {
            current = ((Cons<T>)current).tail;
        }
        
        return ((Cons<T>)current).head;
    }
}
