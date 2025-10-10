package arrays_and_hashing

fun main() {
    println(groupAnagramsBetter(arrayOf("eat","tea","tan","ate","nat","bat")))
}

// Time O(n), Space O(n)
fun groupAnagramsOptimal(strs: Array<String>): List<List<String>> {
    val map = mutableMapOf<List<Int>, MutableList<String>>()
    for (item in strs) {
        val countArray = IntArray(26) { 0 }
        for (ch in item) {
            countArray[ch - 'a']++
        }
        val key = countArray.toList()
        val list = map.getOrDefault(key, mutableListOf())
        list.add(item)
        map[key] = list
    }
    return map.values.toList()
}

// Time O(n * n log n), Space O(n)
fun groupAnagramsBetter(strs: Array<String>): List<List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()
    for (item in strs) {
        val sortedString = StringBuilder(item)
        sort(sortedString, 0, item.length - 1)
        val list = map.getOrDefault(sortedString.toString(), mutableListOf())
        map[sortedString.toString()] = list.apply { add(item) }
    }
    return map.values.toList()
}

private fun sort(item: StringBuilder, low: Int, high: Int) {
    if (low >= high) {
        return
    }
    val midIndex = (low + high) / 2
    sort(item, low, midIndex)
    sort(item, midIndex + 1, high)
    //merge
    val temp = StringBuilder()
    var leftPointer = low
    var rightPointer = midIndex + 1
    while (leftPointer <= midIndex && rightPointer <= high) {
        if (item[leftPointer] <= item[rightPointer]) {
            temp.append(item[leftPointer])
            leftPointer++
        } else {
            temp.append(item[rightPointer])
            rightPointer++
        }
    }
    while (leftPointer <= midIndex) {
        temp.append(item[leftPointer])
        leftPointer++
    }
    while (rightPointer <= high) {
        temp.append(item[rightPointer])
        rightPointer++
    }
    for (i in low..high) {
        item[i] = temp[i - low]
    }

}

// Time O(n^2), Space O(n)
fun groupAnagramsBruteForce(strs: Array<String>): List<List<String>> {
    val resultList = mutableListOf<List<String>>()
    for (i in strs.indices) {
        val l = resultList.flatten()
        if (l.contains(strs[i])) {
            continue
        }
        val innerList = mutableListOf<String>()
        innerList.add(strs[i])
        for (j in i + 1..<strs.size) {
            if (isAnagram(strs[i], strs[j])) {
                innerList.add(strs[j])
            }
        }
        resultList.add(innerList)
    }
    return resultList
}

private fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) {
        return false
    }
    val mapOne = mutableMapOf<Char, Int>()
    val mapTwo = mutableMapOf<Char, Int>()

    for (ch in s) {
        val count = mapOne.getOrDefault(ch, 0)
        mapOne[ch] = count + 1
    }

    for (ch in t) {
        val count = mapTwo.getOrDefault(ch, 0)
        mapTwo[ch] = count + 1
    }

    for (entry in mapOne) {
        if (mapTwo[entry.key] != entry.value) {
            return false
        }
    }

    return true
}