package com.ktspring.catalogservice.unit

import com.ktspring.catalogservice.controller.CourseController
import com.ktspring.catalogservice.dto.CourseDTO
import com.ktspring.catalogservice.entity.Course
import com.ktspring.catalogservice.service.CourseService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.MockMvc


@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var service: CourseService


    @Test
    fun addCourseTest() {

        val courseDTO = CourseDTO(null, "Test", "카테고리")

        val responseBody = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue(
            responseBody!!.id != null
        )
    }


    @Test
    fun addCourseValid() {
        val courseDTO = CourseDTO(null, "", "")

        val responseBody = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isBadRequest

    }



}