package ru.anani.lesson8;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class LRUCache<K extends Arrays, V> extends LinkedHashMap<K, V> {

    private final Path cacheFile;

    Map<List<Object>,Object> map = new HashMap<>();

    Properties property = new Properties();



    public LRUCache(String root_dir, String fileName) {
        this.cacheFile = Paths.get(root_dir + "/" + fileName + ".properties");
        cacheCreate();
        try {
            property.load(new FileInputStream(String.valueOf(cacheFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cacheCreate() {
        if (!Files.exists(cacheFile)) {
            try {
                Files.createFile(cacheFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean containsInMemoryCache(Object[] args) {
        return map.containsKey(Arrays.asList(args));
    }

    public boolean containsInFileCache(Object[] args) {
        ByteArrayOutputStream byteArrayOutputStreamArgs = new ByteArrayOutputStream();

        try(ObjectOutputStream streamArgs = new ObjectOutputStream(byteArrayOutputStreamArgs)) {
            streamArgs.writeObject(args);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return property.containsKey(Base64.getEncoder().encodeToString(byteArrayOutputStreamArgs.toByteArray()));
    }

    public Object findInMemoryCache(Object[] args) {
        return map.get(Arrays.asList(args));
    }

    public Object findInFileCache(Object[] args) {
//        return map.get(args);

        ByteArrayOutputStream byteArrayOutputStreamArgs = new ByteArrayOutputStream();
        try(ObjectOutputStream streamArgs = new ObjectOutputStream(byteArrayOutputStreamArgs)) {
            streamArgs.writeObject(args);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayInputStream byteArrayInputStreamValue = new ByteArrayInputStream(Base64.getDecoder().decode(property.getProperty(Base64.getEncoder().encodeToString(byteArrayOutputStreamArgs.toByteArray()))));

        Object o = null;
        try(ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStreamValue)){
            o = inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }

    public void setInMemoryCache(Object value, Object... args) {
        map.put(Arrays.asList(args), value);
    }

    public void setInFileCache(Object value, Object... args) {
        ByteArrayOutputStream byteArrayOutputStreamValue = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStreamArgs = new ByteArrayOutputStream();

        try(ObjectOutputStream streamValue = new ObjectOutputStream(byteArrayOutputStreamValue);
            ObjectOutputStream streamArgs = new ObjectOutputStream(byteArrayOutputStreamArgs)) {

            streamValue.writeObject(value);
            streamArgs.writeObject(args);
        } catch (IOException e) {
            e.printStackTrace();
        }

        property.setProperty(Base64.getEncoder().encodeToString(byteArrayOutputStreamArgs.toByteArray()), Base64.getEncoder().encodeToString(byteArrayOutputStreamValue.toByteArray()));

        try {
            property.store(new FileOutputStream(String.valueOf(cacheFile)),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertArgsToKey(Object... args) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (int i = 0; i < args.length; i++) {
                builder.append(String.valueOf(args[i]));
            if(i != args.length - 1)
                builder.append(" ");
        }
        builder.append("}");
        return builder.toString();
    }
}
