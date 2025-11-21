package linked_lists

class MyLinkedList {
    private var first: Node? = null
    private var last: Node? = null
    var size = 0
        private set

    fun addFirst(item: Int) {
        val node = Node(item)
        first?.let {
            node.next = first
        } ?: run {
            last = node
        }
        first = node

        size++
    }

    fun addLast(item: Int) {
        val node = Node(item)
        last?.let {
            it.next = node
        } ?: run {
            first = node
        }
        last = node

        size++
    }

    fun indexOf(item: Int): Int {
        var index = 0
        var node = first
        while (node != null) {
            if (node.value == item) {
                return index
            }
            node = node.next
            index++
        }
        return -1
    }

    fun contains(item: Int): Boolean {
        return indexOf(item) != -1
    }

    fun removeFirst() {
        if (isEmpty()) {
            throw NoSuchElementException()
        }
        if (first == last) {
            first = null
            last = null
        } else {
            val second = first?.next
            first?.next = null
            first = second
        }

        size--
    }

    fun removeLast() {
        if (isEmpty()) {
            throw NoSuchElementException()
        }
        if (first == last) {
            first = null
            last = null
        } else {
            var node = first
            while (node?.next != last) {
                node = node?.next
            }
            node?.next = null
            last = node
        }

        size--
    }

    fun toArray(): IntArray {
        val array = IntArray(size)
        var index = 0
        var node = first
        while (node != null) {
            array[index++] = node.value
            node = node.next
        }
        return array
    }

    fun reverse() {
        var prev = first
        var current = first?.next
        while (current != null) {
            val next = current.next
            current.next = prev
            prev = current
            current = next
        }

        last = first
        last?.next = null
        first = prev
    }

    fun getKthFromEnd(k: Int): Int {
        var firstPointer = first
        var secondPointer = first
        for (i in 0..<k - 1) {
            secondPointer = secondPointer?.next
            if (secondPointer == null) {
                throw IllegalArgumentException()
            }
        }
        while (secondPointer != last) {
            firstPointer = firstPointer?.next
            secondPointer = secondPointer?.next
        }
        return firstPointer?.value ?: throw IllegalStateException()
    }

    fun isEmpty(): Boolean {
        return first == null
    }

    private data class Node(
        val value: Int
    ) {
        var next: Node? = null
    }
}