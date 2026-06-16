package com.example.ssiach11ex1s2.security

import com.example.ssiach11ex1s2.domain.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.web.client.RestTemplate

class AuthenticationServerProxy(
    private val rest: RestTemplate,
) {
    @Value("\${auth.server.base.url")
    private var baseUrl: String? = null

    fun sendAuth(username: String, password: String) {
        val url = baseUrl + "/user/auth"

        val user = User(username = username, password = password)

        val request = HttpEntity<User>(user)
        rest.postForEntity(url, request, Unit::class.java)
    }

    fun sendOTP(username: String, code: String): Boolean {
        val url = baseUrl + "/otp/check"
        val user = User(username = username, code = code)
        val request = HttpEntity<User>(user)
        val response = rest.postForEntity(url, request, Unit::class.java)

        return response.statusCode.is2xxSuccessful
    }

}