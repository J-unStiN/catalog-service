package com.ktspring.catalogservice.service

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class GreetingService {

    @Value("\${message}")
    lateinit var message: String;

    fun retrieveGreeting(name : String) : String {

        return "Hello, $name! $message";
    }



}