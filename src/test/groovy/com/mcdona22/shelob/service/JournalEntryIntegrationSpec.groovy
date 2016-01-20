package com.mcdona22.shelob.service
import com.mcdona22.shelob.Application
import com.mcdona22.shelob.domain.EntryType
import com.mcdona22.shelob.domain.JournalEntry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import spock.lang.Specification

@SpringApplicationConfiguration(classes = Application)
class JournalEntryDaoIntegrationSpec extends Specification {

    @Autowired JournalEntryDao dao
    JournalEntry entry

    def setup(){
        dao.repository.deleteAll()
        entry = new JournalEntry(timestamp: new Date(), serviceName: 'test-service', entryType: EntryType.Register)
    }

    def "spring wiring should be correct"(){
        expect:
            dao?.repository
            dao?.validator
    }


    def "should write a valid entry to the database"(){
        expect:
            dao.repository.findAll().size() == 0
        when:
            Map map = dao.createJournalEntry(entry)
        then:
            map?.entry
            dao.repository.findAll().size() == 1
            map.entry.id != 0
            println map.entry
    }

    def "should not write an invalid entity"(){

        setup:
            entry.serviceName = ""
        expect:
        dao.repository.findAll().size() == 0
        when:
            Map map = dao.createJournalEntry(entry)
        then:
            map?.entry
            dao.repository.findAll().size() == 0
            map?.errors
            entry == map?.entry
            map.entry.id == 0
            println map.errors
    }
}
