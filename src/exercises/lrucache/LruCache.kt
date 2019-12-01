package exercises.lrucache

fun main() {
    with(LruCache<String>(size = 5)) {
        repeat(11) {
            put(it.toString(), it.toString())
        }

        put("5", "55")

        assert(get("1") == null)

        val state = getState()
        println(state)

        assert(state[0].value == "55")
        assert(state[4].value == "7")
    }
}

class LruCache<T>(val size: Int = 10) {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private val hashMap = mutableMapOf<String, Node<T>>()

    fun get(tag: String): T? {
        val node = hashMap.getOrDefault(tag, null) ?: return null

        moveToHead(node)

        return node.value
    }

    fun put(tag: String, value: T) {
        if (hashMap.isEmpty()) {
            val node = Node(null, tag, value, null)
            head = node
            tail = node
            hashMap[tag] = node
        } else if (hashMap[tag] != null) {
            hashMap[tag]?.let { moveToHead(it) }
            hashMap[tag]?.value = value
        } else {
            if (hashMap.size == size) purgeTail()
            val node = Node(null, tag, value, head)
            head?.previous = node
            head = node
            hashMap[tag] = node
        }
    }

    private fun moveToHead(node: Node<T>) {
        if (node != head) {
            if (node == tail) {
                tail = node.previous
                tail?.next = null
            }
            node.previous?.next = node.next
            node.previous = null
            node.next = head
            head = node
        }
    }

    private fun purgeTail() {
        tail?.let {
            hashMap.remove(it.tag)
            it.previous?.next = null
            tail = it.previous
        }
    }

    fun getState(): List<Node<T>> {
        var currentNode = head
        val list = mutableListOf<Node<T>>()
        while (currentNode != null) {
            list.add(currentNode.copy())
            currentNode = currentNode.next
        }

        return list
    }
}

data class Node<T>(var previous: Node<T>?, val tag: String, var value: T, var next: Node<T>?) {
    override fun toString(): String = "Node($tag, $value)"
}