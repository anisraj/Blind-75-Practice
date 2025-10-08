package arrays_and_hashing

fun main() {
    println(isAnagramBruteForce("aacc", "ccac"))
}

// Time O(n), Space O(n)
fun isAnagramBruteOptimal(s: String, t: String): Boolean {
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


// Time O(n log n), Space O(n)
fun isAnagramBruteForce(s: String, t: String): Boolean {
    if (s.length != t.length) {
        return false
    }
    val sortedStringS = StringBuilder(s)
    val sortedStringT = StringBuilder(t)
    sort(sortedStringS, 0, s.length - 1)
    sort(sortedStringT, 0, t.length - 1)
    return sortedStringS == sortedStringT
}

private fun sort(s: StringBuilder, low: Int, high: Int) {
    if (low >= high) {
        return
    }
    val midIndex = (low + high) / 2
    sort(s, low, midIndex)
    sort(s, midIndex + 1, high)
    //merge
    val temp = StringBuilder()
    var left = low
    var right = midIndex + 1
    while (left <= midIndex && right <= high) {
        if (s[left] <= s[right]) {
            temp.append(s[left])
            left++
        } else {
            temp.append(s[right])
            right++
        }
    }

    while (left <= midIndex) {
        temp.append(s[left])
        left++
    }

    while (right <= high) {
        temp.append(s[right])
        right++
    }
    for (i in low..high) {
        s[i] = temp[i - low]
    }
}