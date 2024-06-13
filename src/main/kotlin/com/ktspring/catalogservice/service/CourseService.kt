package com.ktspring.catalogservice.service

import com.ktspring.catalogservice.dto.CourseDTO
import com.ktspring.catalogservice.entity.Course
import com.ktspring.catalogservice.exception.CourseNotFoundException
import com.ktspring.catalogservice.repository.CourseRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CourseService(val courseRepository: CourseRepository) {

    private val logger = KotlinLogging.logger {}

    fun createCourse(courseDto: CourseDTO): CourseDTO {

        val courseEntity = courseDto.let {
            Course(null, it.name, it.category)
        }

        val saveEntity = courseRepository.save(courseEntity);

        logger.info { "save entity $saveEntity" }

        return saveEntity.let {
            CourseDTO(it.id, it.name, it.category)
        }
    }


    fun retrieveAllCourses(): List<CourseDTO> {
        return courseRepository.findAll()
            .map {
                CourseDTO(it.id, it.name, it.category)
            }
    }

    fun updateCourse(courseId: Long, courseDTO: CourseDTO): CourseDTO {
        val existingCourse = courseRepository.findById(courseId);

        return if(existingCourse.isPresent){
            existingCourse.get()
                .let {
                    it.name = courseDTO.name
                    it.category = courseDTO.category
                    courseRepository.save(it)
                    CourseDTO(it.id, it.name, it.category)
                }

        }else {
            throw CourseNotFoundException("course not found : $courseId")
        }

    }

    fun deleteCourse(courseId: Long) {
        val findCourse = courseRepository.findById(courseId);

        if(findCourse.isPresent){
            findCourse.get()
                .let {
                    courseRepository.deleteById(courseId)
                }
        } else {
            throw CourseNotFoundException("course not found : $courseId")
        }

    }


}