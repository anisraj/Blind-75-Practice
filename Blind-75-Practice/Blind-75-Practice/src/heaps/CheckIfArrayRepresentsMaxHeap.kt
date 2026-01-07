package heaps

fun main() {
    val array = intArrayOf(8,4,5,3,1,2)
    val c = CheckIfArrayRepresentsMaxHeap()
    println(c.isMaxHeap(array))
}

class CheckIfArrayRepresentsMaxHeap {
    fun isMaxHeap(array: IntArray): Boolean {
        for (i in array.indices) {
            if (!isValidParent(array, i)) {
                return false
            }
        }
        return true
    }

    private fun isValidParent(array: IntArray, index: Int): Boolean {
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

    private fun getLeftChildIndex(index: Int): Int {
        return (index * 2) + 1
    }

    private fun getRightChildIndex(index: Int): Int {
        return (index * 2) + 2
    }
}

