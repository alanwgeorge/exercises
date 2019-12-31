package exercises

@Suppress("MemberVisibilityCanBePrivate")
class Stack<T> {
    inner class Node<T>(val value: T, val next: Node<T>?) {
        override fun toString() = "Node(value=$value, next=$next)"
    }

    var top: Node<T>? = null

    fun push(value: T) {
        top = Node(value, top)
    }

    fun pop(): T? =
            if (top == null) {
                null
            } else {
                val value = top?.value
                top = top?.next
                value
            }

    fun size():Int {
        var node = top
        var count = 0
        while (node != null) {
            count++
            node = node.next
        }
        return count
    }

    fun toList(): List<T> {
        var node = top
        val list = mutableListOf<T>()
        while (node != null) {
            list.add(node.value)
            node = node.next
        }
        return list
    }

    override fun toString() = "Stack(top=$top)"
}