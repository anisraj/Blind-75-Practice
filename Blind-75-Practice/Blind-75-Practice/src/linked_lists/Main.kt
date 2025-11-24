package linked_lists

import java.util.LinkedList

fun main() {
    val list = MyLinkedList.createWithLoop()
    println(list.hasLoop())
}