package com.dingdong.kopring.controller

import com.dingdong.kopring.dto.CourseDto
import com.dingdong.kopring.entity.Course
import com.dingdong.kopring.service.CourseService
import com.dingdong.kopring.service.HelloService
import com.dingdong.kopring.util.courseDto
import com.ninjasquad.springmockk.MockkBean
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
internal class CourseControllerUnitTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var courseServiceMockk: CourseService


    @Test
    fun addCourse() {
        val courseDto = CourseDto(null, "test", "test",1)
        //addCourse 실행시 mock 객체 return -> courseDto
        every { courseServiceMockk.addCourse(any()) } returns courseDto(id = 1)

        val result = webTestClient.post()
            .uri("/v1/courses", courseDto)
            .bodyValue(courseDto)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody
        Assertions.assertTrue(result!!.id != null)
        verify { courseServiceMockk.addCourse(any()) }
    }

    @Test
    fun getCourseList() {
        every { courseServiceMockk.getCourselist(any()) }.returnsMany(
            listOf(
                courseDto(id = 1),
                courseDto(id = 2, name = "test"),
            )
        )
        val uri = UriComponentsBuilder.fromUriString("/v1/courses")
            .queryParam("course_name", "test")
            .toUriString()

        val courseDtos = webTestClient.get()
            .uri(uri)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDto::class.java)
            .returnResult()
            .responseBody
        Assertions.assertEquals(2, courseDtos!!.size)
    }

    @Test
    fun updateCourse() {
        every { courseServiceMockk.updateCourse(any(), any()) } returns courseDto(
            id = 100,
            name = "test"
        )
        val updateCourseDto = CourseDto(null, "Hello test", "Hello test")

        val result = webTestClient.put()
            .uri("/v1/courses/{course_id}", 100)
            .bodyValue(updateCourseDto)
            .exchange()
            .expectStatus().isOk
            .expectBody(CourseDto::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals("test", result!!.name)
        Assertions.assertEquals(100, result!!.id)
    }

    @Test
    fun deleteCourse() {
        every { courseServiceMockk.deleteCourse(any()) } just runs

        val result = webTestClient.delete()
            .uri("/v1/courses/{course_id}", 100)
            .exchange()
            .expectStatus().isNoContent
    }

}