package com.dingdong.kopring.intg

import com.dingdong.kopring.dto.CourseDto
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
internal class CourseControllerIntgTest{
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun addCourse(){
        val courseDto = CourseDto(null, "test", "test")
        val result = webTestClient.post()
            .uri("/v1/courcses", courseDto)
            .bodyValue(courseDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody
        assertTrue(result!!.id != null)
    }
}