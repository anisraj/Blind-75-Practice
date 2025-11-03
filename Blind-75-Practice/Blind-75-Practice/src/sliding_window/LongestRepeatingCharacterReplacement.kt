package sliding_window

import kotlin.math.max

fun main() {
    println(characterReplacementBetter("IMNJJTRMJEGMSOLSCCQICIHLQIOGBJAEHQOCRAJQMBIBATGLJDTBNCPIFRDLRIJHRABBJGQAOLIKRLHDRIGERENNMJSDSSMESSTR", 2))
}

// Time O(n), Space O(26)
fun characterReplacementBetter(s: String, k: Int): Int {
    var result = 0
    var maxFrequency = 0
    val hash = IntArray(26) { 0 }
    var i = 0
    var j = 0
    while (j <= s.lastIndex) {
        hash[s[j] - 'A']++
        maxFrequency = max(maxFrequency, hash[s[j] - 'A'])
        if ((j - i + 1) - maxFrequency > k) {
            hash[s[i] - 'A']--
            i++
        }
        if ((j - i + 1) - maxFrequency <= k) {
            result = max(result, j - i + 1)
        }
        j++
    }
    return result
}

// Time O(n^2), Space O(26)
fun characterReplacementBruteForce(s: String, k: Int): Int {
    var result = 0
    for (i in 0..s.lastIndex) {
        val hash = IntArray(26) { 0 }
        var maxFrequency = 0
        for (j in i..s.lastIndex) {
            hash[s[j] - 'A']++
            maxFrequency = max(maxFrequency, hash[s[j] - 'A'])
            val length = j - i + 1
            if (length - maxFrequency <= k) {
                result = max(result, length)
            } else {
                break
            }
        }
    }
    return result
}