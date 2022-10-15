package com.dingdong.kopring.controller

import com.dingdong.kopring.service.HelloService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient


@WebMvcTest(controllers = [HelloController::class])
@AutoConfigureWebTestClient
class HelloControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var helloServiceMock: HelloService

    @Test
    fun hello(){
        val name = "dingdong"

        every {helloServiceMock.hello(any())} returns name

        val result = webTestClient.get()
            .uri("/hello/{name}", name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()

        Assertions.assertEquals("$name", result.responseBody)
    }

}