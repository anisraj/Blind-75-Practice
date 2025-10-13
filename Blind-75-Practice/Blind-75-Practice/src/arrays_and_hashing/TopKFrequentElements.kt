package arrays_and_hashing

fun main() {
    println(topKFrequentBetter(intArrayOf(1,1,1,2,2,3), 2).contentToString())
}

//Time O(n), Space O(n)
fun topKFrequentBetter(nums: IntArray, k: Int): IntArray {
    val countArray = Array<MutableList<Int>?>(nums.size + 1) { null }
    //map of <number, occurrences>
    val map = mutableMapOf<Int, Int>()
    for (item in nums) {
        val count = map.getOrDefault(item, 0)
        map[item] = count + 1
    }

    for (entry in map) {
        val list = countArray[entry.value] ?: mutableListOf()
        countArray[entry.value] = list.apply { add(entry.key) }
    }
    val result = mutableListOf<Int>()
    for (i in countArray.size - 1 downTo 1) {
        countArray[i]?.let {
            for (num in it) {
                result.add(num)
                if (result.size == k) {
                    return result.toIntArray()
                }
            }
        }
    }
    return result.toIntArray()
}

// Time O(n log n), Space O(n)
fun topKFrequentBruteForce(nums: IntArray, k: Int): IntArray {
    //map of <number, occurrences>
    val map = mutableMapOf<Int, Int>()
    for (item in nums) {
        val count = map.getOrDefault(item, 0)
        map[item] = count + 1
    }

    val sortedList = map.entries.sortedByDescending {
        it.value
    }

    val result = IntArray(k)
    for (i in 0..<k) {
        result[i] = sortedList[i].key
    }

    return result
}
