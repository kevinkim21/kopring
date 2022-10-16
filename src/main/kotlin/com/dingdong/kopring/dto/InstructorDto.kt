package com.dingdong.kopring.dto

import javax.validation.constraints.NotBlank


class InstructorDto(
    val id: Int?,
    @get:NotBlank(message = "instructorDto.name must not be blank")
    var name: String,
)