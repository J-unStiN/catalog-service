package com.ktspring.catalogservice.entity

import jakarta.persistence.*


@Entity
@Table(name = "courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long?,
    val name : String,
    val category: String
)