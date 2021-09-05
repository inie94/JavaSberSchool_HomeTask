package ru.anani.lesson5.task5.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;
    private static final Path path = Paths.get("D:\\anani\\IdeaProjects\\JavaSberSchool_HomeTask\\javasberschool\\src\\homework\\lesson5\\reflectionhw\\task5\\cache\\cache.properties");
    private final File cache = path.toFile();
    Properties property = new Properties();

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // Remove the eldest element whenever size of cache exceeds the capacity
        return (size() > this.capacity);
    }

    public LRUCache(int capacity) {
        // Call constructor of LinkedHashMap with accessOrder set to true to
        // achieve LRU Cache behavior
        super(capacity + 1, 1.0f, true);
        this.capacity = capacity;

        if(!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            property.load(new FileInputStream(cache));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public V find(K key) {
        if(super.containsKey(key))
            return super.get(key);
        if(property.containsKey(key))
            return (V) property.getProperty(key.toString());
        return null;
    }

//    public boolean create

    public void set(K key, V value) {
        property.setProperty(key.toString(), value.toString());
        try {
            property.store(new FileOutputStream(String.valueOf(path)),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.put(key, value);
    }
}
