package queues

fun main() {
    val queue = StackWithTwoQueues()
    queue.push(10)
    queue.push(20)
    queue.push(30)
    queue.push(40)
    println(queue)
    println("removed: " + queue.pop())
    println("removed: " + queue.pop())
    println(queue)
}

class StackWithTwoQueues {
    private var queue1 = ArrayDeque<Int>()

    fun push(item: Int) {
        queue1.add(item)
    }

    fun pop(): Int {
        if (queue1.isEmpty()) {
            throw IllegalStateException()
        }
        val queue2 = ArrayDeque<Int>()
        while (queue1.size > 1) {
            queue2.add(queue1.removeFirst())
        }
        val resultItem = queue1.removeFirst()
        swapQueues(queue2)
        return resultItem
    }

    private fun swapQueues(queue2: ArrayDeque<Int>) {
        queue1 = queue2
    }

    override fun toString(): String {
        return queue1.toString()
    }
}