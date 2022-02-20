package lab1;

public class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K, V>> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public int compareTo(Pair<K, V> pair) {
        int cmp1 = key.compareTo(pair.key);
        if(cmp1 != 0)
            return cmp1;
        return value.compareTo(pair.value);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(!(obj instanceof Pair))
            return false;
        Pair<?, ?> p = (Pair<?, ?>) obj;
        return key.equals(p.key) && value.equals(p.value);
    }
}
