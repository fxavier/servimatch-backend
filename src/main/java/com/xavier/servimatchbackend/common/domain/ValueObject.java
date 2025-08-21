package com.xavier.servimatchbackend.common.domain;

/**
 * Base class for value objects in the domain model.
 * Value objects are defined by their attributes and do not have a unique identifier.
 * They are immutable and should override equals and hashCode methods based on their attributes.
 */
public abstract class ValueObject {

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
