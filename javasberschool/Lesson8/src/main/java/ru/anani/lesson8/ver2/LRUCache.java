package ru.anani.lesson8.ver2;

import ru.anani.lesson8.annotation.Cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class LRUCache<K extends Arrays, V> extends LinkedHashMap<K, V> {

    private final Path ROOT_DIR;
    private Path filePath;

    Map<List<Object>,Object> memoryCache = new HashMap<>();
    Map<List<Object>,Object> fileCache = new HashMap<>();
//    Properties properties = new Properties();


    public LRUCache(String root_dir) {
        this.ROOT_DIR = Paths.get(root_dir);
    }

    public void createCacheFile(String fileName, boolean zip) {
        if (!String.valueOf(filePath).equals(ROOT_DIR + "/" + fileName + ".cache")) {
            this.filePath = Paths.get(ROOT_DIR + "/" + fileName + ".cache");

            if (!Files.exists(filePath)) {
                try {
                    Files.createFile(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            serializeObject(fileCache);
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
        fileCache = (Map<List<Object>, Object>) deserializeObject();
        if(cache.ignoreIdentificationBy().length == 0) {
            return fileCache.containsKey(Arrays.asList(args));
        } else {
            List<Object> chekList = new LinkedList<>(Arrays.asList(args));

            for (int index: cache.ignoreIdentificationBy()) {
                chekList.remove(index - 1);
            }

            return fileCache.keySet().stream().anyMatch(o ->
                    o.containsAll(chekList));
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
        fileCache = (Map<List<Object>, Object>) deserializeObject();
        if (ignoreIdentificationBy.length == 0) {
            return fileCache.get(Arrays.asList(args));
        } else {
            List<Object> chekList = new LinkedList<>(Arrays.asList(args));

            for (int index: ignoreIdentificationBy) {
                chekList.remove(index - 1);
            }
            return fileCache.get(fileCache.keySet().stream().filter(key -> key.containsAll(chekList)).findFirst().get());
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
            fileCache.put(
                    Arrays.asList(args),
                    new ArrayList<>(
                        ((List<?>) value).subList(
                            (((List<?>) value).size() - (int) cache.listList()),
                            ((List<?>) value).size()
                        )
                    )
            );
        } else {
            fileCache.put(Arrays.asList(args), value);
        }

        serializeObject(fileCache);
    }

    private void serializeObject(Object o) {
        try(ObjectOutputStream streamArgs =
                    new ObjectOutputStream(new FileOutputStream(String.valueOf(filePath)))) {
            streamArgs.writeObject(o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object deserializeObject() {
        try(ObjectInputStream inputStream =
                    new ObjectInputStream(new FileInputStream(String.valueOf(filePath)))) {
            return inputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
