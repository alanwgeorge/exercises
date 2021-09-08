@file:Suppress("DuplicatedCode")

package exercises.sudokuverifier

/*
 * https://leetcode.com/problems/valid-sudoku/
 */

class Solution {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        (0..8).map { row ->
            (0..8).map { col ->
                row to col
            }
        }.forEach {
            if(!validate(board, it)) return false
        }

        (0..8).map { col ->
            (0..8).map { row ->
                row to col
            }
        }.forEach {
            if(!validate(board, it)) return false
        }

        listOf(0..2, 3..5, 6..8).forEach { rangeX ->
            listOf(0..2, 3..5, 6..8).forEach { rangeY ->
                rangeX.flatMap { row ->
                    rangeY.map { col ->
                        row to col
                    }
                }.also {
                    if(!validate(board, it)) return false
                }
            }
        }

        return true
    }

    fun validate(board: Array<CharArray>, coordinates: List<Pair<Int, Int>>): Boolean {
        val values = coordinates.map {
            loops++
            Character.getNumericValue(board[it.first][it.second])
        }.filter {
            it != -1
        }

        val inRange = values.all {
            loops++
            it in 1..9
        }

        val noDuplicates = values.toSet().size == values.size

        return inRange && noDuplicates
    }

    companion object {
        var loops = 0
    }
}

data class Test(val board: List<CharArray>, val solution: Boolean)

fun main() {
    val tests = listOf(
        Test(board = listOf(
            charArrayOf('5','3','.','.','7','.','.','.','.'),
            charArrayOf('6','.','.','1','9','5','.','.','.'),
            charArrayOf('.','9','8','.','.','.','.','6','.'),
            charArrayOf('8','.','.','.','6','.','.','.','3'),
            charArrayOf('4','.','.','8','.','3','.','.','1'),
            charArrayOf('7','.','.','.','2','.','.','.','6'),
            charArrayOf('.','6','.','.','.','.','2','8','.'),
            charArrayOf('.','.','.','4','1','9','.','.','5'),
            charArrayOf('.','.','.','.','8','.','.','7','9')
        ), solution = true),
        Test(board = listOf(
            charArrayOf('8','3','.','.','7','.','.','.','.'),
            charArrayOf('6','.','.','1','9','5','.','.','.'),
            charArrayOf('.','9','8','.','.','.','.','6','.'),
            charArrayOf('8','.','.','.','6','.','.','.','3'),
            charArrayOf('4','.','.','8','.','3','.','.','1'),
            charArrayOf('7','.','.','.','2','.','.','.','6'),
            charArrayOf('.','6','.','.','.','.','2','8','.'),
            charArrayOf('.','.','.','4','1','9','.','.','5'),
            charArrayOf('.','.','.','.','8','.','.','7','9')
        ), solution = false),
        Test(board = listOf(
            charArrayOf('5','3','4','6','7','8','9','1','2'),
            charArrayOf('6','7','2','1','9','5','3','4','8'),
            charArrayOf('1','9','8','3','4','2','5','6','7'),
            charArrayOf('8','5','9','7','6','1','4','2','3'),
            charArrayOf('4','2','6','8','5','3','7','9','1'),
            charArrayOf('7','1','3','9','2','4','8','5','6'),
            charArrayOf('9','6','1','5','3','7','2','8','4'),
            charArrayOf('2','8','7','4','1','9','6','3','5'),
            charArrayOf('3','4','5','2','8','6','1','7','9')
        ), solution = true)
    )

    val s = Solution()

    tests.forEach { test ->
        println("test: $test")
        Solution.loops = 0
        val result = s.isValidSudoku(test.board.toTypedArray())
        println("$result loops:${Solution.loops}")
        if (result != test.solution) throw Exception("test case $test failed, result $result, expected ${test.solution}")
    }
}