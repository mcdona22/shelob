package com.mcdona22.shelob.controller
import com.mcdona22.shelob.domain.JournalEntry
import com.mcdona22.shelob.service.JournalEntryDao
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletResponse

@RestController
class JournalEntryController {
    final static Logger LOG = LoggerFactory.getLogger(JournalEntryController)
    @Autowired JournalEntryDao dao


    @RequestMapping( value = "/journalentry", method = RequestMethod.POST)
    Map saveUpdate(@RequestBody JournalEntry entry, HttpServletResponse response){
        LOG.info "about to write $entry"
        Map returnValue = dao.createJournalEntry(entry)
        LOG.info "service returned : $returnValue"
        if( returnValue?.errors ){
            response.status = 500
        }
        return returnValue
    }
}

