package exercises

import kotlin.math.max

@Suppress("unused", "MemberVisibilityCanBePrivate")
class TreeNode<T>(
        val value: T,
        var left: TreeNode<T>? = null,
        var right: TreeNode<T>? = null){

    companion object {
        private var diameter = 0
    }

    override fun toString(): String {
        return "Node(value=$value, $value-left=$left, $value-right=$right)"
    }

    val isLeafNode: Boolean
        get() = left == null && right == null

    fun childCount(): Int = childCountRecur(this)

    private fun childCountRecur(treeNode: TreeNode<T>): Int {
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

    private fun maxDepthRecur(treeNode: TreeNode<T>, accum: Int): Int {
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
        diameterRecur(this)
        return diameter
    }

    private fun diameterRecur(treeNode: TreeNode<T>): Int {
        with(treeNode) {
            if (isLeafNode) return 0

            val l = left?.let {
                diameterRecur(it) + 1
            } ?: 0

            val r = right?.let {
                diameterRecur(it) + 1
            } ?: 0

            if (l + r + 1 > diameter) {
                diameter = l + r + 1
            }

//            println("${node.value} $l $r $diameter")

            return max(l, r)
        }
    }

    fun children(): Stack<T> =
        Stack<T>().apply {
            left?.let {
                childrenRecur(it, this)
            }
            right?.let {
                childrenRecur(it, this)
            }
        }

    private fun childrenRecur(node: TreeNode<T>, stack: Stack<T>): Stack<T> {
        with(node) {
            stack.push(value)

            left?.let {
                childrenRecur(it, stack)
            }

            right?.let {
                childrenRecur(it, stack)
            }

            return stack
        }
    }

    fun prettyPrint() = printTreeRecur(this, 0)

    private fun <T> printTreeRecur(treeNode: TreeNode<T>?, indent: Int) {
        if (treeNode == null) return
        val newIndent = indent + 4

        printTreeRecur(treeNode.left, newIndent)
        print(" ".repeat(newIndent))
        println("${treeNode.value}")
        printTreeRecur(treeNode.right, newIndent)
    }
}
