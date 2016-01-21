package com.mcdona22.shelob.controller

import com.mcdona22.shelob.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import spock.lang.Specification


@SpringApplicationConfiguration(classes = Application)

class JournalEntryControllerIntegrationSpec extends Specification{
    @Autowired JournalEntryController controller

    def "wiring should be correct"(){
        expect:
            controller?.dao?.repository     // wired through the entire stack
    }

}
