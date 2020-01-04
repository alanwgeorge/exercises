package exercises.treelongestcycle

import exercises.readLine
import exercises.readLineInput
import java.io.File
import java.util.*

/*
* https://www.hackerearth.com/practice/data-structures/trees/binary-and-nary-trees/practice-problems/approximate/largest-cycle-in-a-tree-9113b3ab/
*/

class NaryTreeNode<T>(val value: T, val edges: MutableList<NaryTreeNode<T>> = mutableListOf()) {
    fun toListBfs(): List<T> {
        val values = mutableListOf<T>()
        val queue = LinkedList<NaryTreeNode<T>>()
        var currentNode: NaryTreeNode<T> = this
        var deepNode = this

        queue.addAll(currentNode.edges)

        while (queue.peekFirst() != null) {
            if (!values.contains(currentNode.value)) {
                values.add(currentNode.value)
                queue.addAll(currentNode.edges)
                deepNode = currentNode
            }

            currentNode = queue.removeFirst()
        }

//        println("deepNode: $deepNode")
        return values
    }

    override fun toString(): String {
        return "NaryTreeNode(value=$value, #edges=${edges.size})"
    }


}

fun main() {
//    readLineInput = File("src/exercises/treelongestcycle/test2.txt").inputStream()
    readLineInput = System.`in`

    val edgeCount = readLine()?.toInt() ?: throw Exception("error parsing node count")

    val nodes = mutableMapOf<Int, NaryTreeNode<Int>>()
    val root = NaryTreeNode(1)
    nodes[1] = root

    repeat(edgeCount - 1) { count ->
        val (a, b) = readLine()?.split("""\s+""".toRegex(), 2)?.map { it.toInt() } ?: throw Exception("error parsing edge $count")

        val aNode = nodes[a] ?: NaryTreeNode(a)
        val bNode = nodes[b] ?: NaryTreeNode(b)

        aNode.edges.add(bNode)
        bNode.edges.add(aNode)

        nodes[a] = aNode
        nodes[b] = bNode
    }

    val longCycleStart = root.toListBfs().last()
    val longCycleEnd = nodes[longCycleStart]?.toListBfs()?.last() ?: throw Exception("longCycleStart not found")
    println("$longCycleStart $longCycleEnd")
}
