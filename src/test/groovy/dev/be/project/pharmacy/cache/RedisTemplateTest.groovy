package dev.be.project.pharmacy.cache

import dev.be.project.AbstractIntegrationContainerBaseTest
import dev.be.project.pharmacy.entity.Pharmacy
import dev.be.project.pharmacy.repository.PharmacyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate

import java.time.LocalDateTime

class RedisTemplateTest extends AbstractIntegrationContainerBaseTest {

//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    def "RedisTemplate String operation"() {
//
//        given:
//        def valueOperation = redisTemplate.opsForValue()
//        def key = "stringKey"
//        def value = "hello"
//
//        when:
//        valueOperation.set(key, value)
//
//        then:
//        def result = valueOperation.get(key)
//        result == value
//
//    }
//
//    def "RedisTemplate set operations"() {
//
//        given:
//        def setOperation = redisTemplate.opsForSet()
//        def key = "setKey"
//
//        when:
//        setOperation.add(key, "h", "e", "l", "l", "o")
//
//        then:
//        def size = setOperation.size(key)
//        size == 4
//
//    }
//
//    def "RedisTemplate hash operations" () {
//        given:
//        def hashOperation = redisTemplate.opsForHash()
//        def key = "hashKey"
//
//        when:
//        hashOperation.put(key, "subKey", "value")
//
//        then:
//        def result = hashOperation.get(key, "subKey")
//        result == "value"
//    }

}
