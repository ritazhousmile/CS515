/** CS515 Lab 2

File: QueueTest.java

Name: huan zhou Rita 

Section: 01

Date: 5/22/2025


*/
import javax.management.RuntimeErrorException;

public class Queue<T> {

    private Node _front;
    private Node _rear;
    private int _size;

    private class Node {
        T data;
        Node next;

        public Node(T e) {
            this.data = e;
        }

        public Node(T e, Node n) {
            this(e);
            this.next = n;
        }
    }

    public Queue() {
        this._front = null;
        this._rear = null;
        this._size = 0;
    }

    public Queue(Queue<T> q) {
        // To Implement
        this();
        Node curNode = q._front;
        while (curNode != null) {
            this.enqueue(curNode.data);
            curNode = curNode.next;
        }
    }

    public void enqueue(T e) {
        // To Implement
        Node newNode = new Node(e);
        if(_rear != null) {
            _rear.next = newNode;
        } else {
            _front = newNode;
        }
        _rear = newNode;
        _size++;
    }

    public T dequeue() {
        // To Implement
        if(_front == null) throw new EmptyQueueException ("Queue is empty");
        T d = _front.data;
        _front = _front.next;
        if(_front == null) {
            _rear = null;
        }
        _size--;
        return d;

        
    }

    public int size() {
        // To Implement
        return _size;
    }

    public boolean isEmpty() {
        // To Implement
        return _size == 0;
    }

    public String toString() {
        // To Implement
        StringBuilder sb = new StringBuilder();
        Node curNode = _front;
        while (curNode != null) {
            sb.append(curNode.data).append(" ");
            curNode = curNode.next;
        }
            return sb.toString().trim();
    }
}
