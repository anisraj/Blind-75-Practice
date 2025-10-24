package sliding_window

import kotlin.math.max

fun main() {
    println(lengthOfLongestSubstringOptimal("abcabcbb"))
}

// Time O(n), Space O(n)
fun lengthOfLongestSubstringOptimal(s: String): Int {
    var longest = 0
    //map of <char, index>
    val map  = mutableMapOf<Char, Int>()
    var l = 0
    var r = 0
    while (r <= s.lastIndex) {
        if (map.containsKey(s[r])) {
            l = max(l, map.getValue(s[r]) + 1)
        }
        map[s[r]] = r
        longest = max(longest, (r - l + 1))
        r++
    }
    return longest
}

// Time O(n), Space O(n)
fun lengthOfLongestSubstringBetter(s: String): Int {
    var longest = 0
    val set = mutableSetOf<Char>()
    var l = 0
    var r = 0
    while (r <= s.lastIndex) {
        while (s[r] in set) {
            set.remove(s[l])
            l++
        }
        set.add(s[r])
        longest = max(longest, (r - l + 1))
        r++

    }
    return longest
}


// Time O(n^2), Space O(n)
fun lengthOfLongestSubstringBruteForce(s: String): Int {
    var longest = 0
    for (i in 0..s.lastIndex) {
        val set = mutableSetOf<Char>()
        set.add(s[i])
        for (j in i + 1..s.lastIndex) {
            if (!set.contains(s[j])) {
                set.add(s[j])
            } else {
                break
            }
        }
        longest = max(longest, set.size)
    }
    return longest
}