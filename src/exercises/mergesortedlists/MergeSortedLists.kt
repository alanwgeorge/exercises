package exercises.mergesortedlists

/*
*  https://leetcode.com/problems/merge-two-sorted-lists/
*/

data class ListNode(val `val`: Int, var next: ListNode? = null)

class Solution {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        when {
            l1 == null -> return l2
            l2 == null -> return l1
        }

        val root = ListNode(0)
        var nextSpot: ListNode = root

        var ptr1: ListNode? = l1
        var ptr2: ListNode? = l2

        while (ptr1 != null && ptr2 != null) {
            if (ptr1.`val` < ptr2.`val`) {
                nextSpot.next = ptr1
                nextSpot = ptr1
                ptr1 = ptr1.next
            } else {
                nextSpot.next = ptr2
                nextSpot = ptr2
                ptr2 = ptr2.next
            }
        }

        if (ptr1 != null) {
            nextSpot.next = ptr1
        }

        if (ptr2 != null) {
            nextSpot.next = ptr2
        }

        return root.next
    }
}

data class Test(val l1: ListNode?, val l2: ListNode?, val solution: ListNode?)

fun main() {
    val tests = listOf(
            Test(
                    ListNode(1, ListNode(2, ListNode(4))),
                    ListNode(1, ListNode(3, ListNode(4))),
                    ListNode(1, ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(4))))))
            ),
            Test(
                    ListNode(1),
                    ListNode(1, ListNode(3, ListNode(4))),
                    ListNode(1, ListNode(1, ListNode(3, ListNode(4))))
            ),
            Test(
                    null,
                    ListNode(1, ListNode(3, ListNode(4))),
                    ListNode(1, ListNode(3, ListNode(4)))
            ),
            Test(null, null, null)
    )

    val s = Solution()

    tests.forEach {
        println("test: $it")
        val result = s.mergeTwoLists(it.l1, it.l2)
        println(result)
        if (result != it.solution) throw Exception("test $it failed, result $result, expected ${it.solution}")
    }
}