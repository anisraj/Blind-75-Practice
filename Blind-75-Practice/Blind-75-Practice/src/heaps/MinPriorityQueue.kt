package heaps

fun main() {
    val minPriorityQueue = MinPriorityQueue(10)
    minPriorityQueue.enqueue("C", 30)
    minPriorityQueue.enqueue("B", 20)
    minPriorityQueue.enqueue("D", 40)
    minPriorityQueue.enqueue("A", 10)

    println("Removed: ${minPriorityQueue.dequeue()}")
    println("Removed: ${minPriorityQueue.dequeue()}")
    println("Removed: ${minPriorityQueue.dequeue()}")
    println("Removed: ${minPriorityQueue.dequeue()}")
}

class MinPriorityQueue(capacity: Int) {
    private val minHeap = MinHeap(capacity)

    fun enqueue(value: String, priority: Int) {
        minHeap.insert(priority, value)
    }

    fun dequeue(): String {
        return minHeap.remove().second
    }
}