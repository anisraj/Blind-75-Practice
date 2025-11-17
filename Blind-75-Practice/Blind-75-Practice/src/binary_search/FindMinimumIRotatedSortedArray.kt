package binary_search

import kotlin.math.min

fun main() {
    val input = intArrayOf(2,1)
    println(findMinBetter(input))
}

// Time O(n log n), Space O(1)
fun findMinBetter(nums: IntArray): Int {
    var low = 0
    var high = nums.size - 1
    var result = Int.MAX_VALUE
    while (low <= high) {
        val mid = (low + high) / 2
        if (nums[mid] >= nums[low]) {
            result = min(result, nums[low])
            low = mid + 1
        } else {
            result = min(result, nums[mid])
            high = mid - 1
        }
    }
    return result
}

// Time O(n), Space O(1)
fun findMinBruteForce(nums: IntArray): Int {
    var min = nums.first()
    for (num in nums) {
        if (num < min) {
            min = num
        }
    }
    return min
}