/**
 * CS515 Assignment 2p

*Name: Huan Zhou(Rita)

*Section: 01

*Date: 06/03/2025

Collaboration Declaration: 

Collaboration: None
*/

import java.util.TreeMap;

public class Map<K extends Comparable<K>, V> implements Cloneable {

    private TreeMap<K, V> map;
    private int size;

    public Map() {
        this.map = new TreeMap<>();
        this.size = 0;
    }
    
    public Map(Map<K, V> v) {
        this.map = new TreeMap<>();
        this.size = 0;
        for (K key : v.map.keySet()) {
            this.map.put(key, v.map.get(key));
            this.size++;
        }
    }

    public boolean insert(K k, V v) {
        if (map.containsKey(k)) {
            return false;
        }
        map.put(k, v);
        size++;
        return true;
    }

    public boolean erase(K k) {
        if (map.containsKey(k)) {
            map.remove(k);
            size--;
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public V get(Object k) {
        return map.get(k);
    }

    @Override
    public Object clone() {
        Map<K, V> clone = new Map<>();
        clone.map = new TreeMap<>(this.map);
        clone.size = this.size;
        return clone;
    }

    public V replace(K k, V v) {
        if (map.containsKey(k)) {
            map.put(k, v);
            return v;
        }
        return null;
    }
}
