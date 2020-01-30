package exercises.mergeksortedlists

/*
* https://leetcode.com/problems/merge-k-sorted-lists/
*/

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        return "ListNode(`val`=$`val`, next=$next)"
    }
}

class Solution {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val result = null

        return result
    }
}


fun main() {
    val tests = listOf(
            listOf(listOf(1,4,5), listOf(1,3,4), listOf(2,6)) to listOf(1,1,2,3,4,4,5,6)
    )

    val s = Solution()

    tests.forEach {
        println("test: $it")
        val result = s.mergeKLists(it.first.toArrayListNode())
        println(result)
        if (result != it.second.toListNode()) throw Exception("test $it failed, result $result, expected ${it.second.toListNode()}")
    }
}

fun List<Int>.toListNode(): ListNode? {
    if (isEmpty()) return null
    val head = ListNode(first())
    var ptr = head

    for (n in this.drop(1)) {
        ListNode(n).apply {
            ptr.next = this
            ptr = this
        }
    }

    return head
}

fun List<List<Int>>.toArrayListNode(): Array<ListNode?> {
    if (this.isEmpty()) return arrayOf(null)
    val result = mutableListOf<ListNode>()

    forEach {
        it.toListNode()?.let { list ->
            result.add(list)
        }
    }

    return result.toTypedArray()
}
