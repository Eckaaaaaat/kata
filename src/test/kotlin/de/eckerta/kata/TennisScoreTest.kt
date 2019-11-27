package de.eckerta.kata

import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@ExperimentalUnsignedTypes
object TennisScoreTest : Spek({
    describe("Score") {

        val input = listOf(
            Triple(0U, 0U, "Love All"),
            Triple(1U, 1U, "Fifteen All"),
            Triple(2U, 2U, "Thirty All"),

            Triple(4U, 0U, "Win Player1"),
            Triple(5U, 0U, "Win Player1"),
            Triple(6U, 0U, "Win Player1"),

            Triple(0U, 4U, "Win Player2"),
            Triple(0U, 5U, "Win Player2"),
            Triple(0U, 6U, "Win Player2"),

            Triple(0U, 1U, "Love Fifteen"),
            Triple(0U, 2U, "Love Thirty"),
            Triple(0U, 3U, "Love Forty"),
            Triple(1U, 0U, "Fifteen Love"),
            Triple(1U, 2U, "Fifteen Thirty"),
            Triple(1U, 3U, "Fifteen Forty"),
            Triple(2U, 0U, "Thirty Love"),
            Triple(2U, 1U, "Thirty Fifteen"),
            Triple(2U, 3U, "Thirty Forty"),
            Triple(3U, 0U, "Forty Love"),
            Triple(3U, 1U, "Forty Fifteen"),
            Triple(3U, 2U, "Forty Thirty"),

            Triple(3U, 3U, "Deuce"),
            Triple(4U, 4U, "Deuce"),
            Triple(5U, 5U, "Deuce"),

            Triple(3U, 4U, "Advantage Player2"),
            Triple(4U, 5U, "Advantage Player2"),
            Triple(4U, 3U, "Advantage Player1"),
            Triple(5U, 4U, "Advantage Player1"),
            Triple(10U, 11U, "Advantage Player2"),

            Triple(3U, 5U, "Win Player2"),
            Triple(10U, 12U, "Win Player2"),
            Triple(5U, 3U, "Win Player1")
        )

        for ((player1, player2, expected) in input) {
            it ("$player1-$player2 should be $expected") {
                TennisScore().scoreToString(player1, player2) `should be equal to` expected
            }
        }
    }
})