package com.mcdona22.shelob.controller

import com.mcdona22.shelob.domain.JournalEntry
import com.mcdona22.shelob.service.JournalEntryDao
import spock.lang.Specification

import javax.servlet.http.HttpServletResponse

class JournalEntryControllerSpec extends Specification {
    Map returnMap
    JournalEntry testEntry
    JournalEntryDao dao = Mock()
    HttpServletResponse response = Mock()
    JournalEntryController controller

    def setup(){
        controller = new JournalEntryController()
        controller.dao = dao
    }

    def "well formed request should return a map"(){
        when:
            Map map = controller.saveUpdate(testEntry, response)
        then:
            1 * dao.createJournalEntry(testEntry) >> returnMap
            map == returnMap
    }
}
