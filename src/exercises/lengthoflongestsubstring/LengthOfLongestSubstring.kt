package exercises.lengthoflongestsubstring

class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var charCollect = mutableListOf<Char>()
        var maxLength = 0
        var maxString = ""

        s.forEach { char ->
            val stringIdx = String(charCollect.toCharArray()).indexOf(char)
            if (stringIdx == -1)  {
                charCollect.add(char)
                if (charCollect.size > maxLength) {
                    maxLength = charCollect.size
                    maxString = String(charCollect.toCharArray())
                }
            } else  {
                charCollect = charCollect.drop(stringIdx + 1).toMutableList()
                charCollect.add(char)
            }

            println("$char $charCollect $maxString $maxLength")
        }

//        println(maxString)
        return maxLength
    }
}

fun main() {
    val s = Solution()

    println(s.lengthOfLongestSubstring("pwwkew"))
}