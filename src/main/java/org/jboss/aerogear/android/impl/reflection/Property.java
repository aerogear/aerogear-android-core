/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.android.impl.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Helper to access property with reflection
 */
public class Property {

    private final Class klass;
    private final String fieldName;

    private Class type;

    private Method getMethod;
    private Method setMethod;

    public Class getType() {
        return type;
    }

    /**
     * Constructor to access field with get/set
     *
     * @param klass Class to be manipulated
     * @param fieldName Field to be accessed
     *
     * @throws IllegalArgumentException if either param is null
     * @throws PropertyNotFoundException if property isn't found
     */
    public Property(Class klass, String fieldName) {
        if (klass == null) {
            throw new IllegalArgumentException("Class could not be null");
        }
        if (fieldName == null) {
            throw new IllegalArgumentException("Fieldname could not be null");
        }

        this.klass = klass;
        this.fieldName = fieldName;

        setPropertyType();
        findAccessorMethods();
    }

    private void setPropertyType() {
        try {
            type = findFieldInClass(klass, fieldName).getType();
        } catch (NoSuchFieldException e) {
            throw new FieldNotFoundException(klass, fieldName);
        }
    }

    /**
     * Search field in class/superclasses
     *
     * @param klass Class to search
     * @param fieldName Field to search
     * @return Field with @RecordId
     *
     * @throws NoSuchFieldException if field isn't found
     */
    private Field findFieldInClass(Class klass, String fieldName) throws NoSuchFieldException {
        try {
            return klass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superclass = klass.getSuperclass();
            if (superclass != null) {
                return findFieldInClass(superclass, fieldName);
            }
            throw new NoSuchFieldException();
        }
    }

    private void findAccessorMethods() {
        try {
            getMethod = klass.getMethod(getMethodName());
            setMethod = klass.getMethod(setMethodName(), type);
        } catch (Exception e) {
            throw new PropertyNotFoundException(klass, getType(), fieldName);
        }

    }

    private String getMethodName() {
        if (type == boolean.class) {
            return "is" + capitalize(fieldName);
        } else {
            return "get" + capitalize(fieldName);
        }
    }

    private String setMethodName() {
        return "set" + capitalize(fieldName);
    }

    private String capitalize(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    /**
     * Get value
     *
     * @param instance Instance to get value
     * @return Value
     */
    public Object getValue(Object instance) {
        try {
            return getMethod.invoke(instance);
        } catch (Exception e) {
            throw new PropertyNotFoundException(klass, getType(), fieldName);
        }

    }

    /**
     * Set new value
     *
     * @param instance Instance to set new value
     * @param value new value
     */
    public void setValue(Object instance, Object value) {
        try {
            setMethod.invoke(instance, value);
        } catch (Exception e) {
            throw new PropertyNotFoundException(klass, getType(), fieldName);
        }

    }

    public String getFieldName() {
        return fieldName;
    }

}
