package sliding_window

fun main() {
    println(minWindowBruteForce("ADOBECODEBANC", "ABC"))
}

// Time O(n^2), Space O(t.length)
fun minWindowBruteForce(s: String, t: String): String {
    var result = ""
    var minLength = Int.MAX_VALUE
    for (i in 0..s.lastIndex) {
        //char frequencies map
        val map = mutableMapOf<Char, Int>()
        t.forEach {
            val count = map.getOrDefault(it, 0)
            map[it] = count + 1
        }
        var count = 0
        for (j in i..s.lastIndex) {
            if (map[s[j]] != null && map[s[j]]!! > 0) {
                count++
                val c = map[s[j]]!!
                map[s[j]] = c - 1
                if (count == t.length && (j - i + 1) <= minLength) {
                    minLength = j - i + 1
                    result = s.substring(i..j)
                }
            }
        }
    }
    return result
}