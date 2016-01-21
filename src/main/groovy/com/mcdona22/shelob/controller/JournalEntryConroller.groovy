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

@RestController
class JournalEntryController {
    final static Logger LOG = LoggerFactory.getLogger(JournalEntryController)
    @Autowired JournalEntryDao dao


    // TODO make this a POST
    @RequestMapping( value = "/journalentry", method = RequestMethod.POST)
    Map saveUpdate(@RequestBody JournalEntry entry){
        LOG.info "about to write $entry"
        return dao.createJournalEntry(entry)
    }
}

