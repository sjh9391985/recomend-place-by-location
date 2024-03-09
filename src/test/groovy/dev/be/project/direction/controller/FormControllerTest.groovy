package dev.be.project.direction.controller

import dev.be.project.direction.dto.OutputDto
import dev.be.project.pharmacy.service.PharmacyRecommendationService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class FormControllerTest extends Specification {

//    private MockMvc mockMvc
//    private PharmacyRecommendationService pharmacyRecommendationService = Mock()
//
//    private List<OutputDto> outputDtoList
//
//    def setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(new FormController(pharmacyRecommendationService)).build()
//        outputDtoList = new ArrayList<>()
//        outputDtoList.addAll(
//                OutputDto.builder()
//                        .pharmacyName("pharmacy1")
//                        .build(),
//                OutputDto.builder()
//                        .pharmacyName("pharmacy2")
//                        .build()
//        )
//    }
//
//    def "GET /"() {
//        expect:
//        mockMvc.perform(get("/"))
//                .andExpect(handler.handleType(FormController.cast()))
//                .andExpect(handler.methodName("main"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("main"))
//                .andDo(log())
//    }
//
//    def "POST /search"() {
//        given:
//        String inputAddress = "서울 성북구 종암동"
//
//        when:
//        def resultActions = mockMvc.perform(post("/search").param("address", inputAddress))
//
//        then:
//        1 * pharmacyRecommendationService.recommendPharmacyList(argument -> {
//            assert argument == inputAddress // mock 객체 args 검즘
//        }) >> outputDtoList
//
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(view().name("output"))
//                .andExpect(model().attributeExists("outputFormList"))
//                .andExpect(model().attribute("outputFormList", outputDtoList))
//                .andDo(print())
//
//    }
}
