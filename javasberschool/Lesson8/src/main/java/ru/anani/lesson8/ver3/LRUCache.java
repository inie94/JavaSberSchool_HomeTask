package ru.anani.lesson8.ver3;

import ru.anani.lesson8.annotation.Cache;
import ru.anani.lesson8.annotation.CachedType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.*;

public class LRUCache<K extends Arrays, V> extends LinkedHashMap<K, V> {

    private final Path ROOT_DIR;
    private final Path ZIP_PATH;
    private Path filePath;
    private String fileName;

    private Cache cacheParams;

    Map<List<Object>,Object> memoryCache = new HashMap<>();
    Map<List<Object>,Object> fileCache = new HashMap<>();
//    Properties properties = new Properties();


    public LRUCache(String root_dir) {
        this.ROOT_DIR = Paths.get(root_dir);
        this.ZIP_PATH = Paths.get(root_dir + "/arch.zip");
    }

    public void config(String methodName) {
        if(cacheParams.cacheType().equals(CachedType.FILE)) {
            if (cacheParams.fileNamePrefix().equals("")) {
                fileName = methodName;
            } else {
                fileName = cacheParams.fileNamePrefix();
            }

            if(cacheParams.zip()) {
                if(!Files.exists(ZIP_PATH)) {
                    try {
                        Files.createFile(ZIP_PATH);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        ZipFile zipFile = new ZipFile(String.valueOf(ZIP_PATH));
                        Enumeration<? extends ZipEntry> entries = zipFile.entries();
                        while (entries.hasMoreElements()) {
                            ZipEntry entry;
                            if((entry = entries.nextElement()).getName().equals(fileName)) {
                                Path tempFile = createTempFile();
                                try(InputStream fis = zipFile.getInputStream(entry);
                                    FileOutputStream fos = new FileOutputStream(String.valueOf(tempFile))){
                                    while (fis.available() > 0) {
                                        fos.write(fis.read());
                                    }
                                }
                                fileCache = (Map<List<Object>, Object>) deserializeObject(tempFile);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                createCacheFile(fileName);
            }
        }
    }

    public void createCacheFile(String fileName) {
        if (!String.valueOf(filePath).equals(ROOT_DIR + "/" + fileName + ".cache")) {
            this.filePath = Paths.get(ROOT_DIR + "/" + fileName + ".cache");

            if (!Files.exists(filePath)) {
                try {
                    Files.createFile(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            serializeObject(fileCache, filePath);
        }
    }

    public boolean contains(Object[] args) {
        switch (cacheParams.cacheType()) {
            case FILE:
                return containsInFileCache(args);
            case IN_MEMORY:
                return containsInMemoryCache(args);
        }
        return false;
    }

    public boolean containsInMemoryCache(Object[] args) {
        if(cacheParams.ignoreIdentificationBy().length == 0) {
            return memoryCache.containsKey(Arrays.asList(args));
        } else {
            List<Object> chekList = new LinkedList<>(Arrays.asList(args));

            for (int index: cacheParams.ignoreIdentificationBy()) {
                chekList.remove(index - 1);
            }
            return memoryCache.keySet().stream().anyMatch(objects -> objects.containsAll(chekList));
        }
    }

    public boolean containsInFileCache(Object[] args) {
//        fileCache = (Map<List<Object>, Object>) deserializeObject(filePath);
        if(cacheParams.ignoreIdentificationBy().length == 0) {
            return fileCache.containsKey(Arrays.asList(args));
        } else {
            List<Object> chekList = new LinkedList<>(Arrays.asList(args));

            for (int index: cacheParams.ignoreIdentificationBy()) {
                chekList.remove(index - 1);
            }

            return fileCache.keySet().stream().anyMatch(o ->
                    o.containsAll(chekList));
        }
    }

    public Object find(Object[] args) {
        switch (cacheParams.cacheType()) {
            case FILE:
                return findInFileCache(args);
            case IN_MEMORY:
                return findInMemoryCache(args);
        }
        return null;
    }

    public Object findInMemoryCache(Object[] args) {
        if (cacheParams.ignoreIdentificationBy().length == 0) {
            return memoryCache.get(Arrays.asList(args));
        } else {
            List<Object> chekList = new LinkedList<>(Arrays.asList(args));

            for (int index: cacheParams.ignoreIdentificationBy()) {
                chekList.remove(index - 1);
            }

            return memoryCache.get(memoryCache.keySet().stream().filter(objects -> objects.containsAll(chekList)).findFirst().get());
        }
    }

    public Object findInFileCache(Object[] args) {
//        fileCache = (Map<List<Object>, Object>) deserializeObject(filePath);
        if (cacheParams.ignoreIdentificationBy().length == 0) {
            return fileCache.get(Arrays.asList(args));
        } else {
            List<Object> chekList = new LinkedList<>(Arrays.asList(args));

            for (int index: cacheParams.ignoreIdentificationBy()) {
                chekList.remove(index - 1);
            }
            return fileCache.get(fileCache.keySet().stream().filter(key -> key.containsAll(chekList)).findFirst().get());
        }
    }

    public void set(Object value, Object[] args) {
        switch (cacheParams.cacheType()) {
            case FILE:
                setInFileCache(value, args);
                if(cacheParams.zip()) {
                    setInZip(value, args);
                } else {
                    serializeObject(fileCache, filePath);
                }
                break;
            case IN_MEMORY:
                setInMemoryCache(value, args);
        }
    }



    public void setInMemoryCache(Object value, Object... args) {
        if ((value instanceof Collection || value instanceof Map) &&
                cacheParams.listList() != 0 &&
                ((List<?>) value).size() > cacheParams.listList()
        ) {
            memoryCache.put(
                    Arrays.asList(args),
                    ((List<?>) value).subList(
                            (((List<?>) value).size() - cacheParams.listList()),
                            ((List<?>) value).size()
                    )
            );
        } else {
            memoryCache.put(Arrays.asList(args), value);
        }
    }
    private void setInZip(Object value, Object[] args) {
        Path tempFile = null;
        try {
            tempFile = createTempFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        serializeObject(fileCache, tempFile);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(String.valueOf(ZIP_PATH)));
             ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(String.valueOf(ZIP_PATH)))) {

            if(zipInputStream.getNextEntry() != null) {
                ZipFile zipFile = new ZipFile(String.valueOf(ZIP_PATH));
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    zipOutputStream.putNextEntry(entries.nextElement());
                    zipOutputStream.closeEntry();
                }
            }

            zipOutputStream.putNextEntry(new ZipEntry(fileName + ".cache"));
            zipOutputStream.write(Files.readAllBytes(tempFile));
            zipOutputStream.closeEntry();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInFileCache(Object value, Object[] args) {
        if ((value instanceof Collection || value instanceof Map) &&
                cacheParams.listList() != 0 &&
                ((List<?>) value).size() > cacheParams.listList()
        ) {
            fileCache.put(
                    Arrays.asList(args),
                    new ArrayList<>(
                        ((List<?>) value).subList(
                            (((List<?>) value).size() - cacheParams.listList()),
                            ((List<?>) value).size()
                        )
                    )
            );
        } else {
            fileCache.put(Arrays.asList(args), value);
        }

//        serializeObject(fileCache);
    }

    private void serializeObject(Object o, Path file) {
        try(ObjectOutputStream streamArgs =
                    new ObjectOutputStream(new FileOutputStream(String.valueOf(file)))) {
            streamArgs.writeObject(o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object deserializeObject(Path filePath) {
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

    public void setCacheParams(Cache cacheParams) {
        this.cacheParams = cacheParams;
    }

    public Path createTempFile() throws IOException {
            return Files.createTempFile("new Date().toString()", "-temp");
    }
}
