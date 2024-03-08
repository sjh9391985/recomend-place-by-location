package dev.be.project.direction.service

import dev.be.project.AbstractIntegrationContainerBaseTest
import dev.be.project.api.dto.DocumentDto
import dev.be.project.pharmacy.dto.PharmacyDto
import dev.be.project.pharmacy.service.PharmacySearchService
import spock.lang.Specification

class Base62ServiceTest extends Specification{

   private Base62Service base62Service;

    def setup() {
        base62Service = new Base62Service()
    }

    def "check base62 encoder/decoder" () {
        given:
        long num = 5

        when:
        def encodedId = base62Service.encodeDirectionId(num)

        def decodedId = base62Service.decodeDirection(encodedId)

        then:
        num == decodedId
    }


}
