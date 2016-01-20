package com.mcdona22.shelob.service

import com.mcdona22.shelob.domain.JournalEntry
import com.mcdona22.shelob.domain.JournalEntryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.validation.ConstraintViolation
import javax.validation.Validator

import javax.annotation.Resource

@Service
class JournalEntryDao {
    @Autowired JournalEntryRepository repository
    @Resource Validator validator

    Map createJournalEntry(JournalEntry entry){
        Map map = [entry: entry]
        Set<ConstraintViolation<JournalEntry>> errors

        errors = validator.validate(entry)

        if( errors.size()){
            println "$entry is not valid"
            map.errors = errors
        } else {
            repository.save(entry)
        }

        return map
    }
}
