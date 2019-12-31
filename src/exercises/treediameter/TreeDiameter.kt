package exercises.treediameter

import exercises.TreeNode

/*
* https://www.hackerearth.com/practice/data-structures/trees/binary-and-nary-trees/tutorial/
*/

fun main() {
    val (nodeCount, rootValue) = readLine()?.split("""\s+""".toRegex(), 2)?.map { it.toInt() } ?: throw Exception("error parsing nodeCount and rootValue")

    val root = TreeNode(rootValue)

    val paths = mutableListOf<Pair<List<Char>, Int>>()

    repeat(nodeCount - 1) { _ ->
        val path = readLine()?.toList()?.filter { it == 'L' || it == 'R' } ?: throw Exception("error parsing path")
        val value = readLine()?.toInt() ?: throw Exception("error parsing node value")
        paths.add(path to value)
    }

    for (p in paths.sortedBy { it.first.size }) {
        val (path, value) = p

        val newNode = TreeNode(value)
        var location = root

        path.dropLast(1).forEach { c ->
            location = when (c) {
                'L' -> location.left ?: throw Exception("error in path location left is null")
                'R' -> location.right ?: throw Exception("error in path location right is null")
                else -> throw Exception("path value != L or R")
            }
        }

        when (path[path.lastIndex]) {
            'L' -> location.left = newNode
            'R' -> location.right = newNode
        }
    }
//    root.prettyPrint()
    println(root.diameter())
}