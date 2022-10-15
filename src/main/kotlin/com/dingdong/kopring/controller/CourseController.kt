package com.dingdong.kopring.controller

import com.dingdong.kopring.dto.CourseDto
import com.dingdong.kopring.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses")
class CourseController(
    val courseService: CourseService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody courseDto: CourseDto): CourseDto = courseService.addCourse(courseDto)

    @GetMapping
    fun getCourseList(): List<CourseDto> = courseService.getCourselist()

}