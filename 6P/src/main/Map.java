/**
 * CS515 Assignment 6P
 *
 * Name: Huan Zhou(Rita)
 *
 * Section: 01
 *
 * Date: 07/07/2025
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

    private class Elem {
        K key;
        V value;
        Elem left;
        Elem right;
        boolean rightThread;
    }

    private Elem _root;
    private int _size;

    public Map() {
        _root = new Elem();
        _root.left = _root;
        _root.right = null;
        _root.rightThread = false;
        _size = 0;
    }

    public Map(Map<K, V> v) {
        if (v._root == v._root.left) {
            _root = new Elem();
            _root.left = _root;
            _root.right = null;
            _size = 0;
        } else {
            _root = new Elem();
            _root.left = _root;
            _root.right = null;
            _root.left = copyTree(v._root.left);
            copyThread(_root, v._root);
            _size = v._size;
        }
    }


    private void addToMap(Elem root, TreeMap<K, Elem> keyElemMap) {
        if(root != null) {
            keyElemMap.put(root.key, root);
            addToMap(root.left, keyElemMap);
            if(!root.rightThread) {
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
            if( o.rightThread) {
                temp = newKeyElemMap.get(e.getKey());
                temp.rightThread = true;
                temp.right = newKeyElemMap.get(origKeyElemMap.get(e.getKey()).right.key);
            }
        }
    }

    private Elem copyTree(Elem origRoot) {
        if (origRoot == null) {
            return null;
        } else {
            Elem newRoot = new Elem();
            newRoot.key = origRoot.key;
            newRoot.value = origRoot.value;
            newRoot.left = copyTree(origRoot.left);
            if (!origRoot.rightThread)
                newRoot.right = copyTree(origRoot.right);
            return newRoot;
        }
    }

    private Elem findNode(K key) {
        Elem current = _root.left;
        while (current != _root) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                return current;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                if (current.rightThread) {
                    break;
                }
                current = current.right;
            }
        }
        return null;
    }

    public boolean insert(K k, V v) {
        if (k == null) return false;
        
        // If tree is empty
        if (_root.left == _root) {
            Elem newNode = new Elem();
            newNode.key = k;
            newNode.value = v;
            newNode.left = _root;
            newNode.right = _root;
            newNode.rightThread = true;
            _root.left = newNode;
            _size++;
            return true;
        }
        
        Elem current = _root.left;
        Elem parent = null;
        
        // Find the insertion point
        while (current != _root) {
            parent = current;
            int cmp = k.compareTo(current.key);
            
            if (cmp == 0) {
                // Key already exists, update value
                current.value = v;
                return false;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                if (current.rightThread) {
                    break;
                }
                current = current.right;
            }
        }
        
        // Create new node
        Elem newNode = new Elem();
        newNode.key = k;
        newNode.value = v;
        newNode.left = _root;
        newNode.rightThread = true;
        
        // Insert as left or right child
        if (k.compareTo(parent.key) < 0) {
            // Insert as left child
            newNode.right = parent;
            parent.left = newNode;
        } else {
            // Insert as right child
            newNode.right = parent.right;
            parent.right = newNode;
            parent.rightThread = false;
        }
        
        _size++;
        return true;
    }

    public boolean erase(K k) {
        if (k == null || _size == 0) return false;
        
        Elem current = _root.left;
        Elem parent = _root;
        boolean isLeftChild = true;
        
        // Find the node to delete
        while (current != _root) {
            int cmp = k.compareTo(current.key);
            if (cmp == 0) {
                break;
            } else if (cmp < 0) {
                parent = current;
                current = current.left;
                isLeftChild = true;
            } else {
                parent = current;
                if (current.rightThread) {
                    return false; // Node not found
                }
                current = current.right;
                isLeftChild = false;
            }
        }
        
        if (current == _root) {
            return false; // Node not found
        }
        
        // Case 1: Node is a leaf (no children)
        if (current.left == _root && current.rightThread) {
            if (current == _root.left) {
                _root.left = _root;
            } else if (isLeftChild) {
                parent.left = _root;
            } else {
                parent.right = current.right;
                parent.rightThread = true;
            }
        }
        // Case 2: Node has only left child
        else if (current.rightThread) {
            // Find the rightmost node in left subtree to update its threading
            Elem rightmost = current.left;
            while (rightmost.right != _root && !rightmost.rightThread) {
                rightmost = rightmost.right;
            }
            rightmost.right = current.right;
            rightmost.rightThread = true;
            
            if (current == _root.left) {
                _root.left = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
                parent.rightThread = false;
            }
        }
        // Case 3: Node has only right child
        else if (current.left == _root) {
            if (current == _root.left) {
                _root.left = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }
        // Case 4: Node has two children
        else {
            // Find inorder successor (leftmost in right subtree)
            Elem successor = current.right;
            Elem succParent = current;
            boolean succIsLeftChild = false;
            
            while (successor.left != _root) {
                succParent = successor;
                successor = successor.left;
                succIsLeftChild = true;
            }
            
            // Replace current node's data with successor's data
            current.key = successor.key;
            current.value = successor.value;
            
            // Remove successor node
            if (successor.rightThread) {
                // Successor is a leaf
                if (succIsLeftChild) {
                    succParent.left = _root;
                } else {
                    succParent.right = successor.right;
                    succParent.rightThread = true;
                }
            } else {
                // Successor has right child
                if (succIsLeftChild) {
                    succParent.left = successor.right;
                } else {
                    succParent.right = successor.right;
                }
            }
        }
        
        _size--;
        return true;
    }


	public void clear() {
        _root = new Elem();
        _root.left = _root;
        _root.right = null;
        _root.rightThread = false;
        _size = 0;
    }

    boolean isEmpty() {
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

    boolean containsKey(K k) {
        return findNode(k) != null;
    }

    V getorDefault(K key, V defaultValue) {
        Elem node = findNode(key);
        return node != null ? node.value : defaultValue;
    }

    V insertIfAbsent(K key, V value) {
        Elem node = findNode(key);
        if (node != null) {
            return node.value;
        }
        insert(key, value);
        return null;
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        if (action == null) throw new NullPointerException();
        forEachHelper(_root.left, action);
    }
    
    private void forEachHelper(Elem node, BiConsumer<? super K, ? super V> action) {
        if (node != _root) {
            forEachHelper(node.left, action);
            action.accept(node.key, node.value);
            if (!node.rightThread) {
                forEachHelper(node.right, action);
            }
        }
    }

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        if (function == null) throw new NullPointerException();
        replaceAllHelper(_root.left, function);
    }
    
    private void replaceAllHelper(Elem node, BiFunction<? super K, ? super V, ? extends V> function) {
        if (node != _root) {
            replaceAllHelper(node.left, function);
            node.value = function.apply(node.key, node.value);
            if (!node.rightThread) {
                replaceAllHelper(node.right, function);
            }
        }
    }

    @Override
    public Object clone() {
        return new Map<>(this);
    }

    private void printTree(int level, Elem p, StringBuilder res) {
        int i;
        if (p != null && p != _root) {
            if (p.right != null  && !p.rightThread)
                printTree(level+1, p.right, res);
            for(i=0;i<level;i++) {
                res.append("\t");;
            }
            res.append(p.key).append(" ").append(p.value).append("\n");
            if (p.left != _root) {
                printTree(level+1, p.left, res);
            }
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

    public Map<K,V>.KeyIterator keyIterator() {
        return new KeyIterator();
    }

    public class KeyIterator implements Iterator {

        private Elem ptr;

        private KeyIterator() {
            // Start from the leftmost node
            ptr = _root.left;
            if (ptr != _root) {
                while (ptr.left != _root) {
                    ptr = ptr.left;
                }
            }
        }

        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            K key = ptr.key;
            
            // Move to next node using threading
            if (ptr.rightThread) {
                ptr = ptr.right;
            } else {
                ptr = ptr.right;
                while (ptr.left != _root) {
                    ptr = ptr.left;
                }
            }
            
            return key;
        }

        public boolean hasNext() {
            return ptr != _root;
        }
    }
}
