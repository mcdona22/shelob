package com.mcdona22.shelob.domain
import spock.lang.Specification
import spock.lang.Unroll

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

    void "should sing the national anthem on creation"(){}

    void "should behave correctly with the map constructor"(){
        setup:
            Date beforeCreation = new Date()
            Map map = [ serviceName: "name-of-service"]
        when:
            JournalEntry entry = new JournalEntry(map)
        then:
            entry.createdOn?.time >= beforeCreation.time
            entry.serviceName == map.serviceName
            entry.id == 0   // default value
    }


    @Unroll
    void "the object should fail validation correctly"(){
        setup:
            Map map = [ serviceName: serviceName, createdOn: timestamp]
            JournalEntry entry = new JournalEntry(map)
        when:
            Set<ConstraintViolation<JournalEntry>> violations = validator.validate(entry)
        then:
            println map
            println violations
            violations.size() == 1
            assert violations[0]?.propertyPath.toString() == property
            violations[0]?.message
        where:
        property        |timestamp  |serviceName
        "createdOn"     |null       |"test-service"
        "serviceName"   |new Date() | null
        "serviceName"   |new Date() | "  "
        "serviceName"   |new Date() | ""
    }
}
