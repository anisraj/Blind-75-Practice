package linked_lists

import java.util.PriorityQueue

private fun mergeKListsOptimal(lists: Array<ListNode?>): ListNode? {
    val priorityQueue = PriorityQueue(ListNodeComparator())
    for (head in lists) {
        head?.let {
            priorityQueue.add(head)
        }
    }
    val resultNode = ListNode(-1)
    var dummy: ListNode? = resultNode
    while (priorityQueue.isNotEmpty()) {
        val node = priorityQueue.poll()
        if (node?.next != null) {
            priorityQueue.add(node.next)
        }
        dummy?.next = node
        dummy = dummy?.next
    }
    return resultNode.next
}

class ListNodeComparator : Comparator<ListNode> {
    override fun compare(o1: ListNode, o2: ListNode): Int {
        return o1.`val` - o2.`val`
    }
}

private fun mergeKListsBetter(lists: Array<ListNode?>): ListNode? {
    var resultNode = lists.firstOrNull()
    for (i in 1..<lists.size) {
        resultNode = mergeTwoLists(resultNode, lists[i])
    }
    return resultNode
}

private fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val resultNode = ListNode(0)
    var dummy: ListNode? = resultNode
    var node1 = list1
    var node2 = list2
    while (node1 != null && node2 != null) {
        if (node1.`val` <= node2.`val`) {
            dummy?.next = node1
            node1 = node1.next
        } else {
            dummy?.next = node2
            node2 = node2.next
        }
        dummy = dummy?.next
    }
    while (node1 != null) {
        dummy?.next = node1
        node1 = node1.next
        dummy = dummy?.next
    }
    while (node2 != null) {
        dummy?.next = node2
        node2 = node2.next
        dummy = dummy?.next
    }
    return resultNode.next
}


private fun mergeKListsBruteForce(lists: Array<ListNode?>): ListNode? {
    val list = mutableListOf<Int>()
    for (i in lists.indices) {
        var tempHead = lists[i]
        while (tempHead != null) {
            list.add(tempHead.`val`)
            tempHead = tempHead.next
        }
    }
    list.sort()
    val resultNode = ListNode(0)
    var dummy: ListNode? = resultNode
    for (item in list) {
        dummy?.next = ListNode(item)
        dummy = dummy?.next
    }
    return resultNode.next
}