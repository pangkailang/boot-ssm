package com.pkl.boot.common.exception;

import org.springframework.lang.Nullable;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Iterator;

public class MyAssert {
    public static void noNullElements(@Nullable Collection<?> collection, String message,Class< ? extends RuntimeException> clz) throws Exception {
        Constructor constructor = clz.getConstructor(String.class);
        if (collection != null) {
            Iterator var2 = collection.iterator();
            while(var2.hasNext()) {
                Object element = var2.next();
                if (element == null) {
                    throw (RuntimeException)constructor.newInstance(message);
                }
            }
        }

    }
}
