/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */

package org.apache.logging.log4j.plugins.defaults.bean;

import org.apache.logging.log4j.plugins.spi.bean.Bean;
import org.apache.logging.log4j.plugins.spi.model.MetaClass;
import org.apache.logging.log4j.plugins.spi.model.Qualifiers;
import org.apache.logging.log4j.plugins.spi.model.Variable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Objects;

abstract class AbstractBean<T> implements Bean<T> {
    private final Variable variable;
    private final MetaClass<?> declaringClass;

    AbstractBean(final Variable variable, final MetaClass<?> declaringClass) {
        this.variable = Objects.requireNonNull(variable);
        this.declaringClass = declaringClass;
    }

    @Override
    public MetaClass<?> getDeclaringClass() {
        return declaringClass;
    }

    @Override
    public Collection<Type> getTypes() {
        return variable.getTypes();
    }

    @Override
    public Qualifiers getQualifiers() {
        return variable.getQualifiers();
    }

    @Override
    public Class<? extends Annotation> getScopeType() {
        return variable.getScopeType();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractBean<?> that = (AbstractBean<?>) o;
        return variable.equals(that.variable) &&
                Objects.equals(declaringClass, that.declaringClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable, declaringClass);
    }

    abstract boolean isTrackingDependencies();
}