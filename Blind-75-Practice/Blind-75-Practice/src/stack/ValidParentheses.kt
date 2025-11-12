package stack

import java.util.Stack

fun main() {
    println(isValid("]"))
}

private fun isValid(s: String): Boolean {
    val openingBrackets = arrayOf('(', '[', '{')
    val closingBrackets = arrayOf(')', ']', '}')
    val stack = Stack<Char>()
    for (ch in s) {
        if (openingBrackets.contains(ch)) {
            stack.push(ch)
        } else {
            if (stack.isEmpty()) {
                return false
            }
            val top = stack.pop()
            if (openingBrackets.indexOf(top) != closingBrackets.indexOf(ch)) {
                return false
            }
        }
    }
    return stack.isEmpty()
}