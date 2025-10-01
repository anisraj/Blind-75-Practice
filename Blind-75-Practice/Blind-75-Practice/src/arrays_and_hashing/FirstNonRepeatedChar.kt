package arrays_and_hashing

fun main() {
    println(getFirstNonRepeatedChar("a green apple"))
}

private fun getFirstNonRepeatedChar(input: String): Char {
    val map = mutableMapOf<Char, Int>() // mutable map of in kotlin preserves the order
    for (char in input) {
        val count = map.getOrDefault(char, 0)
        map[char] = count + 1
    }

    for (char in input) {
        if (char != ' ' && map[char] == 1) {
            return char
        }
    }

    return Char.MIN_VALUE
}