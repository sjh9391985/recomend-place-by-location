package dev.be.project.direction.controller

import dev.be.project.direction.dto.OutputDto
import dev.be.project.direction.service.DirectionService
import dev.be.project.pharmacy.service.PharmacyRecommendationService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class DirectionControllerTest extends Specification {

    private MockMvc mockMvc

    private DirectionService directionService = Mock()

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new DirectionController(directionService))
                .build()
    }

    def "GET /dir/{encodedId}"() {
        given:
        String encodedId = "r"
        String redirectURL = "https://map.kakao.com/link/map/pharmacy,38.11,128.11"

        when:
        directionService.findDirectionUrlById(encodedId) >> redirectURL

        def result = mockMvc.perform { get("/dir/{encodedId}", encodedId) }

        then:
        result
                .andExpect { status().is3xxRedirection() }
                .andExpect { redirectURL(redirectURL) }
                .andDo { print() }

    }


}
