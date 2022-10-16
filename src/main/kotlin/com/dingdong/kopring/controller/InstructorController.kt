package com.dingdong.kopring.controller

import com.dingdong.kopring.dto.InstructorDto
import com.dingdong.kopring.service.InstructorService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/instructors")
@Validated
class InstructorController(
    val instructorService: InstructorService,
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody instructorDto: InstructorDto) = instructorService.createInstructor(instructorDto)
}