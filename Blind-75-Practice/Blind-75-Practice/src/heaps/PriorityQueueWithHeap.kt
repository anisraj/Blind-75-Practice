package heaps

fun main() {
    val queue = PriorityQueueWithHeap()
    queue.enqueue(10)
    queue.enqueue(30)
    queue.enqueue(20)
    println("Removed: ${queue.dequeue()}")
    println("Removed: ${queue.dequeue()}")
    println("Removed: ${queue.dequeue()}")
}

class PriorityQueueWithHeap {
    private val heap = Heap(10)

    fun enqueue(item: Int) {
        heap.insert(item)
    }

    fun dequeue(): Int {
        return heap.remove()
    }

    fun isEmpty(): Boolean {
        return heap.isEmpty()
    }
}