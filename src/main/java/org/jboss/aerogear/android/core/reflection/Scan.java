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
package org.jboss.aerogear.android.core.reflection;

import org.jboss.aerogear.android.core.RecordId;

import java.lang.reflect.Field;

/**
 * Helper to search annotations
 */
public final class Scan {

    /**
     * Search field with @RecordId
     *
     * @param klass Class to search @RecordId
     * @return Field with @RecordId
     */
    private static Field recordIdFieldIn(Class klass) {
        for (Field field : klass.getDeclaredFields()) {
            if (field.isAnnotationPresent(RecordId.class)) {
                return field;
            }
        }
        Class superclass = klass.getSuperclass();
        if (superclass != null) {
            return recordIdFieldIn(superclass);
        }
        throw new RecordIdNotFoundException(klass);
    }

    /**
     * Search field with @RecordId
     *
     * @param klass Class to search @RecordId
     * @return Name Field name with the @RecordId
     */
    public static String recordIdFieldNameIn(Class klass) {
        return recordIdFieldIn(klass).getName();
    }

    /**
     * 
     * Finds the value of the ID property in the data object.
     * 
     * @param data the object to search for an id
     * @return the id value of data, may be null
     * 
     * @throws RecordIdNotFoundException if data does not have a field annotated with @RecordId
     */
    public static String findIdValueIn(Object data) {
        String recordIdFieldName = Scan.recordIdFieldNameIn(data.getClass());
        Object idObject = new Property(data.getClass(), recordIdFieldName).getValue(data);
        String id = idObject == null ? null : idObject.toString();
        return id;
    }

}
