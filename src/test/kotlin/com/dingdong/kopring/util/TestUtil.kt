package com.dingdong.kopring.util

import com.dingdong.kopring.dto.CourseDto
import com.dingdong.kopring.entity.Course
import java.util.Locale.Category

fun courseEntityList() = listOf(
    Course(null, "test1", "test1"),
    Course(null, "test2", "test2"),
    Course(null, "test3", "test3"),
)

fun courseDto(
    id: Int? = null,
    name: String = "test",
    category: String = "test",
) = CourseDto(
    id,
    name,
    category,
)