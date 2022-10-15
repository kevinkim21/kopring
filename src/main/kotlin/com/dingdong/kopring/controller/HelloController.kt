package com.dingdong.kopring.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api")
class HelloController {
    val log: Logger = LoggerFactory.getLogger(HelloController::class.java)

    @GetMapping("/hello/{name}")
    fun hellworld(@PathVariable name: String): String {
        return "$name world"
    }
}