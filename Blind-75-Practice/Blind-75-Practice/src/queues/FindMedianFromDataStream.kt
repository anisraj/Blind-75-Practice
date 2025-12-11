package queues

import java.util.PriorityQueue

fun main() {
    val medianFinder = MedianFinderOptimal()
    medianFinder.addNum(1)
    medianFinder.addNum(2)
    println(medianFinder.findMedian())
    medianFinder.addNum(3)
    println(medianFinder.findMedian())
}

class MedianFinderOptimal {
    private val maxQ = PriorityQueue<Int>(compareByDescending { it })
    private val minQ = PriorityQueue<Int>()

    fun addNum(num: Int) {
        if (minQ.isNotEmpty() && num > minQ.peek()) {
            minQ.add(num)
        } else {
            maxQ.add(num)
        }

        //rebalance
        if (maxQ.size > minQ.size + 1) {
            minQ.add(maxQ.poll())
        }
        if (minQ.size > maxQ.size + 1) {
            maxQ.add(minQ.poll())
        }
    }

    fun findMedian(): Double {
        return when {
            maxQ.size > minQ.size -> maxQ.peek().toDouble()
            minQ.size > maxQ.size -> minQ.peek().toDouble()
            else -> (minQ.peek() + maxQ.peek()) / 2.0
        }
    }
}

class MedianFinderBruteForce() {
    private var list: MutableList<Int> = mutableListOf()

    fun addNum(num: Int) {
        list.add(num)
    }

    fun findMedian(): Double {
        list.sort()
        val median = (list.size + 1) / 2
        if (list.size % 2 == 0) {
            return (list[median - 1].toDouble() + list[median].toDouble()) / 2
        }
        return (list[median - 1].toDouble())
    }

}