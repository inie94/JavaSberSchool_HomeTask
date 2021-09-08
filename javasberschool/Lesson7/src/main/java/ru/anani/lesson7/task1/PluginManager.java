package ru.anani.lesson7.task1;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PluginManager {
    private final Path pluginRootDirectory;
    private final List<Plugin> plugins = new LinkedList<>();
    private /*final*/ ClassLoader pluginLoader;

    public PluginManager(String pluginRootDirectory) {
        Path rootDir = Paths.get(pluginRootDirectory);
        if (!Files.isDirectory(rootDir)) {
            throw new IllegalArgumentException("Переданный путь не является директорией");
        }
        this.pluginRootDirectory = rootDir;
    }

    /**
     *
     * @param pluginName
     * @param pluginClassName
     * @return null if plugin not found
     */
    public Plugin load(String pluginName, String pluginClassName) {
        try {
            return (Plugin) pluginLoader.loadClass(pluginClassName).getConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Инициализирует все плагины из pluginRootDirectory
     */
    public void initializePlugin() throws IOException {
        Map<URL, Map<String, String>> map = getAllUrlsWithNestedPluginsNamesAndClassesNames();

        pluginLoader = new URLClassLoaderImpl(map.keySet().toArray(new URL[0]));

        map.keySet().forEach(
                url -> map.get(url).forEach(
                        (fileName, pluginName) -> plugins.add(load(pluginName, fileName)
                        )
                )
        );
    }

    /**
     * Запускает все имеющиеся плагины.  т.е вызывает метод doUsefull
     */
    public void startAll() {
        for (Plugin plugin : plugins) {
            plugin.doUseful();
        }
    }

    /**
     * @return all urls into root directory with nested plugins names and classes names
     */
    private Map<URL, Map<String, String>> getAllUrlsWithNestedPluginsNamesAndClassesNames() throws IOException {
        Set<Path> paths = Files.list(pluginRootDirectory).collect(Collectors.toSet());

        Map<URL, Map<String, String>> map = new HashMap<>();

        for(Path path:paths) {
            Map<String, String> pluginNamesAndFileNamesMap = new HashMap<>();
            Files.list(path).collect(Collectors.toList()).forEach(filePath ->
                    pluginNamesAndFileNamesMap.put(
                            filePath.getFileName().toString().replace(".class",""),
                            filePath.getParent().getFileName().toString()
                    )
            );
            map.put(path.toUri().toURL(), pluginNamesAndFileNamesMap);
        }
        return map;
    }
}
