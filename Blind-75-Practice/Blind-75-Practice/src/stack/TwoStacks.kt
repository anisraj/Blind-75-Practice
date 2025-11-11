package stack

class TwoStacks(private val capacity: Int) {
    init {
        if (capacity <= 0) {
            throw IllegalArgumentException("Capacity must be greater then 0.")
        }
    }
    val items = IntArray(capacity) { 0 }
    var top1 = 0
    var top2 = capacity - 1

    fun push1(item: Int) {
        if (isFull1()) {
            throw StackOverflowError()
        }
        items[top1++] = item
    }

    fun push2(item: Int) {
        if (isFull2()) {
            throw StackOverflowError()
        }
        items[top2--] = item
    }

    fun pop1(): Int {
        if (isEmpty1()) {
            throw IllegalStateException()
        }
        return items[--top1]
    }

    fun pop2(): Int {
        if (isEmpty2()) {
            throw IllegalStateException()
        }
        return items[top2++]
    }

    fun isEmpty1(): Boolean {
        return top1 == 0
    }

    fun isFull1(): Boolean {
        return top1 == top2
    }

    fun isEmpty2(): Boolean {
        return top2 == items.size
    }

    fun isFull2(): Boolean {
        return top2 - 1 == top1
    }

    override fun toString(): String {
        return items.contentToString()
    }
}