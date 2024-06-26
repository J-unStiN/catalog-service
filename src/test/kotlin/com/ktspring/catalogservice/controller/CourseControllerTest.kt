package com.ktspring.catalogservice.controller

import com.ktspring.catalogservice.dto.CourseDTO
import com.ktspring.catalogservice.entity.Course
import com.ktspring.catalogservice.repository.CourseRepository
import com.ktspring.catalogservice.util.courseEntityList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp() {
        courseRepository.deleteAll();
        val courses = courseEntityList();
        courseRepository.saveAll(courses);
    }

    @Test
    fun createCourse() {
        val courseDTO = CourseDTO(id = null,"테스트용 이름", "테스트용 카테고리");
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
    fun retrieveAllCourses() {
        val courseDTOs = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        assertEquals(3, courseDTOs!!.size);
    }

    @Test
    fun updateCourse() {

        val course = Course(null, "테스용 이름입니다,", "개발")
        courseRepository.save(course);

        val courseDTO = CourseDTO(null, "테스용 이름111입니다", "개발11")

        val updateCourse = webTestClient
            .put()
            .uri("/v1/courses/{courseId}", course.id)
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isOk
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        assertEquals("테스용 이름111입니다", updateCourse!!.name)
    }

    @Test
    fun deleteCourse() {

        val course = Course(null, "테스용 이름입니다,", "개발")
        courseRepository.save(course);

        val updateCourse = webTestClient
            .delete()
            .uri("/v1/courses/{courseId}", course.id)
            .exchange()
            .expectStatus().isNoContent

    }

}