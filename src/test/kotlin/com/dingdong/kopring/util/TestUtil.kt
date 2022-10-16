package com.dingdong.kopring.util

import com.dingdong.kopring.dto.CourseDto
import com.dingdong.kopring.entity.Course
import com.dingdong.kopring.entity.Instructor
import java.util.Locale.Category

fun courseEntityList(instructor: Instructor? = null) = listOf(
    Course(null, "test1", "test1", instructor),
    Course(null, "test2", "test2", instructor),
    Course(null, "test3", "test3", instructor),
)

fun courseDto(
    id: Int? = null,
    name: String = "test",
    category: String = "test",
    instructorId: Int? = 1
) = CourseDto(
    id,
    name,
    category,
    instructorId
)

fun instructorEntity(name: String = "test") = Instructor(null, name)