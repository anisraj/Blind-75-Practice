package linked_lists

fun main() {

}

private fun hasCycleBetter(head: ListNode?): Boolean {
    var slowPointer = head
    var fastPointer = head
    while (slowPointer != null && fastPointer != null && fastPointer.next != null) {
        slowPointer = slowPointer.next
        fastPointer = fastPointer.next?.next
        if (slowPointer == fastPointer) {
            return true
        }
    }
    return false
}

private fun hasCycleBruteForce(head: ListNode?): Boolean {
    val set = mutableSetOf<ListNode>()
    var node = head
    while (node != null) {
        if (set.contains(node)) {
            return true
        }
        set.add(node)
        node = node.next
    }
    return false
}