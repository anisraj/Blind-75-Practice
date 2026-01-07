package heaps

fun main() {
    val minHeap = MinHeap(10)
    minHeap.insert(20, "A")
    minHeap.insert(10, "B")
    minHeap.insert(30, "C")
    minHeap.insert(40, "D")

    println("Removed: ${minHeap.remove()}")
    println("Removed: ${minHeap.remove()}")
    println("Removed: ${minHeap.remove()}")
    println("Removed: ${minHeap.remove()}")

    println()

}

class MinHeap(capacity: Int) {
    private val items = Array<Node?>(capacity) { null }
    private var count = 0

    fun insert(key: Int, value: String) {
        if (isFull()) {
            throw IllegalStateException("Heap is full!")
        }
        val node = Node(key, value)
        items[count] = node
        bubbleUp()
        count++
    }

    private fun bubbleUp() {
        var itemIndex = count
        var parentIndex = getParentIndex(itemIndex)
        while (
            itemIndex > 0 &&
            items[itemIndex]!!.key < items[parentIndex]!!.key
        ) {
            val temp = items[itemIndex]
            items[itemIndex] = items[parentIndex]
            items[parentIndex] = temp

            itemIndex = parentIndex
            parentIndex = getParentIndex(itemIndex)
        }
    }
    fun remove(): Pair<Int, String> {
        if (isEmpty()) {
            throw IllegalStateException("Heap is empty!")
        }
        val node = items.first()

        items[0] = items[count - 1]
        items[count - 1] = null
        bubbleDown()
        count--
        return Pair(node!!.key, node.value)
    }

    private fun bubbleDown() {
        var index = 0
        while (
            index < count - 1 &&
            !isValidParent(index)
        ) {
            val smallerChildIndex = getSmallerChildIndex(index)

            val temp = items[index]
            items[index] = items[smallerChildIndex]
            items[smallerChildIndex] = temp

            index = smallerChildIndex
        }
    }

    private fun isValidParent(index: Int): Boolean {
        val leftChildIndex = getLeftChildIndex(index)
        val rightChildIndex = getRightChildIndex(index)
        if (leftChildIndex >= count - 1) {
            return true
        }
        if (rightChildIndex >= count - 1) {
            return items[index]!!.key < items[leftChildIndex]!!.key
        }
        return items[index]!!.key < items[leftChildIndex]!!.key &&
                items[index]!!.key < items[rightChildIndex]!!.key
    }

    private fun getSmallerChildIndex(index: Int): Int {
        val leftChildIndex = getLeftChildIndex(index)
        val rightChildIndex = getRightChildIndex(index)
        if (leftChildIndex >= count - 1) {
            return index
        }
        if (rightChildIndex >= count - 1) {
            return leftChildIndex
        }
        return if (items[leftChildIndex]!!.key < items[rightChildIndex]!!.key) {
            leftChildIndex
        } else {
            rightChildIndex
        }
    }

    private fun getParentIndex(index: Int): Int {
        return (index - 1) / 2
    }

    private fun getLeftChildIndex(index: Int): Int {
        return (index * 2) + 1
    }

    private fun getRightChildIndex(index: Int): Int {
        return (index * 2) + 2
    }

    fun isFull(): Boolean {
        return count == items.size
    }

    fun isEmpty(): Boolean {
        return count == 0
    }

    private data class Node(
        val key: Int,
        val value: String
    )
}