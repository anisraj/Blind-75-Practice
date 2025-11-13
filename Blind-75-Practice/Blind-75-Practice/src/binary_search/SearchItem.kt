package binary_search

fun main() {
    val array = intArrayOf(3,4,6,7,9,12,16,17)
    println(searchItemRecursive(array, 16, 0, array.size - 1))
}

private fun searchItemRecursive(array: IntArray, target: Int, low: Int, high: Int): Int {
    if (low > high) {
        return -1
    }
    val mid = (low + high) / 2
    if (array[mid] == target) {
        return mid
    } else if (target > array[mid]) {
        return searchItemRecursive(array, target, mid + 1, high)
    } else {
        return searchItemRecursive(array, target, low, mid - 1)
    }
}

private fun searchItem(array: IntArray, target: Int): Int {
    var low = 0
    var high = array.size - 1
    while (low <= high) {
        val mid = (low + high) / 2
        if (array[mid] == target) {
            return mid
        } else if (target > array[mid]) {
            low = mid + 1
        } else {
            high = mid - 1
        }
    }
    return -1
}