package stack

import java.util.Stack

fun main() {
    println(reverseString("abcd"))
}

private fun reverseString(input: String): String {
    val stack = Stack<Char>()
    for (ch in input) {
        stack.push(ch)
    }
    val stringBuilder = StringBuilder()
    while (!stack.empty()) {
        stringBuilder.append(stack.pop())
    }
    return stringBuilder.toString()
}