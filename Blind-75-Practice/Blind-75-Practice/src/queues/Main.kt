package queues

import java.util.Stack

fun main() {
    val queue = ArrayDeque<Int>()
    queue.add(10)
    queue.add(20)
    queue.add(30)
    queue.add(40)
    queue.add(50)
    println(queue)
    reverseFirstKElements(queue, 3)
    println(queue)
}

private fun reverse(queue: ArrayDeque<Int>) {
    val stack = Stack<Int>()
    while (queue.isNotEmpty()) {
        stack.push(queue.removeFirst())
    }
    while (stack.isNotEmpty()) {
        queue.add(stack.pop())
    }
}

private fun reverseFirstKElements(queue: ArrayDeque<Int>, k: Int) {
    val stack = Stack<Int>()
    val tempQueue = ArrayDeque<Int>()
    var count = 0
    while (count < k) {
        stack.push(queue.removeFirst())
        count++
    }
    while (queue.isNotEmpty()) {
        tempQueue.add(queue.removeFirst())
    }
    while (stack.isNotEmpty()) {
        queue.add(stack.pop())
    }
    while (tempQueue.isNotEmpty()) {
        queue.add(tempQueue.removeFirst())
    }
}