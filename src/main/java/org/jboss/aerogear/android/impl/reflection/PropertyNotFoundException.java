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

/**
 * Thrown when a property cannot be found.
 */
public class PropertyNotFoundException extends RuntimeException {

    /**
     * Constructs with class and fieldName cannot be found.
     *
     * @param klass Class where field cannot be found.
     * @param type the type of the field that was being looked for
     * @param fieldName field cannot be found property.
     * 
     */
    public PropertyNotFoundException(Class klass, Class type, String fieldName) {
        super("Cannot find get/set to field " + fieldName + " (" + type.getSimpleName() + ") on " + klass.getSimpleName());
    }

}
