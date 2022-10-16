package com.dingdong.kopring.repository

import com.dingdong.kopring.entity.Instructor
import org.springframework.data.repository.CrudRepository

interface InstructorRepository: CrudRepository<Instructor, Int> {
}