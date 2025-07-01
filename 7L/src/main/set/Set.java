/**
 * CS515 Assignment 6L
 *
 * Name: Huan Zhou(Rita)
 *
 * Section: 01
 *
 * Date: 06/30/2025
 *
 * Collaboration Declaration: 
 * Collaboration: None
 */

package set;


import java.util.function.Consumer;

public class Set<T extends Comparable<T>> {
    private Elem _root;
    private int _size; 

    public Set() {
        _size = 0;
        // create a dummy root node
        _root = new Elem();
        _root.left = _root;  // empty tree
        _root.right = null;
        _root.rightThread = false;
    }

    public boolean insert(T v) {
        if (_root.left == _root) {
            _root.left = createLeaf(v, null, _root);
            _size++;
            return true;
        }
        if (insert(_root.left, v, _root)){
            _size++;
            return true;
        }
        return false;
    }

    private boolean insert(Elem cur, T value, Elem lastLeft) {
        if (value.equals(cur.info)){
            return false;
        }
        // insert at left
        if (value.compareTo(cur.info) < 0) {
            if (cur.left == null) {
                cur.left = createLeaf(value, null, cur);
                return true;
            }
            // Else continue down the tree
            return insert(cur.left, value, cur);
        }
        // insert at right
        if (!cur.rightThread){
            // Continue down the tree
            return insert(cur.right, value, lastLeft);
        } else {  // current's right is a thread; add a new node
            cur.rightThread = false;
            cur.right = createLeaf(value, null, lastLeft);
            return true;
        }
    }

    private Elem createLeaf(T value, Elem left, Elem right) {
        Elem cur = new Elem();
        cur.info = value;
        cur.left = left;
        cur.right = right;
        cur.rightThread = true;
        return cur;
    }

    public String breadthFirst() {
        if (_size == 0) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        java.util.Queue<Elem> queue = new java.util.LinkedList<>();
        
        // Start with the actual root
        queue.add(_root.left);
        
        while (!queue.isEmpty()) {
            Elem current = queue.poll();
            
            // Add current node to result
            result.append(current.info);
            result.append(" ");
            
            // Add left child if it exists
            if (current.left != null) {
                queue.add(current.left);
            }
            
            // Add right child if it exists (not a thread)
            if (current.right != null && !current.rightThread) {
                queue.add(current.right);
            }
        }
        
        // Remove trailing space
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }
        
        return result.toString();
    }

    public String depthFirstInOrder() {
        if (_size == 0) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        Elem current = _root.left;
        
        // Find the leftmost node (smallest element)
        while (current.left != null) {
            current = current.left;
        }
        
        // Traverse using threads
        while (current != _root) {
            result.append(current.info);
            result.append(" ");
            
            // Move to next node using thread
            if (current.rightThread) {
                current = current.right;
            } else {
                // Move to leftmost node of right subtree
                current = current.right;
                while (current.left != null) {
                    current = current.left;
                }
            }
        }
        
        // Remove trailing space
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }
        
        return result.toString();
    }

    public void forEach(Consumer<? super T> action) {
        if (_size == 0) {
            return;
        }
        
        Elem current = _root.left;
        
        // Find the leftmost node (smallest element)
        while (current.left != null) {
            current = current.left;
        }
        
        // Traverse using threads and apply action
        while (current != _root) {
            action.accept(current.info);
            
            // Move to next node using thread
            if (current.rightThread) {
                current = current.right;
            } else {
                // Move to leftmost node of right subtree
                current = current.right;
                while (current.left != null) {
                    current = current.left;
                }
            }
        }
    }

    public int size() {
        return this._size;
    }

    public int height() {
        if (_size == 0) {
            return 0;
        }
        return height(_root.left);
    }

    // Post order traversal height of the tree
    public int height(Elem p) {
        if (p == null) {
            return 0;
        }
        
        // Post-order: calculate heights of children first
        int leftHeight = height(p.left);
        
        // For right child, check if it's a real child or a thread
        int rightHeight = 0;
        if (p.right != null && !p.rightThread) {
            rightHeight = height(p.right);
        }
        
        // Return max height of children + 1 (for current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // output the structure of tree. The tree is output as "lying down"
    // in which _root is the LEFT most Elem.
    public void printTree(int level, Elem p){
        int i;
        if (p != null) {
            if (p.right != null && !p.rightThread) {
                printTree(level+1, p.right);
            }
            for(i=0;i<level;i++) {
                System.out.print("\t");
            }
            System.out.println(p.info);
            printTree(level+1, p.left);
        }
    }

    // outputs information in tree in in order traversal order
    public void dump() {
        if (this._size != 0) { // tree non-empty
            printTree(0, _root.left);   // print tree structure
        }
    }

    private class Elem {
        T info;
        Elem left;
        Elem right;
        boolean rightThread;
        public Elem() {
            left = null;
            right = null;
            rightThread = false;
        }
    }
}
