package com.xavier.servimatchbackend.common.domain;

import lombok.Getter;

import java.util.UUID;
/**
 * Base class for domain entities.
 * Provides a unique identifier and overrides equals and hashCode methods.
 */

@Getter
public abstract class DomainEntity {

    protected final UUID id;

    protected DomainEntity() {
        this.id = UUID.randomUUID();
    }
    protected DomainEntity(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEntity that = (DomainEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
