package com.ktspring.catalogservice.service

import com.ktspring.catalogservice.controller.GreetingController
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient


@WebMvcTest(controllers = [GreetingController::class])
@AutoConfigureWebTestClient
class GreetingServiceTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var greetingService: GreetingService


    @Test
    fun getGreeting() {

        val name = "sj";

        val returnResult = webTestClient.get()
            .uri("/v1/greetings/{name}", name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()

        assertEquals("Hello, $name! Hello profile!", returnResult.responseBody)
    }

}