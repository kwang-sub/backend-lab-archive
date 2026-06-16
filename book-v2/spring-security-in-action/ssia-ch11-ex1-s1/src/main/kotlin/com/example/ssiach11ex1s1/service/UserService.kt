package com.example.ssiach11ex1s1.service

import com.example.ssiach11ex1s1.domain.Otp
import com.example.ssiach11ex1s1.domain.User
import com.example.ssiach11ex1s1.repository.OtpRepository
import com.example.ssiach11ex1s1.repository.UserRepository
import com.example.ssiach11ex1s1.utill.GenerateCodeUtil
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val otpRepository: OtpRepository,
) {

    fun addUser(user: User) {
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user)
    }

    fun auth(user: User) {
        val findUserOptional = userRepository.findByUsername(user.username)
        if (findUserOptional.isPresent) {
            val findUser = findUserOptional.get()
            if (passwordEncoder.matches(user.password, findUser.password)) renewOtp(findUser)
            else throw BadCredentialsException("Bad credentials.")
        } else throw BadCredentialsException("Bad credentials.")
    }

    fun renewOtp(user: User) {
        val code = GenerateCodeUtil.generateCode()
        val otpOptional = otpRepository.findByUsername(user.username)
        if (otpOptional.isPresent) {
            val otp = otpOptional.get()
            otp.code = code
        } else {
            Otp(user.username, code)
                .let { otpRepository.save(it) }
        }
    }

    fun check(otpToValidate: Otp): Boolean {
        val userOtp = otpRepository.findByUsername(otpToValidate.username)
        if (userOtp.isPresent) {
            val otp = userOtp.get()
            if (otpToValidate.code == otp.code) return true
        }

        return false
    }
}