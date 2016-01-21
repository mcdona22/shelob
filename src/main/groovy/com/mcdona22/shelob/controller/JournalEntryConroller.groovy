package com.mcdona22.shelob.controller

import com.mcdona22.shelob.domain.JournalEntry
import com.mcdona22.shelob.service.JournalEntryDao

class JournalEntryController {

    JournalEntryDao dao

    Map saveUpdate(JournalEntry entry){
        return dao.createJournalEntry(entry)
    }
}

