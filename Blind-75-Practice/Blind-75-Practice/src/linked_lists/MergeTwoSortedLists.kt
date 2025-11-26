package linked_lists

fun main() {

}

private fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val resultNode = ListNode(0)
    var dummy: ListNode? = resultNode
    var node1 = list1
    var node2 = list2
    while (node1 != null && node2 != null) {
        if (node1.`val` <= node2.`val`) {
            dummy?.next = ListNode(node1.`val`)
            node1 = node1.next
        } else {
            dummy?.next = ListNode(node2.`val`)
            node2 = node2.next
        }
        dummy = dummy?.next
    }
    while (node1 != null) {
        dummy?.next = ListNode(node1.`val`)
        node1 = node1.next
        dummy = dummy?.next
    }
    while (node2 != null) {
        dummy?.next = ListNode(node2.`val`)
        node2 = node2.next
        dummy = dummy?.next
    }
    return resultNode.next
}
