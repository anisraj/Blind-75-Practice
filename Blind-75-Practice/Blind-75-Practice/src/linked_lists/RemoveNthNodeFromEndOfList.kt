package linked_lists

fun main() {

}

private fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val dummy = ListNode(0)
    dummy.next = head
    var left: ListNode? = dummy
    var right = head
    for (i in 1..n) {
        right = right?.next
    }

    while (right != null) {
        left = left?.next
        right = right.next
    }

    left?.next = left?.next?.next
    return dummy.next
}