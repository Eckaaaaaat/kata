package de.eckerta.kata

import java.lang.Math.abs

@ExperimentalUnsignedTypes
class TennisScore() {
    fun scoreToString(player1Score: UInt, player2Score: UInt): String {
        return when {
            minOf(player1Score, player2Score) < 3U -> earlyGame(player1Score, player2Score)
            else -> lateGame(player1Score, player2Score)
        }
    }

    private fun earlyGame(player1Score: UInt, player2Score: UInt): String {
        return when {
            player1Score == player2Score -> "${scoreToName(player1Score)} All"
            player1Score >= 4U -> "Win Player1"
            player2Score >= 4U -> "Win Player2"
            else -> "${scoreToName(player1Score)} ${scoreToName(player2Score)}"
        }
    }

    private fun lateGame(player1Score: UInt, player2Score: UInt): String {
        return when {
            player1Score == player2Score -> "Deuce"
            player1Score == player2Score + 1U -> "Advantage Player1"
            player1Score + 1U == player2Score -> "Advantage Player2"
            player1Score > player2Score -> "Win Player1"
            else -> "Win Player2"
        }
    }

    private fun scoreToName(score: UInt) = when (score) {
        0U -> "Love"
        1U -> "Fifteen"
        2U -> "Thirty"
        3U -> "Forty"
        else -> null
    }
}