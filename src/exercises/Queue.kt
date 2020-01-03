package exercises

@Suppress("unused")
class Queue<T> {
    inner class Node<T>(var ahead: Node<T>?, val value: T, var behind: Node<T>?)
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var _size: Int = 0

    fun enqueue(item: T) {
        tail?.let {
            it.behind = Node(tail, item, null)
            tail = it.behind
        } ?: run {
            tail = Node(null, item, null)
            head = tail
        }
        _size++
    }

    fun dequeue(): T? = head?.let {
        val value = it.value

        head = head?.behind
        head?.ahead = null

        if (head == null) tail = null

        _size--

        value
    }

    fun peek(): T? =  head?.value

    val size
        get() = _size

    fun toList(): List<T> {
        val result = mutableListOf<T>()

        var node = head
        while (node != null) {
            result.add(node.value)
            node = node.behind
        }

        return result
    }

    override fun toString(): String = toList().joinToString(" ,")
}