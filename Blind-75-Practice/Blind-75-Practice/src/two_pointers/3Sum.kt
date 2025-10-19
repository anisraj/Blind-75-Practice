package two_pointers

fun main() {
    println(threeSumBruteOptimal(intArrayOf(-1,0,1,2,-1,-4)))
}

// Time O(n log n + n^2), Space O(m)
//m = number of triplets to return
fun threeSumBruteOptimal(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    quickSort(nums, 0, nums.lastIndex)
    for (i in nums.indices) {
        if (i > 0 && nums[i] == nums[i - 1]) continue
        var j = i + 1
        var k = nums.lastIndex
        while (j < k) {
            val sum = nums[i] + nums[j] + nums[k]
            if (sum < 0) {
                j++
            } else if (sum > 0) {
                k--
            } else {
                result.add(listOf(nums[i], nums[j], nums[k]))
                j++
                k--
                while (j < k && nums[j] == nums[j - 1]) j++
                while (j < k && nums[k] == nums[k++]) k--
            }
        }

    }
    return result
}

// Time O(n^2), Space O(n)
fun threeSumBruteBetter(nums: IntArray): List<List<Int>> {
    val result = mutableSetOf<List<Int>>()
    for (i in nums.indices) {
        val set = mutableSetOf<Int>()
        for (j in i + 1..<nums.size) {
            val kValue = -(nums[i] + nums[j])
            if (set.contains(kValue)) {
                val temp = listOf(nums[i], nums[j], kValue)
                val arrayToSort = temp.toIntArray()
                quickSort(arrayToSort,0, 2)
                result.add(arrayToSort.toList())
            }
            set.add(nums[j])
        }
    }
    return result.toList()
}


// Time O(n^3), Space O(n)
fun threeSumBruteForce(nums: IntArray): List<List<Int>> {
    val result = mutableSetOf<List<Int>>()
    for (i in nums.indices) {
        for (j in i + 1..<nums.size) {
            for (k in j + 1..<nums.size) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    val temp = listOf(nums[i], nums[j], nums[k])
                    val arrayToSort = temp.toIntArray()
                    quickSort(arrayToSort,0, 2)
                    result.add(arrayToSort.toList())
                }
            }
        }
    }
    return result.toList()
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
    //swap pivot and j
    val temp = nums[j]
    nums[j] = nums[low]
    nums[low] = temp
    return j
}