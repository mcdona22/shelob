package com.mcdona22.shelob.domain
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

class JournalEntrySpec extends Specification{

    static Validator validator

    def setupSpec(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }


    void "should behave correctly with the map constructor"(){
        setup:
            Map map = [timestamp : new Date(), serviceName: "name-of-service", entryType: EntryType.Register]
        when:
            JournalEntry entry = new JournalEntry(map)
        then:
            entry.timestamp == map.timestamp
            entry.serviceName == map.serviceName
            entry.entryType == map.entryType
            entry.id == 0   // default value
    }

    void "the object should fail validation correctly"(){
        setup:
            Map map = [timestamp: timestamp, serviceName: serviceName,entryType: entryType]
            JournalEntry entry = new JournalEntry(map)
        when:
            Set<ConstraintViolation<JournalEntry>> violations = validator.validate(entry)
        then:
            println map
            println violations
            assert violations.size() == 1
            assert violations[0]?.propertyPath.toString() == property
            violations[0]?.message
        where:
        property        |timestamp  |serviceName    |entryType
        "timestamp"     |null       |"test-service" | EntryType.Register
        "serviceName"   |new Date() | null          | EntryType.Register
        "serviceName"   |new Date() | "  "          | EntryType.Register
        "serviceName"   |new Date() | ""            | EntryType.Register
        "entryType"     |new Date() | "test-service"| null


    }
}
