package arrays_and_hashing

import kotlin.math.max

fun main() {
    val array = MyArray(3)
    array.insert(10)
    array.insert(20)
    array.insert(30)
    array.print()
    array.insertAt(50, 2)
    array.print()
}

class MyArray(private val length: Int) {
    private var items: IntArray = IntArray(length)
    private var count = 0

    fun insert(item: Int) {
        if (count == items.size) {
            val newItems = IntArray(count * 2)
            for (i in 0..<count) {
                newItems[i] = items[i]
            }
            items = newItems
        }
        items[count++] = item
    }

    fun removeAt(index: Int) {
        if (index < 0 || index >= count) {
            throw IllegalArgumentException()
        }
        for (i in index..<count - 1) {
            items[i] = items[i + 1]
        }
        count--
    }

    fun indexOf(item: Int): Int {
        for (i in 0..<count) {
            if (item == items[i]) {
                return i
            }
        }
        return -1
    }

    fun maxElement(): Int {
        var maxItem = 0
        for (i in 0..<count) {
            maxItem = max(maxItem, items[i])
        }
        return maxItem
    }

    fun intersect(anotherArray: MyArray): MyArray {
        val intersection = MyArray(count - 1)
        for (item in items) {
            if (anotherArray.indexOf(item) >= 0) {
                intersection.insert(item)
            }
        }
        return intersection
    }

    fun reverse() {
        var i = 0
        var j = count - 1
        while (i <= j) {
            val temp = items[i]
            items[i] = items[j]
            items[j] = temp
            i++
            j--
        }
    }

    fun insertAt(item: Int, index: Int) {
        if (index < 0 || index >= count) {
            throw IllegalArgumentException()
        }
        if (count == items.size) {
            val newItems = IntArray(count * 2)
            for (i in 0..<count) {
                newItems[i] = items[i]
            }
            items = newItems
        }
        for (i in count downTo index + 1) {
            items[i] = items[i - 1]
        }
        items[index] = item
        count++
    }

    fun print() {
        println(items.sliceArray(0..<count).contentToString())
    }
}