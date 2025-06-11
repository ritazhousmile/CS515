package recfunlist;

import java.util.NoSuchElementException;
import static recfunlist.List.cons;

public final class Nil<T> implements List<T> {

    // Must be defined here and not in List to avoid class initialization deadlocks.
    final static Object TheNil = new Nil<Void>();

    private Nil() {
        // make Nil a singleton; equals and hashCode not redefined
        // INFO: Please do not change me :)
    }

    @Override
    public String toString() {
        return "List()";
    }

    public T head() {
        throw new NoSuchElementException("Cannot get head of an empty list");
    }

    public List<T> tail() {
        throw new NoSuchElementException("Cannot get tail of an empty list");
    }

    public T last() {
        throw new NoSuchElementException("Cannot get last element of an empty list");
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean nonEmpty() {
        return false;
    }

    public int length() {
        return 0;
    }

    public List<T> take(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Cannot take negative number of elements");
        }
        return this;
    }

    public List<T> drop(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Cannot drop negative number of elements");
        }
        return this;
    }

    public List<T> reverse() {
        return this;
    }

    public T getAt(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative: " + index);
        }
        throw new NoSuchElementException("Cannot get element at index " + index + " from empty list");
    }

    public List<T> join(List<T> other) {
        return other;
    }

    public List<T> append(T value) {
        return cons(value, this);
    }
}
