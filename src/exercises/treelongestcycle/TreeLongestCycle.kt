package exercises.treelongestcycle

import exercises.Tree
import exercises.TreeNode

/*
* https://www.hackerearth.com/practice/data-structures/trees/binary-and-nary-trees/practice-problems/approximate/largest-cycle-in-a-tree-9113b3ab/
*/

fun main() {
    val edgeCount = readLine()?.toInt() ?: throw Exception("error parsing node count")

    val nodes = mutableMapOf<Int, TreeNode<Int>>()
    var nextNodeKey = 1

    repeat(edgeCount - 1) { count ->
        val (a, b) = readLine()?.split("""\s+""".toRegex(), 2)?.map { it.toInt() } ?: throw Exception("error parsing edge $count")

        val parent = if (count == 0) {
            TreeNode(a).also {
                nodes[nextNodeKey++] = it
            }
        } else {
            nodes[a]
        } ?: throw Exception("can't find parent for $a $b")

        val child = TreeNode(b).also {
            nodes[nextNodeKey++] = it
        }

        when {
            parent.left == null -> {
                parent.left = child
            }
            parent.right == null -> {
                parent.right = child
            }
            else -> {
                throw Exception("parent node is already full")
            }
        }
    }

    nodes[1]?.let { root ->
//        it.prettyPrint()
        val tree = Tree(root)
        val diameterNode = tree.findDiameterNode()
        val diameterL = diameterNode.left?.let { left ->
            Tree(left).findDeepestNode().value
        } ?: root.value
        val diameterR = diameterNode.right?.let { right ->
            Tree(right).findDeepestNode().value
        } ?: root.value

        println("$diameterL $diameterR")
    }
}