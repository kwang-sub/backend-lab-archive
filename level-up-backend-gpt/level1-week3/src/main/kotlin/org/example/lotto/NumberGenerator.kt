package org.example.org.example.lotto

class NumberGenerator {
    companion object {
        private var ID = 1

        fun generateId(): Int {
            return ID++
        }
    }
}