package linked_lists

import java.util.LinkedList

fun main() {
    val list = MyLinkedList()
    list.addFirst(5)
    println(list.getKthFromEnd(1))
}