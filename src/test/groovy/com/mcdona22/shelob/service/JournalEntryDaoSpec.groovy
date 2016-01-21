package com.mcdona22.shelob.service

import com.mcdona22.shelob.domain.JournalEntryRepository
import spock.lang.Specification

// TODO consider deleting this - it doesnt do anything the integration spec does
class JournalEntryDaoSpec extends Specification{
    JournalEntryDao dao
    JournalEntryRepository repository = Mock()

    def setup(){
        dao = new JournalEntryDao( repository: repository)
    }

    def "should be set up correctly"(){
        expect:
            dao.repository == repository
    }
}
