package com.xavier.servimatchbackend.common.domain;

import java.time.Instant;
import java.util.UUID;

/**
 * Base class for domain events in the domain model.
 * Domain events represent significant occurrences within the domain that can affect the state of aggregates.
 * Each event has a unique identifier, a timestamp indicating when it occurred, and an associated aggregate ID.
 */

public abstract class DomainEvent {

    private  final UUID eventId;
    private final Instant occurredOn;
    private final UUID aggregateId;

    protected DomainEvent(UUID aggregateId) {
        this.eventId = UUID.randomUUID();
        this.occurredOn = Instant.now();
        this.aggregateId = aggregateId;
    }
}
