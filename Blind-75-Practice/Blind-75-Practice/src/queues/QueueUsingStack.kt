package queues

import java.util.Stack

fun main() {
    val queue = QueueUsingStack()
    queue.enqueue(10)
    queue.enqueue(20)
}

class QueueUsingStack {
    private val stack1 = Stack<Int>()
    private val stack2 = Stack<Int>()

    fun enqueue(item: Int) {
        stack1.push(item)
    }

    fun dequeue(): Int {
        if (isEmpty()) {
            throw IllegalStateException()
        }
        if (stack2.isEmpty()) {
            while (stack1.isNotEmpty()) {
                stack2.push(stack1.pop())
            }
        }
        return stack2.pop()
    }

    fun isEmpty(): Boolean {
        return stack1.isEmpty() && stack2.isEmpty()
    }
}