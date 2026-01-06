package heaps

fun main() {
    val input = intArrayOf(5,3,8,4,1,2)
    println(getKthLargestItem(6, input))
}

private fun getKthLargestItem(k: Int, input: IntArray): Int {
    if (k < 1 || k > input.size) {
        throw IllegalArgumentException()
    }

    val heap = Heap(input.size)
    for (i in input.indices) {
        heap.insert(input[i])
    }
    for (i in 1..<k) {
        heap.remove()
    }
    return heap.remove()
}