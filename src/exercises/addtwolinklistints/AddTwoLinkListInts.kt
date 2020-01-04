package exercises.addtwolinklistints

/*
* https://leetcode.com/problems/add-two-numbers/
*/

data class ListNode(var `val`: Int, var next: ListNode? = null) {
    override fun toString(): String {
        val builder = StringBuilder("[")
        var node: ListNode? = this

        while (node != null) {
            builder.append(node.`val`)
            if (node.next != null) builder.append(", ")
            node = node.next
        }

        builder.append(']')
        return builder.toString()
    }
}

class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var one: ListNode? = l1
        var two: ListNode? = l2
        val falseHead = ListNode(0)
        var currentNode = falseHead
        var carry = 0

        while (one != null || two != null) {
            ((one?.`val` ?: 0) + (two?.`val` ?: 0) + carry).let { sum ->
                carry = sum / 10
                ListNode(sum % 10).apply {
                    currentNode.next = this
                    currentNode = this
                }
            }

            one = one?.next
            two = two?.next
        }

        if (carry > 0) currentNode.next = ListNode(carry)

        return falseHead.next
    }
}

@Suppress("DuplicatedCode")
fun main() {
    val s = Solution()
    val test1 = s.addTwoNumbers(Data.test1.first, Data.test1.second)
    println(test1)
    assert(test1 == Data.test1Solution)

    val test2 = s.addTwoNumbers(Data.test2.first, Data.test2.second)
    println(test2)
    assert(test2 == Data.test2Solution)

    val test3 = s.addTwoNumbers(Data.test3.first, Data.test3.second)
    println(test3)
    assert(test3 == Data.test3Solution)

    val test4 = s.addTwoNumbers(Data.test4.first, Data.test4.second)
    println(test4)
    assert(test4 == Data.test4Solution)
}

private fun assert(value: Boolean) {
    if (!value) {
        throw AssertionError("assert failed")
    }
}

@Suppress("DuplicatedCode")
object Data {
    val l1 = ListNode(2).apply {
        next = ListNode(4).apply {
            next = ListNode(3)
        }
    }
    val l2 = ListNode(5).apply {
        next = ListNode(6).apply {
            next = ListNode(4)
        }
    }
    val l3 = ListNode(1).apply {
        next = ListNode(0).apply {
            next = ListNode(0).apply {
                next = ListNode(0).apply {
                    next = ListNode(0).apply {
                        next = ListNode(0).apply {
                            next = ListNode(0).apply {
                                next = ListNode(0).apply {
                                    next = ListNode(0).apply {
                                        next = ListNode(0).apply {
                                            next = ListNode(0).apply {
                                                next = ListNode(0).apply {
                                                    next = ListNode(0).apply {
                                                        next = ListNode(0).apply {
                                                            next = ListNode(0).apply {
                                                                next = ListNode(0).apply {
                                                                    next = ListNode(0).apply {
                                                                        next = ListNode(0).apply {
                                                                            next = ListNode(0).apply {
                                                                                next = ListNode(0).apply {
                                                                                    next = ListNode(0).apply {
                                                                                        next = ListNode(0).apply {
                                                                                            next = ListNode(0).apply {
                                                                                                next = ListNode(0).apply {
                                                                                                    next = ListNode(0).apply {
                                                                                                        next = ListNode(0).apply {
                                                                                                            next = ListNode(0).apply {
                                                                                                                next = ListNode(0).apply {
                                                                                                                    next = ListNode(0).apply {
                                                                                                                        next = ListNode(0).apply {
                                                                                                                            next = ListNode(0).apply {
                                                                                                                                next = ListNode(1)
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    val l4 = ListNode(5).apply {
        next = ListNode(6).apply {
            next = ListNode(4)
        }
    }
    val l5 = ListNode(5)
    val l6 = ListNode(9).apply {
        next = ListNode(8)
    }
    val l7 = ListNode(1)

    // [2,4,3]
    // [5,6,4]
    val test1 = l1 to l2
    // [7,0,8]
    val test1Solution = ListNode(7).apply {
        next = ListNode(0).apply {
            next = ListNode(8)
        }
    }

    //[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
    //[5,6,4]
    val test2 = l3 to l4
    //[6,6,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
    val test2Solution = ListNode(6).apply {
        next = ListNode(6).apply {
            next = ListNode(4).apply {
                next = ListNode(0).apply {
                    next = ListNode(0).apply {
                        next = ListNode(0).apply {
                            next = ListNode(0).apply {
                                next = ListNode(0).apply {
                                    next = ListNode(0).apply {
                                        next = ListNode(0).apply {
                                            next = ListNode(0).apply {
                                                next = ListNode(0).apply {
                                                    next = ListNode(0).apply {
                                                        next = ListNode(0).apply {
                                                            next = ListNode(0).apply {
                                                                next = ListNode(0).apply {
                                                                    next = ListNode(0).apply {
                                                                        next = ListNode(0).apply {
                                                                            next = ListNode(0).apply {
                                                                                next = ListNode(0).apply {
                                                                                    next = ListNode(0).apply {
                                                                                        next = ListNode(0).apply {
                                                                                            next = ListNode(0).apply {
                                                                                                next = ListNode(0).apply {
                                                                                                    next = ListNode(0).apply {
                                                                                                        next = ListNode(0).apply {
                                                                                                            next = ListNode(0).apply {
                                                                                                                next = ListNode(0).apply {
                                                                                                                    next = ListNode(0).apply {
                                                                                                                        next = ListNode(0).apply {
                                                                                                                            next = ListNode(0).apply {
                                                                                                                                next = ListNode(1)
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //[5]
    //[5]
    val test3 = l5 to l5
    //[0,1]
    val test3Solution = ListNode(0).apply {
        next = ListNode(1)
    }

    //[9,8]
    //[1]
    val test4 = l6 to l7
    //[0,9]
    val test4Solution = ListNode(0).apply {
        next = ListNode(9)
    }
}