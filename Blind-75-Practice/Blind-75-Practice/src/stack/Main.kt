package stack

import java.util.Stack

fun main() {
    val stack = MinStack()
    stack.push(10)
    stack.push(2)
    stack.push(1)
    println(stack.min())
    stack.pop()
    stack.pop()
    println(stack.min())
}