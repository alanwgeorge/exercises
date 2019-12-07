package exercises

fun <T : Comparable<T>> MutableList<T>.bubbleSort(): MutableList<T> {
    var numOfMoves = -1
    while (numOfMoves != 0) {
        numOfMoves = 0
        for (i in 0 until lastIndex) {
            if (this[i] > this[i + 1]) {
                numOfMoves++
                swap(i, i + 1)
            }
        }
    }
    return this
}

fun <T : Comparable<T>> MutableList<T>.selectionSort(): MutableList<T> {
    var minPos = 0
    for (i in indices) {
        for (j in (i + 1)..lastIndex) {
            if (this[j] < this[minPos]) minPos = j
        }
        swap(i, minPos)
    }

    return this
}

fun <T : Comparable<T>> MutableList<T>.insertionSort(): MutableList<T> {
    for (i in indices) {
        val tmp = this[i]
        var j = i

        while (j > 0 && tmp < this[j - 1]) {
            this[j] = this[j - 1]
            j--
        }

        this[j] = tmp
    }

    return this
}

fun <T : Comparable<T>> MutableList<T>.mergeSort(): MutableList<T> {
    fun merge(source: MutableList<T>, l: Int, mid: Int, r: Int) {
        val left = mutableListOf<T>()
        val right = mutableListOf<T>()

        for (i in l..mid) {
            left.add(source[i])
        }
        for (j in (mid + 1)..r) {
            right.add(source[j])
        }

        var i = 0; var j = 0; var k = l
        while (i <= left.lastIndex && j <= right.lastIndex) {
            if (left[i] < right[j]) {
                source[k++] = left[i++]
            } else {
                source[k++] = right[j++]
            }
        }

        while (i <= left.lastIndex) {
            source[k++] = left[i++]
        }

        while (j <= right.lastIndex) {
            source[k++] = right[j++]
        }
    }

    fun sort(source: MutableList<T>, l: Int, r:Int) {
        if (l < r) {
            val mid = (l + r) / 2
            sort(source, l, mid)
            sort(source, mid + 1, r)
            merge(source, l, mid, r)
        }
    }

    sort(this, 0, this.lastIndex)

    return this
}

fun <T> MutableList<T>.swap(x: Int, y: Int): MutableList<T> =
    if (x > lastIndex || y > lastIndex || x == y) {
        this
    } else {
        val tmp = this[x]
        this[x] = this[y]
        this[y] = tmp
        this
    }


fun main() {
    println(mutableListOf(7,4,0,1,9,2,5,3,6,8).mergeSort())
}