package com.ktspring.catalogservice.service

import com.ktspring.catalogservice.dto.CourseDTO
import com.ktspring.catalogservice.entity.Course
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


}