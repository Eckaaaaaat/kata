package de.eckerta.kata

import org.amshove.kluent.`should equal`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class ParserTest : Spek({
    Feature("Board parsing") {
        val expected = listOf(
                listOf(Field.EMPTY, Field.EMPTY, Field.EMPTY),
                listOf(Field.X, Field.O, Field.EMPTY),
                listOf(Field.EMPTY, Field.EMPTY, Field.X))

        Scenario("a board description with leading or trailing whitespace on a line") {
            // invalid character M and extra whitespace in 2nd line should be ignored
            val board = "...\nXO.  \n  ..X"

            lateinit var result : List<List<Field>>
            When("parsing") {
                result = board.toFields()
            }

            Then("white space characters should be ignored") {
                result `should equal` expected
            }
        }

        Scenario ( "a board description without white space characters" ) {
            // invalid character M and extra whitespace in 2nd line should be ignored
            val board = "..M\nXO.\n@.X"

            lateinit var result : List<List<Field>>
            When("parsing") {
                result = board.toFields()
            }

            Then("everything except X and O should be treated as empty field") {
                    result `should equal` expected
            }
        }
    }

    Feature("Line Status") {
        val cases = arrayOf(
                Pair("...", State.UNFINISHED),
                Pair("..X", State.UNFINISHED),
                Pair("..O", State.UNFINISHED),
                Pair(".X.", State.UNFINISHED),
                Pair(".XX", State.UNFINISHED),
                Pair(".XO", State.UNFINISHED),
                Pair(".O.", State.UNFINISHED),
                Pair(".OX", State.UNFINISHED),
                Pair(".OO", State.UNFINISHED),
                Pair("X..", State.UNFINISHED),
                Pair("X.X", State.UNFINISHED),
                Pair("X.O", State.UNFINISHED),
                Pair("XX.", State.UNFINISHED),
                Pair("XXX", State.X),
                Pair("XXO", State.DRAW),
                Pair("XO.", State.UNFINISHED),
                Pair("XOX", State.DRAW),
                Pair("XOO", State.DRAW),
                Pair("O..", State.UNFINISHED),
                Pair("O.X", State.UNFINISHED),
                Pair("O.O", State.UNFINISHED),
                Pair("OX.", State.UNFINISHED),
                Pair("OXX", State.DRAW),
                Pair("OXO", State.DRAW),
                Pair("OO.", State.UNFINISHED),
                Pair("OOX", State.DRAW),
                Pair("OOO", State.O)
        )

        cases.forEach { (lineSpec, state) ->
            Scenario("given the line $lineSpec") {
                lateinit var result : State
                When("getting the state") {
                    result = lineSpec.toFields()[0].state()
                }
                Then("it should be $state") {
                    result `should equal` state
                }
            }
        }
    }

    Feature("Board Status") {
        Scenario ("parsing a board") {
            val boardSpec = """
                ..X
                XO.
                .XO
            """.trimIndent()

            val board = Board(boardSpec.toFields())

            Then("it should match the columns") {
                val columnSpec = """
                .X.
                .OX
                X.O
                """.trimIndent()

                board.columns `should equal` columnSpec.toFields()
            }

            Then ("it should match the diagonals") {
                val diagonalsSpec = """
                .OO
                XO.
                """.trimIndent()

                board.diagonals `should equal` diagonalsSpec.toFields()
            }
        }

        Scenario ("a board with horizontal winning position for O") {
            val boardSpec = """
            XO.
            XOX
            .O.
            """.trimIndent()

            Then ("should have state X") {
                Board(boardSpec.toFields()).state `should equal` State.O
            }
        }

        Scenario ("a full board with vertical winning position for X") {
            val boardSpec = """
            OOX
            XOX
            OXX
            """.trimIndent()

            Then ("should have state X") {
                Board(boardSpec.toFields()).state `should equal` State.X
            }
        }

        Scenario ("a board with diagonal winning position for X") {
            val boardSpec = """
            XOO
            OXX
            OXX
            """.trimIndent()

            Then ("should have state X") {
                Board(boardSpec.toFields()).state `should equal` State.X
            }
        }

        Scenario ("a board with diagonal winning position for O") {
            val boardSpec = """
            XOO
            XOX
            OXX
            """.trimIndent()

            Then ("should have O") {
                Board(boardSpec.toFields()).state `should equal` State.O
            }
        }

        Scenario ("a full board with no position") {
            val boardSpec = """
            OXX
            XOO
            OXX
            """.trimIndent()

            Then ("should have state Draw") {
                Board(boardSpec.toFields()).state `should equal` State.DRAW
            }
        }

        Scenario ("a board (not full) with no winning position") {
            val boardSpec = """
            OXX
            XO.
            OXX
            """.trimIndent()

            Then ("should have de.state Unfinished") {
                Board(boardSpec.toFields()).state `should equal` State.UNFINISHED
            }
        }

    }
})