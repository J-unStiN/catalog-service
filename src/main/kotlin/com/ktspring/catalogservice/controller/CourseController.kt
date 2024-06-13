package com.ktspring.catalogservice.controller

import com.ktspring.catalogservice.dto.CourseDTO
import com.ktspring.catalogservice.service.CourseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses")
@Validated
class CourseController(
    val courseService: CourseService
) {


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCourse(@RequestBody @Valid courseDTO: CourseDTO): CourseDTO {

        return courseService.createCourse(courseDTO);
    }

    @GetMapping
    fun retrieveAllCourses(): List<CourseDTO> = courseService.retrieveAllCourses();



    @PutMapping("/{course_id}")
    fun updateCourse(@PathVariable("course_id") course_id: Long,
                     @RequestBody courseDTO: CourseDTO)
    = courseService.updateCourse(course_id, courseDTO);


    @DeleteMapping("/{course_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable("course_id") course_id: Long) {
        courseService.deleteCourse(course_id);
    }


}