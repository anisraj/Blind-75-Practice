package binary_search

fun main() {
    val array = intArrayOf(4,5,6,7,0,1,2)
    println(searchBetter(array, 0))
}

private fun searchBetter(nums: IntArray, target: Int): Int {
    var low = 0
    var high = nums.size - 1
    while (low <= high) {
        val mid = (low + high) / 2
        if (nums[mid] >= nums[low]) {
            if (target >= nums[low] && target <= nums[mid]) {
                return searchItem(nums, low, mid, target)
            }
            low = mid + 1
        } else {
            if (target >= nums[mid] && target <= nums[high]) {
                return searchItem(nums, mid, high, target)
            }
            high = mid - 1
        }
    }
    return -1
}

private fun searchItem(
    array: IntArray,
    low: Int,
    high: Int,
    target: Int
): Int {
    var l = low
    var h = high
    while (l <= h) {
        val mid = (l + h) / 2
        if (array[mid] == target) {
            return mid
        } else if (target < array[mid]) {
            h = mid - 1
        } else {
            l = mid + 1
        }
    }
    return -1
}

private fun searchBruteForce(nums: IntArray, target: Int): Int {
    for (i in nums.indices) {
        if (nums[i] == target) {
            return i
        }
    }
    return -1
}