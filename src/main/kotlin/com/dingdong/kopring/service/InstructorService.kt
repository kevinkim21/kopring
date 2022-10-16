package com.dingdong.kopring.service

import com.dingdong.kopring.dto.InstructorDto
import com.dingdong.kopring.entity.Instructor
import com.dingdong.kopring.repository.InstructorRepository
import org.springframework.stereotype.Service
import java.util.*

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

    fun findByInstructorId(instructorId: Int): Optional<Instructor> {
        return instructorRepository.findById(instructorId)
    }

}
