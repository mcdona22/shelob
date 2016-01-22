package com.mcdona22.shelob.controller
import com.mcdona22.shelob.Application

import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

@SpringApplicationConfiguration(classes = Application)
class JournalEntryControllerIntegrationSpec extends Specification{
    @Autowired JournalEntryController controller
    MockMvc mvc
    String path = "/journalentry"

    def setup(){
        mvc = standaloneSetup(controller ).build()

    }
    def "wiring should be correct"(){
        expect:
            controller?.dao?.repository     // wired through the entire stack
    }

    def "well formed POST should successfully create a journal entry"(){
        setup:
            controller.dao.repository.deleteAll()
            Map entry = [createdOn: new Date(), serviceName: 'ctl-test-service']
            String json = new JsonBuilder(entry).toPrettyString()
            println json
        expect:
            controller.dao.repository.findAll().size() == 0
        when:
            MvcResult result = mvc.perform(post(path)
                                .content(json)
                                .header("Content-Type", "application/json"))
                            .andExpect(status().isOk())
                            .andExpect(content().contentType("application/json;charset=UTF-8"))
                            .andDo(print())
                            .andReturn()
        then:
            result.response.contentAsString
    }

}
