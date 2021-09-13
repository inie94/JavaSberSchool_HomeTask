package ru.anani.lesson8.ver1;

import ru.anani.lesson8.annotation.Cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class LRUCache<K extends Arrays, V> extends LinkedHashMap<K, V> {

    private final Path ROOT_DIR;
    private Path cacheFile;


    Map<List<Object>,Object> memoryCache = new HashMap<>();
    Properties properties = new Properties();


    public LRUCache(String root_dir) {
        this.ROOT_DIR = Paths.get(root_dir);
    }

    public void createCacheFile(String fileName, boolean zip) {
        if (!String.valueOf(cacheFile).equals(ROOT_DIR + "/" + fileName + ".properties")) {
            this.cacheFile = Paths.get(ROOT_DIR + "/" + fileName + ".properties");

            if (!Files.exists(cacheFile)) {
                try {
                    Files.createFile(cacheFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                properties.load(new FileInputStream(String.valueOf(cacheFile)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean contains(Cache cache, Object[] args) {
        switch (cache.cacheType()) {
            case FILE:
                return containsInFileCache(args, cache);
            case IN_MEMORY:
                return containsInMemoryCache(args, cache);
        }
        return false;
    }

    public boolean containsInMemoryCache(Object[] args, Cache cache) {
        if(cache.ignoreIdentificationBy().length == 0) {
            return memoryCache.containsKey(Arrays.asList(args));
        } else {
            List<Object> chekList = new LinkedList<>(Arrays.asList(args));

            for (int index: cache.ignoreIdentificationBy()) {
                chekList.remove(index - 1);
            }
            return memoryCache.keySet().stream().anyMatch(objects -> objects.containsAll(chekList));
        }
    }

    public boolean containsInFileCache(Object[] args, Cache cache) {
        if(cache.ignoreIdentificationBy().length == 0) {
            return properties.containsKey(serializeObjectToString(args));
        } else {
            List<Object> chekList = new LinkedList<>(Arrays.asList(args));

            for (int index: cache.ignoreIdentificationBy()) {
                chekList.remove(index - 1);
            }

            return properties.keySet().stream().anyMatch(o ->
                Arrays.asList((Object[]) deserializeObjectFromString((String) o)).containsAll(chekList));
        }
    }

    public Object find(Cache cache, Object[] args) {
        switch (cache.cacheType()) {
            case FILE:
                return findInFileCache(args, cache.ignoreIdentificationBy());
            case IN_MEMORY:
                return findInMemoryCache(args, cache.ignoreIdentificationBy());
        }
        return null;
    }

    public Object findInMemoryCache(Object[] args, int[] ignoreIdentificationBy) {
        if (ignoreIdentificationBy.length == 0) {
            return memoryCache.get(Arrays.asList(args));
        } else {
            List<Object> chekList = new LinkedList<>(Arrays.asList(args));

            for (int index: ignoreIdentificationBy) {
                chekList.remove(index - 1);
            }

            return memoryCache.get(memoryCache.keySet().stream().filter(objects -> objects.containsAll(chekList)).findFirst().get());
        }
    }

    public Object findInFileCache(Object[] args, int[] ignoreIdentificationBy) {

        if (ignoreIdentificationBy.length == 0) {
            return deserializeObjectFromString(properties.getProperty(serializeObjectToString(args)));
        } else {
            List<Object> chekList = new LinkedList<>(Arrays.asList(args));

            for (int index: ignoreIdentificationBy) {
                chekList.remove(index - 1);
            }

            return  deserializeObjectFromString((String) properties.get(properties.keySet().stream().filter(o -> Arrays.asList((Object[]) deserializeObjectFromString((String) o)).containsAll(chekList)).findFirst().get()));
        }
    }

    public void set(Cache cache, Object value, Object[] args) {
        switch (cache.cacheType()) {
            case FILE:
                setInFileCache(cache, value, args);
                break;
            case IN_MEMORY:
                setInMemoryCache(cache, value, args);
        }
    }

    public void setInMemoryCache(Cache cache, Object value, Object... args) {
        if ((value instanceof Collection || value instanceof Map) &&
                cache.listList() != 0 &&
                ((List<?>) value).size() > cache.listList()
        ) {
            memoryCache.put(
                    Arrays.asList(args),
                    ((List<?>) value).subList(
                            (((List<?>) value).size() - (int) cache.listList()),
                            ((List<?>) value).size()
                    )
            );
        } else {
            memoryCache.put(Arrays.asList(args), value);
        }
    }

    public void setInFileCache(Cache cache, Object value, Object[] args) {
        if ((value instanceof Collection || value instanceof Map) &&
                cache.listList() != 0 &&
                ((List<?>) value).size() > cache.listList()
        ) {
            properties.setProperty(
                    serializeObjectToString(args),
                    serializeObjectToString(
                            new ArrayList<>(
                                    ((List<?>) value).subList(
                                        (((List<?>) value).size() - (int) cache.listList()),
                                        ((List<?>) value).size()
                                    )
                            )
                    )
            );
        } else {
            properties.setProperty(serializeObjectToString(args), serializeObjectToString(value));
        }

        try {
            properties.store(new FileOutputStream(String.valueOf(cacheFile)),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String serializeObjectToString(Object o) {
        ByteArrayOutputStream byteArrayOutputStreamOfObject = new ByteArrayOutputStream();

        try(ObjectOutputStream streamArgs = new ObjectOutputStream(byteArrayOutputStreamOfObject)) {
            streamArgs.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(byteArrayOutputStreamOfObject.toByteArray());
    }

    private Object deserializeObjectFromString(String s) {
        try(ObjectInputStream inputStream = new ObjectInputStream(
                new ByteArrayInputStream(Base64.getDecoder().decode(s)))
        ){
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
