package queues

class ArrayQueue constructor(capacity: Int) {
    private val array = IntArray(capacity) { 0 }
    private var front = 0
    private var rear = 0
    private var count = 0

    fun enqueue(item: Int) {
        if (isFull()) {
            throw IllegalStateException()
        }
        array[rear] = item
        rear = (rear + 1) % array.size
        count++
    }

    fun dequeue(): Int {
        if (isEmpty()) {
            throw IllegalStateException()
        }
        val item = array[front]
        array[front] = 0
        front = (front + 1) % array.size
        count--
        return item
    }

    fun isEmpty(): Boolean {
        return count == 0
    }

    fun isFull(): Boolean {
        return count == array.size
    }

    override fun toString(): String {
        return array.contentToString()
    }
}