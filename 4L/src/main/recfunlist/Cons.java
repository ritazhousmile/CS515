package recfunlist;
/**
 * CS515 Assignment 4L

*Name: Huan Zhou(Rita)

*Section: 01

*Date: 06/10/2025

Collaboration Declaration: 

Collaboration: None
*/
import static recfunlist.List.cons;
import static recfunlist.List.nil;

public final class Cons<T> implements List<T> {

    private final T head;
    private final List<T> tail;

    Cons(T head, List<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public int hashCode() {
        return 19 * tail.hashCode() + head.hashCode();
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

    public List<T> append(T value) {
        return join(cons(value, nil()));
    }

    @Override
    public boolean equals(Object any) {

        if( !(any instanceof Cons<?>)) {
            return false;
        }

        Cons<?> list = (Cons<?>)any;

        return this.head().equals(list.head()) && this.tail().equals(list.tail());
    }

    // STEP 1
    public int length() {
        // TODO: Implement
        return 1 + tail.length();
    }

    // STEP 2
    public T last() {
        // TODO: Implement
        if (tail.isEmpty()) {
            return head;
        }
        return tail.last();
    }

     // STEP 3
    public T getAt(int index) {
        // TODO: Implement
        if (index == 0) {
            return head;
        }
        return tail.getAt(index - 1);
    }

    // STEP 4
    public List<T> join(List<T> other) {
        // TODO: Implement
        return cons(head, tail.join(other));
    }

    // STEP 5
    public List<T> take(int count) {
        // TODO: Implement
        return count == 0 ? nil() : cons(head, tail.take(count - 1));
    }

    // STEP 5 helper
    private List<T> takeHelper(List<T> l, int count) {
        // TODO: Implement
        return l.isEmpty() || count <= 0 ? nil() : cons(l.head(), takeHelper(l.tail(), count - 1));
    }

    // STEP 6
    public List<T> drop(int count) {
        // TODO: Implement
        if (count < 0) {
            throw new IllegalArgumentException("Cannot drop negative number of elements");
        }
        return count == 0 ? this : dropHelper(this.tail(), count - 1);
    }

    // STEP 6 helper
    private List<T> dropHelper(List<T> l, int count) {
        // TODO: Implement
        return l.isEmpty() || count <= 0 ? l : dropHelper(l.tail(), count - 1);
    }

    // STEP 7
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        // TODO: Implement
        str.append("List(");
        toStringHelper(this, str);
        str.append(")");
        return str.toString();
    }

    // STEP 7 helper
    private StringBuilder toStringHelper(List<T> list, StringBuilder str) {
        // TODO: Implement
        if (list.isEmpty()) {
            return str;
        }
        str.append(list.head());
        if (!list.tail().isEmpty()) {
            str.append(",");
        }
        return toStringHelper(list.tail(), str);
    }

    // STEP 8
    public List<T> reverse() {
        // TODO: Implement
        return reverseHelper(this, nil());
    }

    // STEP 8 helper
    private List<T> reverseHelper(List<T> list, List<T> rev) {
        // TODO: Implement
        return list.isEmpty() ? rev : reverseHelper(list.tail(), cons(list.head(), rev));
    }
}
