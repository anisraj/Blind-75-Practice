package queues

fun main() {
    val queue = LinkedListQueue()
    queue.enqueue(10)
    queue.enqueue(20)
    queue.enqueue(30)
    queue.enqueue(40)
    println(queue)
    println("removed: " + queue.dequeue())
    println("removed: " + queue.dequeue())
    println(queue)
}

class LinkedListQueue {
    private var first: Node? = null
    private var last: Node? = null
    var size = 0
        private set

    fun enqueue(item: Int) {
        val node = Node(item)
        last?.let {
            it.next = node
        } ?: run {
            first = node
        }
        last = node
        size++
    }

    fun dequeue(): Int {
        if (isEmpty()) {
            throw IllegalStateException()
        }
        if (first == last) {
            first?.let {
                val item = it.value
                first = null
                last = null
                size--
                return item
            } ?: {
                throw IllegalStateException("Value is null.")
            }
        }
        val head = first
        first = first?.next
        head?.next = null
        return head?.value.also { size-- } ?: throw IllegalStateException("Value is null.")
    }

    fun isEmpty(): Boolean {
        return size == 0
    }

    override fun toString(): String {
        val list = mutableListOf<Int>()
        var node = first
        while (node != null) {
            list.add(node.value)
            node = node.next
        }
        return list.toString()
    }

    private data class Node(
        val value: Int
    ) {
        var next: Node? = null
    }
}