package ru.anani.lesson5.task7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BeanUtils {
    /**
      * Scans object "from" for all getters. If object "to"
      * contains correspondent setter, it will invoke it
      * to set property value for "to" which equals to the property
      * of "from".
      * <p/>
      * The type in setter should be compatible to the value returned
      * by getter (if not, no invocation performed).
      * Compatible means that parameter type in setter should
      * be the same or be superclass of the return type of the getter.
      * <p/>
      * The method takes care only about public methods.
      *
      * @param to   Object which properties will be set.
      * @param from Object which properties will be used to get values.
      */

    public static void assign(Object to, Object from) {
        Set<Method> methods = new HashSet<>();
        Arrays.stream(from.getClass().getMethods())
                .filter(method -> method.getName().startsWith("get"))
                .forEach(methods::add);
        Arrays.stream(to.getClass().getMethods())
                .filter(method -> method.getName().startsWith("set")).filter(method -> methods.stream()
                        .anyMatch(method1 -> method1.getName().substring(2).equals(method.getName().substring(2))))
                .forEach(method -> {
                    Method methodGet = methods.stream()
                            .filter(method1 -> method1.getName().substring(2).equals(method.getName().substring(2)) &&
                                        method.getParameterTypes()[0].isAssignableFrom(method1.getReturnType()))
                            .findFirst().get();
                    try {
                        method.invoke(to, methodGet.invoke(from));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
    }
}
