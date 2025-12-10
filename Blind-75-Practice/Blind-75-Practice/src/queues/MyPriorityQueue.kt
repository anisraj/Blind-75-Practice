package queues

fun main() {
    val queue = MyPriorityQueue()
    queue.insert(10)
    queue.insert(7)
    queue.insert(11)
    println(queue)
    println("removed: " + queue.remove())
    println(queue)
}

class MyPriorityQueue {
    private val items = IntArray(5) { 0 }
    private var count = 0

    fun insert(item: Int) {
        if (count == items.size) {
            throw IllegalStateException()
        }
        //shift items
        var i = count - 1
        while (i >= 0) {
            if (items[i] > item) {
                items[i + 1] = items[i]
            } else {
                break
            }
            i--
        }
        items[i + 1] = item
        count++
    }

    fun remove(): Int {
        if (isEmpty()) {
            throw IllegalStateException()
        }
        return items[--count]
    }

    fun isEmpty(): Boolean {
        return count == 0
    }

    override fun toString(): String {
        return items.sliceArray(0..<count).contentToString()
    }
}