package heaps

fun main() {
    val numbers = intArrayOf(5,3,8,4,1,2)
    heapify(numbers)
    println(numbers.contentToString())
}

private fun heapify(array: IntArray) {
    for (i in array.indices) {
        if (!isValidParent(i, array)) {
            val maxIndex = getMaxChildIndex(i, array)
            //swap
            val temp = array[i]
            array[i] = array[maxIndex]
            array[maxIndex] = temp
        }
    }
}

private fun isValidParent(index: Int, array: IntArray): Boolean {
    val leftChildIndex = getLeftChildIndex(index)
    val rightChildIndex = getRightChildIndex(index)

    if (leftChildIndex >= array.size) {
        return true
    }
    if (rightChildIndex >= array.size) {
        return array[index] >= array[leftChildIndex]
    }

    return array[index] >= array[leftChildIndex] &&
            array[index] >= array[rightChildIndex]
}

private fun getMaxChildIndex(index: Int, array: IntArray): Int {
    val leftChildIndex = getLeftChildIndex(index)
    val rightChildIndex = getRightChildIndex(index)

    if (leftChildIndex >= array.size) {
        return index
    }
    if (rightChildIndex >= array.size) {
        return leftChildIndex
    }
    return if (array[leftChildIndex] > array[rightChildIndex]) {
        leftChildIndex
    } else {
        rightChildIndex
    }
}

private fun getLeftChildIndex(index: Int): Int {
    return (index * 2) + 1
}

private fun getRightChildIndex(index: Int): Int {
    return (index * 2) + 2
}