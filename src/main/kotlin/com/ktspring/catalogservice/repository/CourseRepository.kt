package com.ktspring.catalogservice.repository

import com.ktspring.catalogservice.entity.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Long> {

}