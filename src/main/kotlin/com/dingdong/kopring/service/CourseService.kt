package com.dingdong.kopring.service

import com.dingdong.kopring.dto.CourseDto
import com.dingdong.kopring.entity.Course
import com.dingdong.kopring.exception.CourseNotFoundException
import com.dingdong.kopring.exception.InstructorNotValidException
import com.dingdong.kopring.repository.CourseRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CourseService(
    val courseRepository: CourseRepository,
    val instructorService: InstructorService,
) {
    val log: Logger = LoggerFactory.getLogger(CourseService::class.java)

    fun addCourse(courseDto: CourseDto) : CourseDto{
        val instructorOptional = instructorService.findByInstructorId(courseDto.instructorId!!)
        if(!instructorOptional.isPresent) {
            throw InstructorNotValidException("Instructor not valid: ${courseDto.instructorId!!}")
        }
        val courseEntity = courseDto.let {
            Course(null, it.name, it.category, instructorOptional.get())
        }
        courseRepository.save(courseEntity)

        return courseEntity.let {
            CourseDto(it.id, it.name, it.category, it.instructor!!.id)
        }
    }

    fun getCourselist(courseName: String): List<CourseDto> {
        val courses = courseName?.let {
            courseRepository.findCourseByName(courseName)
        } ?: courseRepository.findAll()

        return courses
            .map {
                CourseDto(it.id, it.name, it.category)
            }
    }

    fun updateCourse(courseId: Int, courseDto: CourseDto): CourseDto {
        val existCourse = courseRepository.findById(courseId)

        return if(existCourse.isPresent) {
            existCourse.get()
                .let {
                    it.name = courseDto.name
                    it.category = courseDto.category
                    courseRepository.save(it)
                    CourseDto(it.id, it.name, it.category)
                }
        }else {
            throw CourseNotFoundException("Not found course : $courseId")
        }
    }

    fun deleteCourse(courseId: Int){
        val existCourse = courseRepository.findById(courseId)
        if(existCourse.isPresent) {
            existCourse.get()
                .let {
                    courseRepository.deleteById(courseId)
                }
        }else {
            throw CourseNotFoundException("Not found course : $courseId")
        }
    }

}
