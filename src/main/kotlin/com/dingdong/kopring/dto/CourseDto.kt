package com.dingdong.kopring.dto

import javax.validation.constraints.NotBlank

data class CourseDto(
    val id: Int?,
    @get:NotBlank(message = "course.name must not be blank")
    val name: String,

    @get:NotBlank(message = "course.category must not be blank")
    val category: String,
)