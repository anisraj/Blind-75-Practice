package heaps

fun main() {
    val heap = Heap(10)
    heap.insert(20)
    heap.insert(10)
    heap.insert(15)
    heap.insert(4)
    heap.insert(5)
    println("Removed: ${heap.remove()}")
    println("Removed: ${heap.remove()}")
    println("Removed: ${heap.remove()}")
    println("Removed: ${heap.remove()}")
    println("Removed: ${heap.remove()}")
    println(heap)
}

class Heap(capacity: Int) {
    private val items: IntArray = IntArray(capacity)
    private var count = 0

    fun insert(item: Int) {
        if (isFull()) {
            throw IllegalStateException()
        }

        items[count] = item
        bubbleUp()
        count++
    }

    fun remove(): Int {
        if (isEmpty()) {
            throw IllegalStateException("Heap is empty!")
        }

        val itemToRemove = items.first()
        items[0] = items[count - 1]
        items[count - 1] = 0
        bubbleDown()
        count--
        return itemToRemove
    }

    private fun bubbleDown() {
        var itemIndex = 0
        while (
            itemIndex < count - 1 &&
            !isValidParent(itemIndex)
        ) {
            val maxIndex = largerChildIndex(itemIndex)

            val temp = items[itemIndex]
            items[itemIndex] = items[maxIndex]
            items[maxIndex] = temp

            itemIndex = maxIndex
        }
    }

    private fun isValidParent(index: Int): Boolean {
        if (!hasLeftChild(index)) {
            return true
        }
        if (!hasRightChild(index)) {
            return items[index] >= items[getLeftChildIndex(index)]
        }
        return items[index] >= items[getLeftChildIndex(index)] &&
                items[index] >= items[getRightChildIndex(index)]
    }

    private fun largerChildIndex(index: Int): Int {
        if (!hasLeftChild(index)) {
            return index
        }
        if (!hasRightChild(index)) {
            return getLeftChildIndex(index)
        }
        return if (items[getLeftChildIndex(index)] > items[getRightChildIndex(index)]) {
            getLeftChildIndex(index)
        } else {
            getRightChildIndex(index)
        }
    }

    private fun bubbleUp() {
        var itemIndex = count
        var parentIndex = getParentIndex(itemIndex)
        while (itemIndex > 0 && items[itemIndex] > items[parentIndex]) {
            val temp = items[itemIndex]
            items[itemIndex] = items[parentIndex]
            items[parentIndex] = temp

            itemIndex = parentIndex
            parentIndex = getParentIndex(itemIndex)
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

    private fun hasLeftChild(index: Int): Boolean {
        return getLeftChildIndex(index) < count
    }

    private fun hasRightChild(index: Int): Boolean {
        return getRightChildIndex(index) < count
    }

    fun isFull(): Boolean {
        return count == items.size
    }

    fun isEmpty(): Boolean {
        return count == 0
    }

    override fun toString(): String {
        return items.sliceArray(0..<count).contentToString()
    }
}