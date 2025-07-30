/**
 * CS515 Assignment 9P
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

import java.util.Iterator;
import java.util.TreeMap;
import java.util.NavigableMap;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.NoSuchElementException;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class Map<K extends Comparable<K>, V> implements Cloneable {

    private class Elem implements Entry<K,V> {
        K key;
        V value;
        Elem left;
        Elem right;
        int height;
        boolean rightThread;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V v) {
            V old = value;
            value = v;
            return old;
        }

        public String toString() {
            return String.format("%s %s(%d)", key, value, height);
        }
    }

    private Elem _root;
    private int _size;

    public Map() {
        _root = new Elem();
        _root.left = null;  // Fixed: was causing circular reference!
        _root.right = null;
        _root.rightThread = false;
        _size = 0;
    }

    public Map(Map<K, V> v) {
        // Initialize empty map
        _root = new Elem();
        _root.left = null;  // Fixed: avoid circular reference
        _root.right = null;
        _root.rightThread = false;
        _size = 0;
        
        // Copy all elements from source map using our working iterator
        if (v._size > 0) {
            Iterator<Entry<K,V>> it = v.iterator();
            while (it.hasNext()) {
                Entry<K,V> entry = it.next();
                insert(entry.getKey(), entry.getValue());
            }
        }
    }

    private void addToMap(Elem root, TreeMap<K, Elem> keyElemMap) {
        if(root != null && root != _root) {
            keyElemMap.put(root.key, root);
            addToMap(root.left, keyElemMap);
            if(!root.rightThread && root.right != _root) {
                addToMap(root.right, keyElemMap);
            }
        }
    }

    private void copyThread(Elem newRoot, Elem origRoot) {
        TreeMap<K, Elem> newKeyElemMap = new TreeMap<>();
        TreeMap<K, Elem> origKeyElemMap = new TreeMap<>();

        addToMap(newRoot.left, newKeyElemMap);
        addToMap(origRoot.left, origKeyElemMap);

        NavigableMap<K, Elem> revMap = origKeyElemMap.descendingMap();

        Iterator<NavigableMap.Entry<K,Elem>> it = revMap.entrySet().iterator();
        Entry<K, Elem> e = it.next();
        Elem temp = newKeyElemMap.get(e.getKey());
        temp.rightThread = true;
        temp.right = newRoot;

        while(it.hasNext()) {
            e = it.next();
            Elem o = origKeyElemMap.get(e.getKey());
            if(o.rightThread) {
                temp = newKeyElemMap.get(e.getKey());
                temp.rightThread = true;
                temp.right = newKeyElemMap.get(origKeyElemMap.get(e.getKey()).right.key);
            }
        }
    }

    // AVL helper methods
        private int height(Elem node) {
    if (node == null || node == _root) return -1;
    return node.height;
}

        private void updateHeight(Elem node) {
    if (node != null && node != _root) {
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }
}

            // Helper method to find a node - simplified without threading
    private Elem findNode(K key) {
        if (key == null) return null;
        
        Elem current = _root.left;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                return current;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    // Main insert method with AVL balancing
    public boolean insert(K k, V v) {
        if (k == null) return false;
        
        // If tree is empty
        if (_root.left == null) {
            Elem newNode = new Elem();
            newNode.key = k;
            newNode.value = v;
            newNode.left = null;
            newNode.right = null;
            newNode.rightThread = false;
            newNode.height = 0;
            _root.left = newNode;
            _size++;
            return true;
        }
        
        boolean[] inserted = {false};
        _root.left = insertAVL(_root.left, k, v, inserted);
        
        if (inserted[0]) {
            _size++;
            // Note: Threading rebuild disabled since we're not using threading
        }
        
        return inserted[0];
    }

            // Simple AVL insertion helper without threading complexity
    private Elem insertAVL(Elem node, K key, V value, boolean[] inserted) {
        // Base case: reached null
        if (node == null) {
            Elem newNode = new Elem();
            newNode.key = key;
            newNode.value = value;
            newNode.left = null;
            newNode.right = null;
            newNode.rightThread = false;
            newNode.height = 0;
            inserted[0] = true;
            return newNode;
        }

    int cmp = key.compareTo(node.key);
    
    if (cmp == 0) {
        // Key already exists, do not update (per assignment requirements)
        inserted[0] = false;
        return node;
    } else if (cmp < 0) {
        node.left = insertAVL(node.left, key, value, inserted);
    } else {
        node.right = insertAVL(node.right, key, value, inserted);
    }

        if (!inserted[0]) {
            return node;
        }

        // Update height
        updateHeight(node);

        // Check balance and perform rotations if needed
        int balance = getBalance(node);

        // Left heavy cases
        if (balance > 1) {
            if (getBalance(node.left) >= 0) {
                // Left-Left case
                node = rotateRight(node);
            } else {
                // Left-Right case
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }
        // Right heavy cases
        else if (balance < -1) {
            if (getBalance(node.right) <= 0) {
                // Right-Right case
                node = rotateLeft(node);
            } else {
                // Right-Left case
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }

        return node;
    }

    // Get balance factor
    private int getBalance(Elem node) {
        if (node == null || node == _root) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return leftHeight - rightHeight;
    }

    // AVL rotation methods - simplified without threading
    private Elem rotateRight(Elem y) {
        if (y == null || y.left == null || y.left == _root) return y;
        
        Elem x = y.left;
        
        // Perform rotation
        y.left = x.right;
        x.right = y;
        
        // Update heights
        updateHeight(y);
        updateHeight(x);
        
        return x;
    }

    private Elem rotateLeft(Elem x) {
        if (x == null || x.right == null || x.right == _root) return x;
        
        Elem y = x.right;
        
        // Perform rotation
        x.right = y.left;
        y.left = x;
        
        // Update heights
        updateHeight(x);
        updateHeight(y);
        
        return y;
    }

    // Rebuild all threading after rotations
    private void rebuildAllThreading() {
        if (_root.left == _root) return;
        
        // Step 1: Clear all threading flags and collect nodes
        java.util.List<Elem> allNodes = new java.util.ArrayList<>();
        clearThreadingAndCollect(_root.left, allNodes);
        
        // Step 2: Sort nodes by key to get inorder sequence
        allNodes.sort((a, b) -> a.key.compareTo(b.key));
        
        // Step 3: Set up threading for nodes that need it
        for (int i = 0; i < allNodes.size(); i++) {
            Elem current = allNodes.get(i);
            
            // If this node has no real right child, set up threading
            if (current.right == null || current.right == _root || !hasRealRightChild(current)) {
                current.rightThread = true;
                if (i == allNodes.size() - 1) {
                    current.right = _root; // Last node points to root
                } else {
                    current.right = allNodes.get(i + 1); // Point to successor
                }
            }
        }
    }

    private void clearThreadingAndCollect(Elem node, java.util.List<Elem> nodes) {
        if (node == null || node == _root) return;
        
        // Clear threading flag
        boolean wasThreaded = node.rightThread;
        node.rightThread = false;
        
        // Add to collection
        nodes.add(node);
        
        // Recursively process children
        clearThreadingAndCollect(node.left, nodes);
        
        // Only process right child if it wasn't threaded
        if (!wasThreaded && node.right != null && node.right != _root) {
            clearThreadingAndCollect(node.right, nodes);
        }
    }

    private boolean hasRealRightChild(Elem node) {
        // Check if the node actually has a structural right child
        // This is a heuristic based on the tree structure
        if (node.right == null || node.right == _root) return false;
        
        // If right child's key is greater than current key, it's likely a real child
        return node.right.key.compareTo(node.key) > 0;
    }

    public void clear() {
        _root = new Elem();
        _root.left = _root;
        _root.right = null;
        _root.rightThread = false;
        _size = 0;
    }

    public boolean isEmpty() {
        return _size == 0;
    }

    public int size() {
        return _size;
    }

    @SuppressWarnings("unchecked")
    public V get(Object k) {
        if (k == null) return null;
        try {
            K key = (K) k;
            Elem node = findNode(key);
            return node != null ? node.value : null;
        } catch (ClassCastException e) {
            return null;
        }
    }

    public V replace(K key, V val) {
        Elem node = findNode(key);
        if (node != null) {
            V oldValue = node.value;
            node.value = val;
            return oldValue;
        }
        return null;
    }

    public boolean containsKey(K k) {
        return findNode(k) != null;
    }

    public V getOrDefault(K key, V defaultValue) {
        Elem node = findNode(key);
        return node != null ? node.value : defaultValue;
    }

    public V putIfAbsent(K key, V value) {
        Elem node = findNode(key);
        if (node != null) {
            return node.value;
        }
        insert(key, value);
        return null;
    }

    // Required lambda methods
    public void forEach(BiConsumer<? super K, ? super V> action) {
        if (action == null) throw new NullPointerException();
        
        // Get all nodes in sorted order
        java.util.List<Elem> nodes = getAllNodesSorted();
        
        // Apply action to each node
        for (Elem node : nodes) {
            action.accept(node.key, node.value);
        }
    }

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        if (function == null) throw new NullPointerException();
        
        // Get all nodes in sorted order
        java.util.List<Elem> nodes = getAllNodesSorted();
        
        // Apply function to each node and update value
        for (Elem node : nodes) {
            node.value = function.apply(node.key, node.value);
        }
    }

    // Helper method to get all nodes in sorted order
    private java.util.List<Elem> getAllNodesSorted() {
        java.util.List<Elem> nodes = new java.util.ArrayList<>();
        java.util.Set<Elem> visited = new java.util.HashSet<>();
        collectAllNodes(_root.left, nodes, visited);
        
        // Sort by key to ensure correct order
        nodes.sort((a, b) -> a.key.compareTo(b.key));
        return nodes;
    }

    private void collectAllNodes(Elem node, java.util.List<Elem> nodes, java.util.Set<Elem> visited) {
        if (node == null || node == _root || visited.contains(node)) return;
        
        visited.add(node);
        nodes.add(node);
        
        // Always explore left
        collectAllNodes(node.left, nodes, visited);
        
        // Explore right if it's a real child (not threaded)
        if (!node.rightThread && node.right != null && node.right != _root && !visited.contains(node.right)) {
            collectAllNodes(node.right, nodes, visited);
        }
    }

    @Override
    public Object clone() {
        return new Map<>(this);
    }

    private void printTree(int level, Elem p, StringBuilder res) {
        if (p != null && p != _root) {
            if (p.right != null && !p.rightThread && p.right != _root)
                printTree(level+1, p.right, res);
            for(int i=0;i<level;i++) {
                res.append("\t");
            }
            res.append(p.toString()).append("\n");

            if (p.left != null && p.left != _root)
                printTree(level+1, p.left, res);
        }
    }

    public String toString() {
        if (_root == _root.left) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        printTree(0, _root.left, res);
        return res.toString();
    }

    public Iterator<Entry<K,V>> iterator() {
        return new EntryIterator();
    }

    public class EntryIterator implements Iterator<Entry<K,V>> {
        private java.util.List<Elem> allNodes;
        private int currentIndex;

        private EntryIterator() {
            allNodes = getAllNodesSorted();
            currentIndex = 0;
        }

        public Entry<K,V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return allNodes.get(currentIndex++);
        }

        public boolean hasNext() {
            return currentIndex < allNodes.size();
        }
    }
}