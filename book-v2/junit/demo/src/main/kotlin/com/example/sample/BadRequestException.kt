package com.example.sample

import java.lang.RuntimeException

class BadRequestException: RuntimeException("Invalid input size, you must input 3 length") {
}