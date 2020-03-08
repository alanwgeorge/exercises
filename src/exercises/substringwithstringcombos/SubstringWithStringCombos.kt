@file:Suppress("unused")

package exercises.substringwithstringcombos

/*
*  https://leetcode.com/problems/substring-with-concatenation-of-all-words/
*/

class Solution(private val debug: Boolean = false) {
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        if (words.isEmpty()) return emptyList()

        val result = mutableListOf<Int>()
        val wordIndxs = hashMapOf<String, List<Int>>()

        words.forEach {
            wordIndxs[it] = s.indexesOf(it)
        }

        if (debug) println("words start indexes $wordIndxs")

        val startingList = wordIndxs.values.toMutableList()

        var startIndexes = startingList[0]

        for (idexs in startingList.drop(1)) {
            startIndexes = startIndexes.merge(idexs)
        }

        if (debug) println("merged start idxes $startIndexes")

        val numWords = words.size
        val wordLength = words[0].length
        val totalLengthOfMatch = numWords * wordLength
        val indexDiff = (numWords - 1) * wordLength

        for (start in startIndexes.indices) {
            val end = start + numWords - 1
            if (end > startIndexes.lastIndex) break
            if (debug) println("$start $end ${startIndexes[start]} ${startIndexes[end]} $indexDiff ${startIndexes[end] - startIndexes[start]}")
            if (startIndexes[end] - startIndexes[start] == indexDiff) {
                val candidateSubString = s.subSequence(startIndexes[start], startIndexes[start] + totalLengthOfMatch)
                var matchCount = 0

                words.groupingBy { it }.eachCount().forEach {
                    if (candidateSubString.indexesOf(it.key).size == it.value) {
                        matchCount += it.value
                    }
                }

                if (matchCount == words.size) result.add(startIndexes[start])
            }
        }

        return result
    }
}

class Solution2(private val debug: Boolean = false) {
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val result = mutableListOf<Int>()
        val wordLength = words.getOrNull(0)?.length ?: 0
        if (s.isEmpty() || words.isEmpty() || wordLength == 0 || wordLength > s.length) return emptyList()

        val wordCounts = words.groupingBy { it }.eachCount()

        s.windowed(wordLength * words.size).forEachIndexed { index, fullLengthCandidate ->
            var matchCount = 0
            val candidate = fullLengthCandidate.windowed(wordLength, wordLength)
            wordCounts.forEach { (word, count) ->
                if (candidate.count { it == word } == count) matchCount += count
            }

            if (matchCount == words.size) result.add(index)
        }

        return result
    }
}

fun CharSequence.indexesOf(subString: String): List<Int> {
    val result = mutableListOf<Int>()
    var sPtr: Int

    for (i in this.indices) {
        sPtr = i
        var ssPtr = 0
        while (ssPtr < subString.length && sPtr < length) {
            if (subString[ssPtr] == get(sPtr)) {
                if (ssPtr == subString.lastIndex) result.add(sPtr - subString.lastIndex)
                ssPtr++
                sPtr++
            } else {
                break
            }
        }
    }

    return result
}

fun List<Int>.merge(other: List<Int>): List<Int> {
    val result = mutableListOf<Int>()

    var i = 0
    var j = 0

    while (i < size && j < other.size) {
        if (get(i) < other[j]) {
            result.add(get(i))
            i++
        } else {
            result.add(other[j])
            j++
        }
    }

    while (i < size) {
        result.add(get(i))
        i++
    }

    while (j < other.size) {
        result.add(other[j])
        j++
    }

    return result
}

data class Test(val s: String, val words: List<String>, val solution: List<Int>)

fun main() {
    val tests = listOf(
            Test("barfoothefoobarman", listOf("foo", "bar"), listOf(0, 9)),
            Test("barfoothefoobarbazman", listOf("baz","foo", "bar"), listOf(9)),
            Test("barbazfoo", listOf("baz","foo", "bar"), listOf(0)),
            Test("barbazfoo", listOf(), listOf()),
            Test("wordgoodgoodgoodbestword", listOf("word","good","best","word"), listOf()),
            Test("wordgoodgoodgoodbestword", listOf("word","good","good","good","best","word"), listOf(0)),
            Test("wordgoodgoodgoodbestword", listOf("word","good","best","good"), listOf(8)),
            Test("aaa", listOf("aa","aa"), listOf()),
            Test("aaaaaaaa", listOf("aa","aa","aa"), listOf(0,1,2))
    )

    val s = Solution2(debug = true)

    tests.forEach {
        println("test $it")
        val result = s.findSubstring(it.s, it.words.toTypedArray())
        println("result: $result")
        if (result.sorted() != it.solution.sorted()) throw Exception("test $it failed, result $result, expected ${it.solution}")
    }
}