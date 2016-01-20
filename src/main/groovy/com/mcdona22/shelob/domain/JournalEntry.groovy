package com.mcdona22.shelob.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Entity
class JournalEntry {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id

    @NotNull(message = "the timestamp is mandatory")
    Date timestamp

    @NotNull(message = "the service name is mandatory")
    @Pattern(regexp = '^\\w.*', message = "you may not use the empty string")
    String serviceName

    @NotNull(message = "the entry type is mandatory")
    EntryType entryType


    public String toString(){ "{id: $id, timestamp: $timestamp, serviceName: '$serviceName', entryType: $entryType}"}
}

