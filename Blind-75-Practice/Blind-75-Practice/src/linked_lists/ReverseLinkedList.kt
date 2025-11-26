package linked_lists

fun main() {

}

private fun reverseList(head: ListNode?): ListNode? {
    var prev = head
    var current = head?.next
    prev?.next = null
    while (current != null) {
        val next = current.next

        current.next = prev

        prev = current
        current = next
    }
    return prev
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}