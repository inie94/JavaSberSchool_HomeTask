package ru.anani.lesson7.task1;

import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderImpl extends URLClassLoader {


    public URLClassLoaderImpl(URL[] urls) {
        super(urls);

    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                // If still not found, then invoke findClass in order
                // to find the class.

                try {
                    c = findClass(name);
                }catch (Exception exception) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    c = getParent().loadClass(name);
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
}
