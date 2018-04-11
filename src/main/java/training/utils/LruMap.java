package training.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruMap<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 8021661818936554990L;
    protected final int _maxEntries;

    public LruMap(int initialEntries, int maxEntries)
    {
        super(initialEntries, 0.8f, true);
        _maxEntries = maxEntries;
    }

    public LruMap(int maxEntries ) {
        _maxEntries = maxEntries;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > _maxEntries;
    }
}

