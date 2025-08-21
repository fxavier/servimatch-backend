package com.xavier.servimatchbackend.common.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.util.Collection;
import java.util.UUID;

/**
 * Base class for aggregate roots in the domain model.
 * Provides a unique identifier and overrides equals and hashCode methods.
 *
 * @param <T> the type of the aggregate root
 */

@Getter
@MappedSuperclass
public abstract class AggregateRoot<T extends AggregateRoot<T>> extends AbstractAggregateRoot<T> {

    protected UUID id;

    protected AggregateRoot() {
        this.id = UUID.randomUUID();
    }

    protected AggregateRoot(UUID id) {
        this.id = id;
    }

    // Spring Data will automatically call this to get events to publish
    @DomainEvents
    protected Collection<Object> releaseEvents() {
        return domainEvents();
    }

    // Spring Data will call this after events are published
    @AfterDomainEventPublication
    protected void clearEvents() {
        clearDomainEvents();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AggregateRoot<?> that = (AggregateRoot<?>) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
