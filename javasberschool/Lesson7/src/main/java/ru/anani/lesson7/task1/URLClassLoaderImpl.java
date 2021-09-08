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
                long t0 = System.nanoTime();
                try {
                    c = findClass(name);
                }catch (Exception exception) {

                }
                long t1 = System.nanoTime();
                // this is the defining class loader; record the stats
//                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
//                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
//                sun.misc.PerfCounter.getFindClasses().increment();
                if (c == null) {
                    c = getParent().loadClass(name);
//                    try {
//                        if (this.getParent() != null) {
//                            c = this.getParent().loadClass(name, false);
//                        } else {
//                            c = findBootstrapClassOrNull(name);
//                        }
//                    } catch (ClassNotFoundException e) {
                        // ClassNotFoundException thrown if class not found
                        // from the non-null parent class loader
//                    }
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
}
