package de.eckerta.kata

import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object KataTest : Spek({
    describe("Kata") {

        val input = listOf(
            Triple(1, 1, 2)
        )

        for ((left, right, sum) in input) {
            it ("$left + $right should be $sum") {
                sum(left, right) `should be equal to` sum
            }
        }
    }
})