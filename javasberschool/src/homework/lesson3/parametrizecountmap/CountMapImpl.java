package homework.lesson3.parametrizecountmap;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<K> implements CountMap<K>{
    private Map<K, Integer> map = new HashMap<>();

    @Override
    public void add(K k) {
        Integer count;
        if((count = map.get(k)) == null) {
            count = 0;
        }
        map.put(k, ++count);
    }

    @Override
    public int getCount(K k) {
        return map.get(k);
    }

    @Override
    public int remove(K k) {
        return map.remove(k);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap source) {
        for (Object key: source.toMap().keySet()) {
            Integer count;
            if((count = map.get(key)) == null) {
                count = 0;
            }
            map.put((K) key, source.getCount(key) + count);
        }
    }

    @Override
    public Map toMap() {
        return map;
    }

    @Override
    public void toMap(Map destination) {
        for (K key: map.keySet()) {
            destination.put(key, map.get(key));
        }
    }
}
