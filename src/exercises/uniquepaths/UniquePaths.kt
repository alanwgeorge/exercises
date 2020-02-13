package exercises.uniquepaths

import exercises.BinaryTreeNode

/*
* https://leetcode.com/problems/unique-paths-ii/
*/

class Solution {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid[0][0] == 1 || obstacleGrid.last().last() == 1) return 0

        val pathLength = obstacleGrid.size + obstacleGrid[0].size - 1

        val map: MutableList<MutableList<BinaryTreeNode<Triple<Int, Int, Int>>?>> = MutableList(obstacleGrid.size) {
            MutableList(obstacleGrid[0].size) {
                null as BinaryTreeNode<Triple<Int, Int, Int>>?
            }
        }

        for (x in obstacleGrid.lastIndex downTo 0) {
            for (y in obstacleGrid[x].lastIndex downTo 0) {
                println("$x $y ${obstacleGrid[x][y]}")
                if (obstacleGrid[x][y] == 1) continue
                val newNode = BinaryTreeNode(Triple(obstacleGrid[x][y], x, y))
                map[x][y] = newNode

                if (x < obstacleGrid.lastIndex) {
                    map[x + 1][y]?.let {
                        if (it.value.first == 0) newNode.right = it
                    }
                }

                if (y < obstacleGrid[0].lastIndex) {
                    map[x][y + 1]?.let {
                        if (it.value.first == 0) newNode.left = it
                    }
                }
            }
        }

        map[0][0]?.let {
            println("${it.maxDepth()} ${obstacleGrid.size + obstacleGrid[0].size - 1}")
        }

        val maxDepth = map[0][0]?.maxDepth() ?: 0

        return if (pathLength > maxDepth) {
            0
        } else {
            map[0][0]?.countLeafs() ?: 0
        }
    }
}

class Solution2 {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid[0][0] == 1 || obstacleGrid.last().last() == 1) return 0
        val map: MutableList<MutableList<Int>> = MutableList(obstacleGrid.size) { c ->
            MutableList(obstacleGrid[0].size) { r ->
                if (c == 0 && r == 0) 1 else 0
            }
        }

        for (r in 1..obstacleGrid[0].lastIndex) {
            if (obstacleGrid[0][r] == 0) map[0][r] = map[0][r - 1]
        }
        for (c in 1..obstacleGrid.lastIndex) {
            if (obstacleGrid[c][0] == 0) map[c][0] = map[c - 1][0]
        }

        for (r in 1..obstacleGrid.lastIndex) {
            for (c in 1..obstacleGrid[0].lastIndex) {
                if (obstacleGrid[r][c] == 0) map[r][c] = map[r - 1][c] + map[r][c - 1]
            }
        }

        return map[obstacleGrid.lastIndex][obstacleGrid[0].lastIndex]
    }

    private fun <T> List<List<T>>.print() {
        map { l ->
            println(l.map { it.toString().first() })
        }
    }

}
fun main() {
    val tests = listOf(
            listOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0)) to 2,
            listOf(intArrayOf(1)) to 0,
            listOf(intArrayOf(0,1), intArrayOf(1,0)) to 0,
            listOf(intArrayOf(0,0,0,0,0), intArrayOf(0,0,0,0,1), intArrayOf(0,0,0,1,0), intArrayOf(0,0,0,0,0)) to 10,
            listOf(intArrayOf(0,0), intArrayOf(1,1), intArrayOf(0,0)) to 0
    )

    val s = Solution2()

    tests.forEach {
        println("test: $it")
        val result = s.uniquePathsWithObstacles(it.first.toTypedArray())
        println("result:$result")
        if (result != it.second) throw Exception("test $it failed, result $result, expected ${it.second}")
    }
}