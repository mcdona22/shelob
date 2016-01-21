package com.mcdona22.shelob.controller

import com.mcdona22.shelob.domain.JournalEntry
import com.mcdona22.shelob.service.JournalEntryDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController


@RestController
class JournalEntryController {
    @Autowired JournalEntryDao dao


    // TODO make this a POST
    Map saveUpdate(JournalEntry entry){
        return dao.createJournalEntry(entry)
    }
}

