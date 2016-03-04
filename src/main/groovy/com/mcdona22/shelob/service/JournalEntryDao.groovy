package com.mcdona22.shelob.service
import com.mcdona22.shelob.domain.JournalEntry
import com.mcdona22.shelob.domain.JournalEntryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.annotation.Resource
import javax.validation.ConstraintViolation
import javax.validation.Valid
import javax.validation.Validator

@Service
class JournalEntryDao {
    final static Logger LOG = LoggerFactory.getLogger(JournalEntryDao)

    @Autowired JournalEntryRepository repository
    @Resource Validator validator

    Map createJournalEntry(@Valid JournalEntry entry){
        Map map = [entry: entry]
        Set<ConstraintViolation<JournalEntry>> errors

        errors = validator.validate(entry)

        if( errors.size()){
            List errorList = []
            errors.each{  violation ->
                println violation
                errorList << [(violation.propertyPath) : violation.interpolatedMessage]
            }

            map.errors = errorList
            LOG.warn "$entry is not valid : $errorList"

        } else {
            repository.save(entry)
            LOG.info "saved $entry"
        }

        return map
    }
}
