package org.apache.commons.pool2.temp;

import org.apache.commons.pool2.impl.AbandonedConfig;

import java.lang.annotation.Target;
import java.util.HashMap;

/**
 * Created by niuquanhao on 16/7/26.
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap allObjects = new HashMap();
        Teacher teacher = new Teacher();
        teacher.setAge(22);
        allObjects.put(new IdentityWrapper(teacher), teacher);

        teacher = null;
        System.out.println(allObjects);
    }

    static class IdentityWrapper<T> {
        /** Wrapped object */
        private final T instance;

        /**
         * Create a wrapper for an instance.
         *
         * @param instance object to wrap
         */
        public IdentityWrapper(final T instance) {
            this.instance = instance;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(instance);
        }

        @Override
        @SuppressWarnings("rawtypes")
        public boolean equals(final Object other) {
            return  other instanceof IdentityWrapper &&
                    ((IdentityWrapper) other).instance == instance;
        }

        /**
         * @return the wrapped object
         */
        public T getObject() {
            return instance;
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder();
            builder.append("IdentityWrapper [instance=");
            builder.append(instance);
            builder.append("]");
            return builder.toString();
        }
    }
}
