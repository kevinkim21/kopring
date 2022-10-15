package com.dingdong.kopring.repository

import com.dingdong.kopring.entity.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository : CrudRepository<Course, Int> {
}