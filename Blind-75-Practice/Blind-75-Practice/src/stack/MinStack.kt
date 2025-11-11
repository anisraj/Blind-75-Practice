package stack

import java.util.Stack

class MinStack {
    val stack = Stack<Int>()
    val minStack = Stack<Int>()

    fun push(item: Int) {
        stack.push(item)
        if (minStack.isEmpty()) {
            minStack.push(item)
        } else if (item < minStack.peek()) {
            minStack.push(item)
        }
    }

    fun pop(): Int {
        if (stack.isEmpty()) {
            throw IllegalStateException()
        }
        val top = stack.pop()
        if (top == minStack.peek()) {
            minStack.pop()
        }
        return top
    }

    fun min(): Int {
        return minStack.peek()
    }
}