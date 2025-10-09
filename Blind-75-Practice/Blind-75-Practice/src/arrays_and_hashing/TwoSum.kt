package arrays_and_hashing

fun main() {
    println(twoSumOptimal(intArrayOf(2,11,15,7,16), 9).contentToString())
}

//this will be optimal if we return values, because after sorting
//indices will be rearranged
// Time O(n + n log n), Space O(1)
private fun twoSumOptimal(nums: IntArray, target: Int): IntArray {
    sort(nums, 0, nums.size - 1)
    var i = 0
    var j = nums.size - 1
    while (i < j) {
        if (nums[i] + nums[j] == target) {
            return intArrayOf(nums[i], nums[j])
        }
        if (nums[j] - nums[i] >= target) {
            j--
        } else {
            i++
        }
    }
    return intArrayOf()
}

private fun sort(nums: IntArray, low: Int, high: Int) {
    if (low >= high) {
        return
    }
    val midIndex = (low + high) / 2
    sort(nums, low, midIndex)
    sort(nums, midIndex + 1, high)

    //merge
    val temp = mutableListOf<Int>()
    var leftPointer = low
    var rightPointer = midIndex + 1

    while (leftPointer <= midIndex && rightPointer <= high) {
        if (nums[leftPointer] <= nums[rightPointer]) {
            temp.add(nums[leftPointer])
            leftPointer++
        } else {
            temp.add(nums[rightPointer])
            rightPointer++
        }
    }

    while (leftPointer <= midIndex) {
        temp.add(nums[leftPointer])
        leftPointer++
    }

    while (rightPointer <= high) {
        temp.add(nums[rightPointer])
        rightPointer++
    }

    for (i in low..high) {
        nums[i] = temp[i - low]
    }

}


// Time O(n), Space O(n)
private fun twoSumBetter(nums: IntArray, target: Int): IntArray {
    //map of <number, index>
    val map = mutableMapOf<Int, Int>()
    for (i in nums.indices) {
        val minusResult = target - nums[i]
        if (map.containsKey(minusResult)) {
            return intArrayOf(map.getValue(minusResult), i)
        }
        map[nums[i]] = i
    }
    return intArrayOf()
}

// Time O(n^2), Space O(1)
private fun twoSumBruteForce(nums: IntArray, target: Int): IntArray {
    for (i in nums.indices) {
        for (j in i + 1..<nums.size) {
            if (nums[i] + nums[j] == target) {
                return intArrayOf(i, j)
            }
        }
    }

    return intArrayOf()
}