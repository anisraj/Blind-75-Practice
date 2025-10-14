package arrays_and_hashing/*
package arrays_and_hashing

import kotlinx.coroutines.*
import kotlin.math.max
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val input = intArrayOf(6,2,5,4,3,7)
        sort(input, 3)
        println(input.contentToString())
    }

}

private suspend fun sort(array: IntArray, numberOfBuckets: Int) {
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

    coroutineScope {
        buckets.forEach { bucket ->
            bucket?.let {
                launch(Dispatchers.Default) {
                    mergeSort(bucket, 0, bucket.size - 1)
                }
            }
        }
    }

    var i = 0
    for (bucket in buckets) {
        bucket?.let {
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
}*/
