package stack

import java.util.Stack

fun main() {
    println(isBalanced("(([1] + <2>))"))
}

private fun isBalanced(input: String): Boolean {
    val openingBrackets = arrayOf('(', '{', '<', '[')
    val closingBrackets = arrayOf(')', '}', '>', ']')
    val stack = Stack<Char>()
    for (ch in input) {
        if (openingBrackets.contains(ch)) {
            stack.push(ch)
        } else if (closingBrackets.contains(ch)) {
            if (stack.isEmpty()) {
                return false
            }
            val lastPoppedBracket = stack.pop()
            if (openingBrackets.indexOf(lastPoppedBracket) != closingBrackets.indexOf(ch)) {
                return false
            }
        }
    }
    return stack.isEmpty()
}