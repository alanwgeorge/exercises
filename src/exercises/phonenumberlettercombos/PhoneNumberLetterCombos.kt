@file:Suppress("LocalVariableName")

package exercises.phonenumberlettercombos

/*
* https://leetcode.com/problems/letter-combinations-of-a-phone-number/
*/

@Suppress("SpellCheckingInspection")
val phone = mapOf(
        2 to "abc".toList(),
        3 to "def".toList(),
        4 to "ghi".toList(),
        5 to "jkl".toList(),
        6 to "mno".toList(),
        7 to "pqrs".toList(),
        8 to "tuv".toList(),
        9 to "wxyz".toList()
)

class Solution {
    companion object {
        var loops = 0
    }

    fun letterCombinations(digits: String): List<String> {
        val _digits = digits.map { it.toString().toInt() }
        val result = mutableListOf<String>()

        val root = Nary("")

        var lastNodes = mutableListOf<Nary<String>>().apply {
            add(root)
        }

        for (d in _digits) {
            loops++
            phone[d]?.let {
                val newNodes = mutableListOf<Nary<String>>()
                it.forEach { c ->
                    loops++
                    lastNodes.forEach { node ->
                        loops++
                        val value = node.value + c
                        if (value.length == _digits.size) result.add(value)
                        with(Nary(value)) {
                            parent = node
                            node.children.add(this)
                            newNodes.add(this)
                        }
                    }
                }
                lastNodes = newNodes
            }
        }

        return result
    }

    class Nary<T>(val value: T) {
        var parent: Nary<T>? = null
        val children = mutableListOf<Nary<T>>()

        override fun toString(): String {
            return "Nary(value=$value, parent=${parent?.value}, children=$children)"
        }
    }
}

class Solution2 {
    companion object {
        var loops = 0
    }

    private val result = mutableListOf<String>()

    fun letterCombinations(digits: String): List<String> {
        result.clear()
        if (digits.isNotEmpty()) {
            backtrack("", digits)
        }

        return result
    }

    private fun backtrack(accum: String, digits: String) {
        loops++
        if (digits.isEmpty()) {
            result.add(accum)
        } else {
            val digit = digits.first().toString().toInt()
            phone[digit]?.forEach { c ->
                backtrack(accum + c, digits.drop(1))
            }
        }
    }
}

fun main() {
    @Suppress("SpellCheckingInspection")
    val tests = listOf(
            "23" to listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"),
            "4327" to listOf("gdap", "hdap", "idap", "geap", "heap", "ieap", "gfap", "hfap", "ifap", "gdbp", "hdbp", "idbp", "gebp", "hebp", "iebp", "gfbp", "hfbp", "ifbp", "gdcp", "hdcp", "idcp", "gecp", "hecp", "iecp", "gfcp", "hfcp", "ifcp", "gdaq", "hdaq", "idaq", "geaq", "heaq", "ieaq", "gfaq", "hfaq", "ifaq", "gdbq", "hdbq", "idbq", "gebq", "hebq", "iebq", "gfbq", "hfbq", "ifbq", "gdcq", "hdcq", "idcq", "gecq", "hecq", "iecq", "gfcq", "hfcq", "ifcq", "gdar", "hdar", "idar", "gear", "hear", "iear", "gfar", "hfar", "ifar", "gdbr", "hdbr", "idbr", "gebr", "hebr", "iebr", "gfbr", "hfbr", "ifbr", "gdcr", "hdcr", "idcr", "gecr", "hecr", "iecr", "gfcr", "hfcr", "ifcr", "gdas", "hdas", "idas", "geas", "heas", "ieas", "gfas", "hfas", "ifas", "gdbs", "hdbs", "idbs", "gebs", "hebs", "iebs", "gfbs", "hfbs", "ifbs", "gdcs", "hdcs", "idcs", "gecs", "hecs", "iecs", "gfcs", "hfcs", "ifcs"),
            "9" to listOf("w","x","y","z")
    )

    val s = Solution2()

    tests.forEach {
        println("test:$it")
        Solution2.loops = 0
        val result = s.letterCombinations(it.first)
        println("result:$result loops:${Solution2.loops}")
        if (result.sorted() != it.second.sorted()) throw Exception("test $it failed, result $result, expected ${it.second}")
    }
}