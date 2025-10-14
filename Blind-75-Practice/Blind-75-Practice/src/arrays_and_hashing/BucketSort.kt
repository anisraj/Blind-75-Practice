package arrays_and_hashing

import kotlin.math.max
import kotlin.system.measureTimeMillis

fun main() {
    val input = intArrayOf(6,2,5,4,3,7)
    sort(input, 3)
    println(input.contentToString())
}

private fun sort(array: IntArray, numberOfBuckets: Int) {
    var maxElement = array.first()
    for (item in array) {
        maxElement = max(maxElement, item)
    }
    maxElement++

    val buckets = Array<MutableList<Int>?>(numberOfBuckets) { null }
    for (item in array) {
        val index = (item * numberOfBuckets) / maxElement
        val list = buckets[index] ?: mutableListOf()
        buckets[index] = list.apply { add(item) }
    }

    var i = 0
    for (bucket in buckets) {
        bucket?.let {
            mergeSort(bucket, 0, bucket.size - 1)
            for (item in it) {
                array[i++] = item
            }
        }
    }
}

private fun mergeSort(array: MutableList<Int>, low: Int, high: Int) {
    if (low >= high) {
        return
    }
    val midIndex = (low + high) / 2
    mergeSort(array, low, midIndex)
    mergeSort(array, midIndex + 1, high)
    //merge
    val temp = mutableListOf<Int>()
    var leftPointer = low
    var rightPointer = midIndex + 1
    while (leftPointer <= midIndex && rightPointer <= high) {
        if (array[leftPointer] <= array[rightPointer]) {
            temp.add(array[leftPointer++])
        } else {
            temp.add(array[rightPointer++])
        }
    }
    while (leftPointer <= midIndex) {
        temp.add(array[leftPointer++])
    }
    while (rightPointer <= high) {
        temp.add(array[rightPointer++])
    }
    for (i in low..high) {
        array[i] = temp[i - low]
    }
}