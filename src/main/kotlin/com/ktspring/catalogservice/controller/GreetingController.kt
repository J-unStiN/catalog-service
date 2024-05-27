package com.ktspring.catalogservice.controller

import com.ktspring.catalogservice.service.GreetingService
import lombok.RequiredArgsConstructor
import mu.KLogging
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/greetings")
class GreetingController(
    val greetingService: GreetingService
) {

    private val staticLogger = KotlinLogging.logger {}


    @GetMapping("/{name}")
    fun retrieveGreeting(@PathVariable("name") name: String): String {
        staticLogger.info("Name is $name")
        return greetingService.retrieveGreeting(name);
    }


}