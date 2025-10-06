package arrays_and_hashing

fun main() {
    println(mostFrequent(intArrayOf(1, 2, 2, 3, 3, 3, 4)))
}

fun twoSum(input: IntArray, target: Int): Pair<Int, Int>? {
    val map = mutableMapOf<Int, Int>()
    for (i in input.indices) {
        if (map.containsKey(target - input[i])) {
            return Pair(i, map.getValue(target - input[i]))
        }
        map[input[i]] = i
    }
    return null
}

fun countPairsWithDiff(input: IntArray, difference: Int): Int {
    val set = mutableSetOf<Int>()
    for (item in input) {
        set.add(item)
    }

    var result = 0
    for (item in input) {
        if (set.contains(item + difference) || set.contains(item - difference)) {
            result++
        }
        set.remove(item)
    }
    return result
}

fun mostFrequent(input: IntArray): Int {
    val map = mutableMapOf<Int, Int>()
    for (item in input) {
        val count = map.getOrDefault(item, 0)
        map[item] = count + 1
    }
    var max = -1
    var result = input.first()
    for (entry in map) {
        if (entry.value > max) {
            max = entry.value
            result = entry.key
        }
    }
    return result
}