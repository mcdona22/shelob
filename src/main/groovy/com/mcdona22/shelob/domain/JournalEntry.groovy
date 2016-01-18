package com.mcdona22.shelob.domain

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

class JournalEntry {
    int id
    @NotNull(message = "the timestamp is mandatory")
    Date timestamp

    @NotNull(message = "the service name is mandatory")
    @Pattern(regexp = '^\\w.*', message = "you may not use the empty string")
    String serviceName

    @NotNull(message = "the entry type is mandatory")
    EntryType entryType
}

