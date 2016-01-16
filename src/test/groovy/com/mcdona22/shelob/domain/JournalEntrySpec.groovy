package com.mcdona22.shelob.domain

import spock.lang.Specification

class JournalEntrySpec extends Specification{

    void "should behave correctly with the map constructor"(){
        setup:
            Map map = [timestamp : new Date(), serviceName: "name-of-service"]
        when:
            JournalEntry entry = new JournalEntry(map)
        then:
            entry.timestamp == map.timestamp
            entry.serviceName == map.serviceName
    }
}
