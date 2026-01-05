package heaps

fun main() {
    val numbers = intArrayOf(5,3,10,1,4,2)
    val heap = Heap(numbers.size)
    for (number in numbers) {
        heap.insert(number)
    }
    for (i in numbers.lastIndex downTo 0) {
        numbers[i] = heap.remove()
    }
    println(numbers.contentToString())

}