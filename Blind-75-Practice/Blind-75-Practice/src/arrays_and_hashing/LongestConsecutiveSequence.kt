package arrays_and_hashing

import kotlin.math.max

fun main() {
    println(longestConsecutiveOptimal(intArrayOf(100,102,100,101,4,3,2,1,2)))
}

fun longestConsecutiveOptimal(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    var longest = 1
    val set = mutableSetOf<Int>()
    for (num in nums) {
        set.add(num)
    }
    for (item in set) {
        if (!set.contains(item - 1)) {
            var counter = 1
            var x = item + 1
            while (set.contains(x)) {
                counter++
                x += 1
            }
            longest = max(longest, counter)
        }
    }
    return longest
}

// Time O(n log n + n), Space O(1)
fun longestConsecutiveBetter(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    quickSort(nums, 0, nums.size - 1)
    var longest = 1
    var lastSmaller = Int.MIN_VALUE
    var counter = 0
    for (num in nums) {
        if (num - 1 == lastSmaller) {
            counter++
            lastSmaller = num
        } else if (num != lastSmaller) {
            counter = 1
            lastSmaller = num
        }
        longest = max(longest, counter)
    }
    return longest
}

private fun quickSort(nums: IntArray, low: Int, high: Int) {
    if (low < high) {
        val partitionIndex = getPartitionIndex(nums, low, high)
        quickSort(nums, low, partitionIndex - 1)
        quickSort(nums, partitionIndex + 1, high)
    }
}

private fun getPartitionIndex(nums: IntArray, low: Int, high: Int): Int {
    val pivot = nums[low]
    var i = low
    var j = high
    while (i < j) {
        while (nums[i] <= pivot && i <= high - 1) {
            i++
        }
        while (nums[j] > pivot && j >= low + 1) {
            j--
        }
        if (i < j) {
            //swap i and j
            val temp = nums[i]
            nums[i] = nums[j]
            nums[j] = temp
        }
    }
    //swap j and pivot
    val temp = nums[j]
    nums[j] = nums[low]
    nums[low] = temp
    return j
}

// Time O(n^2), Space O(1)
fun longestConsecutiveBruteForce(nums: IntArray): Int {
    var longest = if (nums.isEmpty()) 0 else 1
    for (i in nums.indices) {
        var x = nums[i]
        var counter = 1
        while (linearSearch(nums, x + 1)) {
            counter++
            x += 1
        }
        longest = max(longest, counter)
    }
    return longest
}

private fun linearSearch(nums: IntArray, num: Int):Boolean {
    for (item in nums) {
        if (item == num) {
            return true
        }
    }
    return false
}
