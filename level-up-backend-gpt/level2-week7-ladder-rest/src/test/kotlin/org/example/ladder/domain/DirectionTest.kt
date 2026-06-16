package org.example.ladder.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DirectionTest {

    @Test
    fun move_returnsNextIndex_whenDirectionIsRight() {
        val result = Direction.RIGHT.move(2)
        assertEquals(3, result)
    }

    @Test
    fun move_returnsPreviousIndex_whenDirectionIsLeft() {
        val result = Direction.LEFT.move(2)
        assertEquals(1, result)
    }

    @Test
    fun canMove_returnsTrue_whenRightConnectionExists() {
        val points = listOf(Point(true), Point(false))
        val result = Direction.RIGHT.canMove(0, points)
        assertTrue(result)
    }

    @Test
    fun canMove_returnsFalse_whenRightConnectionDoesNotExist() {
        val points = listOf(Point(false), Point(false))
        val result = Direction.RIGHT.canMove(0, points)
        assertFalse(result)
    }

    @Test
    fun canMove_returnsTrue_whenLeftConnectionExists() {
        val points = listOf(Point(false), Point(true))
        val result = Direction.LEFT.canMove(1, points)
        assertFalse(result)
    }

    @Test
    fun canMove_returnsFalse_whenLeftConnectionDoesNotExist() {
        val points = listOf(Point(false), Point(false))
        val result = Direction.LEFT.canMove(1, points)
        assertFalse(result)
    }
}