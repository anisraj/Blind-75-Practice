package arrays_and_hashing

fun main() {
    println(productExceptSelfOptimal(intArrayOf(1,2,3,4)).contentToString())
}

// Time O(n), Space O(1)
fun productExceptSelfOptimal2(nums: IntArray): IntArray {
    val resultArray = IntArray(nums.size)
    val prefix = 1
    for (i in 0..nums.lastIndex) {
        
    }
    return resultArray
}

// Time O(n), Space O(2n)
fun productExceptSelfOptimal(nums: IntArray): IntArray {
    val prefixArray = IntArray(nums.size)
    val suffixArray = IntArray(nums.size)
    prefixArray[0] = 1
    for (i in 1..<nums.size) {
        prefixArray[i] = prefixArray[i - 1] * nums[i - 1]
    }
    suffixArray[nums.size - 1] = 1
    for (i in nums.size - 2 downTo 0) {
        suffixArray[i] = suffixArray[i + 1] * nums[i + 1]
    }
    val resultArray = IntArray(nums.size)
    for (i in nums.indices) {
        resultArray[i] = prefixArray[i] * suffixArray[i]
    }
    return resultArray
}

// Time O(n), Space O(1)
fun productExceptSelfBetter(nums: IntArray): IntArray {
    var product = 1
    var zeroCount = 0
    for (num in nums) {
        if (num == 0) {
            zeroCount++
        } else {
            product *= num
        }
    }

    val resultArray = IntArray(nums.size)
    if (zeroCount > 1) {
        return resultArray
    }
    for (i in nums.indices) {
        if (nums[i] != 0 && zeroCount > 0) {
            resultArray[i] = 0
        } else if (zeroCount > 0){
            resultArray[i] = product
        } else {
            resultArray[i] = product / nums[i]
        }
    }
    return resultArray

}

// Time O(n^2), Space O(1)
fun productExceptSelfBruteForce(nums: IntArray): IntArray {
    val resultArray = IntArray(nums.size)
    for (i in nums.indices) {
        var product = 1
        for (j in nums.indices) {
            if (i != j) {
                product *= nums[j]
            }
        }
        resultArray[i] = product
    }
    return resultArray
}