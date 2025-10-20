package two_pointers

import kotlin.math.max
import kotlin.math.min

fun main() {
    println(maxAreaBruteBetter(intArrayOf(1,8,6,2,5,4,8,3,7)))
}

// Time O(n), Space O(1)
fun maxAreaBruteBetter(height: IntArray): Int {
    var maxArea = Int.MIN_VALUE
    var i = 0
    var j = height.lastIndex
    while (i < j) {
        val length = j - i
        val minHeight = min(height[i], height[j])
        val area = minHeight * length
        maxArea = max(maxArea, area)
        if (height[i] <= height[j]) {
            i++
        } else {
            j--
        }
    }
    return maxArea
}

// Time O(n^2), Space O(1)
fun maxAreaBruteForce(height: IntArray): Int {
    var maxArea = Int.MIN_VALUE
    for (i in 0..height.lastIndex) {
        for (j in i + 1..height.lastIndex) {
            val length = j - i
            val minHeight = min(height[i], height[j])
            val area = minHeight * length
            maxArea = max(maxArea, area)
        }
    }
    return maxArea
}