package linked_lists

private fun reorderListBruteForce(head: ListNode?): Unit {
    val list = mutableListOf<ListNode>()
    fillList(head, list)
    if (list.isNotEmpty()) {
        var node = list.firstOrNull()
        var iAdded = true
        var i = 1
        var j = list.lastIndex
        while (i <= j) {
            if (iAdded) {
                node?.next = list[j]
                j--
            } else {
                node?.next = list[i]
                i++
            }
            node = node?.next
            iAdded = !iAdded
        }
        node?.next = null
    }
}

private fun fillList(head: ListNode?, list: MutableList<ListNode>) {
    var node = head
    while (node != null) {
        list.add(node)
        node = node.next
    }
}
