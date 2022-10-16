package com.dingdong.kopring.service

import com.dingdong.kopring.dto.InstructorDto
import com.dingdong.kopring.entity.Instructor
import com.dingdong.kopring.repository.InstructorRepository
import org.springframework.stereotype.Service

@Service
class InstructorService(
    val instructorRepository: InstructorRepository,
) {
    fun createInstructor(instructorDto: InstructorDto): InstructorDto {
        val instructor = instructorDto.let {
            Instructor(it.id, it.name)
        }
        instructorRepository.save(instructor)
        return instructor.let {
            InstructorDto(it.id, it.name)
        }
    }

}
