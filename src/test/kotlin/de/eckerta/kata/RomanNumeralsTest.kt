package de.eckerta.kata

import org.amshove.kluent.`should be equal to`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object RomanNumeralsTest : Spek({
    describe("Kata") {

        val input = listOf(
            Pair(0, "")
            , Pair(1, "I")
            , Pair(2, "II")
            , Pair(3, "III")
            , Pair(4, "IV")
            , Pair(5, "V")
            , Pair(6, "VI")
            , Pair(7, "VII")
            , Pair(8, "VIII")
            , Pair(9, "IX")
            , Pair(10, "X")
            , Pair(15, "XV")
            , Pair(44, "XLIV")
            , Pair(48, "XLVIII")
            , Pair(49, "XLIX")
            , Pair(88, "LXXXVIII")
            , Pair(99, "XCIX")
            , Pair(50, "L")
            , Pair(100, "C")
            , Pair(500, "D")
            , Pair(1000, "M")
            , Pair(2000, "MM")
            , Pair(999, "CMXCIX")
            , Pair(3132, "MMMCXXXII")

        )

        for ((number, roman) in input) {
            it ("$number should be $roman") {
                RomanNumerals.translate(number) `should be equal to` roman
            }
        }
    }
})