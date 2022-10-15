package com.dingdong.kopring.service

import org.springframework.stereotype.Service

@Service
class HelloService {
    fun hello(name:String): String {
        return name
    }
}