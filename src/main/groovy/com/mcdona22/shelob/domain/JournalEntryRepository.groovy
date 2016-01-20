package com.mcdona22.shelob.domain
import org.springframework.data.repository.CrudRepository

interface JournalEntryRepository extends CrudRepository<JournalEntry, Long> {

}
