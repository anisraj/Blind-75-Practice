package stack

class MyStack {
    val items = IntArray(5) { 0 }
    var count = 0

    fun push(item: Int) {
        if (count == items.size) {
            throw StackOverflowError()
        }
        items[count++] = item
    }

    fun pop(): Int {
        if (isEmpty()) {
            throw IllegalStateException("Stack is empty.")
        }
        return items[--count]
    }

    fun peek(): Int {
        if (isEmpty()) {
            throw IllegalStateException("Stack is empty.")
        }
        return items[count - 1]
    }

    fun isEmpty(): Boolean {
        return count == 0
    }

    override fun toString(): String {
        return items.sliceArray(0..<count).contentToString()
    }
}