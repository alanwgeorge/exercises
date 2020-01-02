package exercises.treecounting

import exercises.TreeNode

/*
*  https://www.hackerearth.com/practice/data-structures/trees/binary-and-nary-trees/practice-problems/algorithm/tree-counting-3/
*/

fun main() {
    val (nodeCount, minimumSum) = readLine()?.split("""\s+""".toRegex(), 2)?.map { it.toLong() } ?: throw Exception("error parsing nodeCount and K")
    val numbers = readLine()?.split("""\s+""".toRegex(), nodeCount.toInt())?.map { it.toLong() } ?: throw Exception("error parsing numbers")
    val parents = readLine()?.split("""\s+""".toRegex(), nodeCount.toInt())?.map { it.toInt() } ?: throw Exception("error parsing parents")

    val tree = mutableMapOf<Int, TreeNode<Pair<Int, Long>>>()
    val root = TreeNode(1 to numbers.first())
    tree[0] = root

    numbers.drop(1).zip(parents) { number, parent -> number to parent }
            .forEachIndexed { idx, numberparent ->
                val (number, parentIdx) = numberparent
                val node = TreeNode((idx + 2) to number)
                val parent = tree[parentIdx - 1] ?: throw Exception("could not find parent for $numberparent")

                when {
                    parent.left == null -> {
                        parent.left = node
                    }
                    parent.right == null -> {
                        parent.right = node
                    }
                    else -> {
                        throw Exception("parent node is already full")
                    }
                }

                tree[idx + 1] = node
            }

//    root.prettyPrint()
    println(root.countSubtrees(minimumSum))
}

fun TreeNode<Pair<Int, Long>>.countSubtrees(minimumSum: Long):Int {
    if (isLeafNode) return 0

    var count = 0

    val children = childrenInOrder()

    for (c1 in children.indices) {
        for (c2 in children.lastIndex downTo 0) {
            if (c1 == c2) {
                break
            } else {
               if ((value.second + children[c1].second + children[c2].second) >= minimumSum && children[c1].first != children[c2].first) count++
            }
        }
    }

    left?.let {
        count += it.countSubtrees(minimumSum)
    }

    right?.let {
        count += it.countSubtrees(minimumSum)
    }

    return count
}
