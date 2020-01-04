package exercises

import kotlin.math.max

@Suppress("unused", "MemberVisibilityCanBePrivate")
class BinaryTreeNode<T>(
        val value: T,
        var left: BinaryTreeNode<T>? = null,
        var right: BinaryTreeNode<T>? = null){

    override fun toString(): String {
        return "Node(value=$value, $value-left=$left, $value-right=$right)"
    }

    val isLeafNode: Boolean
        get() = left == null && right == null

    fun childCount(): Int = childCountRecur(this)

    private fun childCountRecur(treeNode: BinaryTreeNode<T>): Int {
        with(treeNode) {
            if (isLeafNode) return 0

            var count = 0
            left?.let {
                count += childCountRecur(it) + 1
            }

            right?.let {
                count += childCountRecur(it) + 1
            }

            return count
        }
    }

    fun maxDepth(): Int = maxDepthRecur(this, 0)

    private fun maxDepthRecur(treeNode: BinaryTreeNode<T>, accum: Int): Int {
        with(treeNode) {
            if (isLeafNode) return accum + 1

            val l = left?.let {
                maxDepthRecur(it, accum + 1)
            } ?: accum

            val r = right?.let {
                maxDepthRecur(it, accum + 1)
            } ?: accum

            return max(l, r)
        }
    }

    fun diameter(): Int {
        val maxDepthL = left?.maxDepth() ?: 0
        val maxDepthR = right?.maxDepth() ?: 0
        val diameterL = left?.diameter() ?: 0
        val diameterR = right?.diameter() ?: 0

        return max((maxDepthL + maxDepthR + 1), max(diameterL, diameterR))
    }

    fun childrenInOrder(): List<T> =
        mutableListOf<T>().apply {
            left?.let {
                childrenRecur(it, this)
            }
            right?.let {
                childrenRecur(it, this)
            }
        }

    private fun childrenRecur(node: BinaryTreeNode<T>, list: MutableList<T>): List<T> {
        with(node) {
            left?.let {
                childrenRecur(it, list)
            }

            list.add(value)

            right?.let {
                childrenRecur(it, list)
            }
            return list
        }
    }

    fun childrenLevelOrder(): List<T> {
        val list = mutableListOf<T>()
        for (level in 1..maxDepth()) {
            list += childrenLevelOrderRecur(this, level)
        }
        return list.drop(1)
    }

    fun childrenLevelOrderRecur(node: BinaryTreeNode<T>, level: Int): List<T> {
        with(node) {
//            println("level:$level value:${node.value}")
            val list = mutableListOf<T>()

            if (level == 1) {
                list.add(value)
            } else {
                left?.let {
                    list += childrenLevelOrderRecur(it, level - 1)
                }

                right?.let {
                    list += childrenLevelOrderRecur(it, level - 1)
                }
            }

//            println(list)
            return list
        }
    }

    fun dfs(): List<T> {
        val stack = Stack<BinaryTreeNode<T>>()
        val valuesInOder = mutableListOf<T>()

        var currentNode: BinaryTreeNode<T>? = this
        while (currentNode != null) {
            valuesInOder.add(currentNode.value)

            currentNode.right?.let {
                stack.push(it)
            }

            currentNode.left?.let {
                stack.push(it)
            }

            currentNode = stack.pop()
        }

        return valuesInOder
    }

    fun bfs(): List<T> {
        val queue = Queue<BinaryTreeNode<T>>()
        val valuesInOrder = mutableListOf<T>()

        var currentNode: BinaryTreeNode<T>? = this
        while (currentNode != null) {
            valuesInOrder.add(currentNode.value)

            currentNode.left?.let {
                queue.enqueue(it)
            }

            currentNode.right?.let {
                queue.enqueue(it)
            }

            currentNode = queue.dequeue()
        }

        return valuesInOrder
    }

    fun prettyPrint() = printTreeRecur(this, 0)

    private fun <T> printTreeRecur(treeNode: BinaryTreeNode<T>?, indent: Int) {
        if (treeNode == null) return
        val newIndent = indent + 4

        printTreeRecur(treeNode.right, newIndent)
        print(" ".repeat(newIndent))
        println("${treeNode.value}")
//        if (treeNode.left != null || treeNode.right != null) println("-<") else println("")
        printTreeRecur(treeNode.left, newIndent)
    }
}

class BinaryTree<T>(val root: BinaryTreeNode<T>) {

    private var diameterNode: BinaryTreeNode<T>? = null
    fun findDiameterNode(): BinaryTreeNode<T> {
        findDiameterNodeRecur(root)
        return diameterNode ?: root
    }

    private fun findDiameterNodeRecur(node: BinaryTreeNode<T>) {
        with(root) {
            val maxDepthL = left?.maxDepth() ?: 0
            val maxDepthR = right?.maxDepth() ?: 0
            val diameterL = left?.diameter() ?: 0
            val diameterR = right?.diameter() ?: 0

            val potentialDiameter = maxDepthL + maxDepthR + 1

            diameterNode = when {
                diameterL > potentialDiameter -> left
                diameterR > potentialDiameter -> right
                else -> node
            }
        }
    }

    private var deepestLevel = 1
    private var deepestNode: BinaryTreeNode<T>? = null
    fun findDeepestNode(): BinaryTreeNode<T> {
        findDeepestNodeRecur(root, 1)
        return deepestNode ?: root
    }

    private fun findDeepestNodeRecur(node: BinaryTreeNode<T>, level: Int) {
        with(node) {
            left?.let {
                findDeepestNodeRecur(it, level + 1)
            }

            if (level > deepestLevel) {
                deepestLevel = level
                deepestNode = node
            }

            right?.let {
                findDeepestNodeRecur(it, level + 1)
            }
        }
    }
}

