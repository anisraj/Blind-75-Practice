package arrays_and_hashing

fun main() {
    println(containsDuplicateBetter(intArrayOf(1,2,3,1)))
}

// Time O(n), Space O(n)
fun containsDuplicateOptimal(input: IntArray): Boolean {
    val set = mutableSetOf<Int>()
    for (item in input) {
        if (set.contains(item)) {
            return true
        }
        set.add(item)
    }
    return false
}

// will merge sort the array
// Time O(n log n + n), Space O(1)
fun containsDuplicateBetter(input: IntArray): Boolean {
    sort(input, 0, input.size - 1)
    for (i in 0..<input.size - 1) {
        if (input[i] == input[i + 1]) {
            return true
        }
    }
    return false
}

private fun sort(input: IntArray, low: Int, high: Int) {
    if (low >= high) {
        return
    }
    val middleIndex = (low + high) / 2
    sort(input, low, middleIndex)
    sort(input, middleIndex + 1, high)
    //merge
    val temp = mutableListOf<Int>()
    var left = low
    var right = middleIndex + 1
    while (left <= middleIndex && right <= high) {
        if (input[left] <= input[right]) {
            temp.add(input[left])
            left++
        } else {
            temp.add(input[right])
            right++
        }
    }
    while (left <= middleIndex) {
        temp.add(input[left])
        left++
    }
    while (right <= high) {
        temp.add(input[right])
        right++
    }
    for (i in low..high) {
        input[i] = temp[i - low]
    }
}

// Time O(n^2), Space O(1)
fun containsDuplicateBruteForce(input: IntArray): Boolean {
    for (i in input.indices) {
        for (j in i + 1..<input.size) {
            if (input[i] == input[j]) {
                return true
            }
        }
    }
    return false
}