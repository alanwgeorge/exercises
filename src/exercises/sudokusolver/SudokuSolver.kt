@file:Suppress("DuplicatedCode")

package exercises.sudokusolver

class Solution {
    fun solveSudoku(board: Array<CharArray>) {
        val openCells = mutableListOf<OpenCell>()
        var cellsUpdated = 0

        board.forEachIndexed { x, chars ->
            chars.forEachIndexed { y, c ->
                if (c == '.') {
                    possibilities(x, y, board).also {
                        if (it.size == 1) {
                            println("cell found $x $y  $it")
                            board[x][y] = it.first().toString().first()
                            cellsUpdated++
                        } else {
//                            println("cell has multiple possibles $x $y $it")
                            openCells.add(OpenCell(x, y, it))
                        }
                    }
                }
            }
        }

        if (cellsUpdated > 0) {
            solveSudoku(board)
        } else if (openCells.isNotEmpty()){
            openCells.sortBy { it.possibles.size  }
            openCells.forEach {
                println("open cell = $it")
            }

            openCells.forEach { (x, y, ps) ->
                if (ps.isEmpty()) {
                    println("impossible cell, dead end")
                    return
                }

                ps.forEach { p ->
                    println("try possible $x $y $p")
                    board[x][y] = p.toString().first()
                    board.print()
                    solveSudoku(board)
                }
            }
        }
    }

    data class OpenCell(val x: Int, val y: Int, val possibles: List<Int>)

    private fun possibilities(x: Int, y: Int, board: Array<CharArray>): List<Int> {
        val rowValues = board[x].map { char ->
             Character.getNumericValue(char).let {
                 if  (it == -1) null else it
             }
        }.filterNotNull()

        val columnValues = board.mapNotNull { row ->
            Character.getNumericValue(row[y]).let {
                if (it == -1) null else it
            }
        }

        val squareValues = squareRangeForIndex(x).map { squareX ->
            squareRangeForIndex(y).map { squareY ->
                Character.getNumericValue(board[squareX][squareY]).let {
                    if (it == -1) null else it
                }
            }
        }.flatten().filterNotNull()

        val values = (rowValues + columnValues + squareValues).distinct()

        val result = (1..9).filter {
            values.contains(it).not()
        }

        return result
    }

    fun squareRangeForIndex(index: Int) = when(index) {
        0,1,2 -> 0..2
        3,4,5 -> 3..5
        6,7,8 -> 6..8
        else -> throw Exception("value out of range")
    }
}

data class Test(val start: List<CharArray>, val solution: List<CharArray>)

fun main() {
    val tests = listOf(
        Test(
            start = listOf(
                charArrayOf('5','3','.','.','7','.','.','.','.'),
                charArrayOf('6','.','.','1','9','5','.','.','.'),
                charArrayOf('.','9','8','.','.','.','.','6','.'),
                charArrayOf('8','.','.','.','6','.','.','.','3'),
                charArrayOf('4','.','.','8','.','3','.','.','1'),
                charArrayOf('7','.','.','.','2','.','.','.','6'),
                charArrayOf('.','6','.','.','.','.','2','8','.'),
                charArrayOf('.','.','.','4','1','9','.','.','5'),
                charArrayOf('.','.','.','.','8','.','.','7','9')
            ),
            solution = listOf(
                charArrayOf('5','3','4','6','7','8','9','1','2'),
                charArrayOf('6','7','2','1','9','5','3','4','8'),
                charArrayOf('1','9','8','3','4','2','5','6','7'),
                charArrayOf('8','5','9','7','6','1','4','2','3'),
                charArrayOf('4','2','6','8','5','3','7','9','1'),
                charArrayOf('7','1','3','9','2','4','8','5','6'),
                charArrayOf('9','6','1','5','3','7','2','8','4'),
                charArrayOf('2','8','7','4','1','9','6','3','5'),
                charArrayOf('3','4','5','2','8','6','1','7','9')
            )
        ),
        Test(
            start = listOf(
                charArrayOf('.','.','9','7','4','8','.','.','.'),
                charArrayOf('7','.','.','.','.','.','.','.','.'),
                charArrayOf('.','2','.','1','.','9','.','.','.'),
                charArrayOf('.','.','7','.','.','.','2','4','.'),
                charArrayOf('.','6','4','.','1','.','5','9','.'),
                charArrayOf('.','9','8','.','.','.','3','.','.'),
                charArrayOf('.','.','.','8','.','3','.','2','.'),
                charArrayOf('.','.','.','.','.','.','.','.','6'),
                charArrayOf('.','.','.','2','7','5','9','.','.')
            ),
            solution = listOf(
                charArrayOf('5','1','9','7','4','8','6','3','2'),
                charArrayOf('7','8','3','6','5','2','4','1','9'),
                charArrayOf('4','2','6','1','3','9','8','7','5'),
                charArrayOf('3','5','7','9','8','6','2','4','1'),
                charArrayOf('2','6','4','3','1','7','5','9','8'),
                charArrayOf('1','9','8','5','2','4','3','6','7'),
                charArrayOf('9','7','5','8','6','3','1','2','4'),
                charArrayOf('8','3','2','4','9','1','7','5','6'),
                charArrayOf('6','4','1','2','7','5','9','8','3')
            )
        )
    )

    val s = Solution()

    tests.forEach { test ->
        println("test = $test")
        val startArray = test.start.toTypedArray()
        s.solveSudoku(startArray)
        startArray.print()
        if (startArray.isSame(test.solution.toTypedArray()).not()) {
            println("test case $test failed")
            println("result")
            startArray.print()
            println("expected")
            test.solution.toTypedArray().print()

            throw Exception("test case $test failed")
        }
    }
}


fun Array<CharArray>.print() {
    forEachIndexed { idx, row ->
        println("$idx ${row.toList()}")
    }
}

fun Array<CharArray>.isSame(other: Array<CharArray>): Boolean {
    if (size != other.size) return false

    forEachIndexed { rowIndex, chars ->
        if (chars.size != other[rowIndex].size) return false

        chars.forEachIndexed { charIndex, c ->
            if (c != other[rowIndex][charIndex]) return false
        }
    }

    return true
}
