package lab3;

import java.util.LinkedList;

public class HashTable<K, V> {
    private static final int maxValue = 16;
    private LinkedList<Entry<K, V>>[] table;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    private static class Entry<K, V> {
        private final K key;
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

        void setValue(V value) {
            this.value = value;
        }
    }

    public HashTable() {
        table = new LinkedList[maxValue];
        size = 0;
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    table[index].remove(entry);
                    size--;
                    return;
                }
            }
        }
    }



    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();

        hashTable.put("apple", 5);
        hashTable.put("banana", 3);
        hashTable.put("orange", 7);
        hashTable.put("pear", 2);

        System.out.println("Размер таблицы: " + hashTable.getSize()); // 4
        System.out.println("Значение по ключу 'banana': " + hashTable.get("banana")); // 3
        System.out.println("Значение по ключу 'orange': " + hashTable.get("orange")); // 7
        System.out.println("Значение по ключу 'pear': " + hashTable.get("pear")); // 2

        hashTable.remove("banana");
        System.out.println("После удаления 'banana', размер: " + hashTable.getSize()); // 3
        System.out.println("Значение по ключу 'banana': " + hashTable.get("banana")); // null
    }
}
