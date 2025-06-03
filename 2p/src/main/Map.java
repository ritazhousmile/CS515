/**
 * CS515 Assignment 2p

*Name: Huan Zhou(Rita)

*Section: 01

*Date: 06/03/2025

Collaboration Declaration: 

Collaboration: None
*/

import java.util.ArrayList;

public class Map<K extends Comparable<K>, V> implements Cloneable {

    private ArrayList<Entry<K, V>> map;
    private int size;

    private static class Entry<K, V> {
        private K key;
        private V value;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
        
        public void setValue(V value) {
            this.value = value;
        }
    }

    public Map() {
        this.map = new ArrayList<>();
        this.size = 0;
    }
    
    public Map(Map<K, V> v) {
        this.map = new ArrayList<>();
        this.size = 0;
        for (Entry<K, V> entry : v.map) {
            this.map.add(new Entry<>(entry.getKey(), entry.getValue()));
            this.size++;
        }
    }

    public boolean insert(K k, V v) {
        for (Entry<K, V> entry : map) {
            if (entry.getKey().equals(k)) {
                return false;
            }
        }
        map.add(new Entry<>(k, v));
        size++;
        return true;
    }

    public boolean erase(K k) {
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).getKey().equals(k)) {
                map.remove(i);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public V get(Object k) {
        for (Entry<K, V> entry : map) {
            if (entry.getKey().equals(k)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Object clone() {

        Map<K, V> clone = new Map<>();
        clone.map = new ArrayList<>();
        clone.size = 0;
        for (Entry<K, V> entry : this.map) {
            clone.map.add(new Entry<>(entry.getKey(), entry.getValue()));
            clone.size++;
        }
        return clone;
    }

    public V replace(K k, V v) {
        for (Entry<K, V> entry : map) {
            if (entry.getKey().equals(k)) {
                entry.setValue(v);
                return v;
            }
        }
        return null;
    }

}
