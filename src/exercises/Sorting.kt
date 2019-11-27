package exercises

fun <T : Comparable<T>> MutableList<T>.bubbleSort(): MutableList<T> {
    var numOfMoves = -1
    while (numOfMoves != 0) {
        numOfMoves = 0
        for (i in 0 until lastIndex) {
            if (get(i) > get(i + 1)) {
                numOfMoves++
                val tmp = get(i)
                set(i, get(i + 1))
                set(i + 1, tmp)
            }
        }
    }
    return this
}

fun main() {
    println(mutableListOf(7,4,0,1,9,2,5,3,6,8).bubbleSort())
}