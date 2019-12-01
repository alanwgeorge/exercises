package exercises

fun <T : Comparable<T>> List<T>.searchBinary(term: T): Int {
    var start = 0
    var end = lastIndex

    while (start <= end) {
        val mid = (start + end) / 2
        when {
            get(mid) > term -> end = mid - 1
            get(mid) < term -> start = mid + 1
            else -> return mid + 1
        }
    }

    return -1
}