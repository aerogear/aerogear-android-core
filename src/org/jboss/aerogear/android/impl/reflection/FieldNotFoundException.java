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
 * Thrown when a particular field cannot be found.
 */
public class FieldNotFoundException extends RuntimeException {

    /**
     * Constructs with class and fieldName cannot be found.
     *
     * @param klass Class where field cannot be found.
     * @param fieldName field cannot be found.
     */
    public FieldNotFoundException(Class klass, String fieldName) {
        super("Cannot find field " + fieldName + " on " + klass.getSimpleName());
    }

}
