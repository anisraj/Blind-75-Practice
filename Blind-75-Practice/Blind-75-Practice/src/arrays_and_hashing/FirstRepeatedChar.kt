package arrays_and_hashing

fun main() {
    println(getFirstRepeatedChar("green apple"))
}

private fun getFirstRepeatedChar(input: String): Char {
    val set = mutableSetOf<Char>()
    for (char in input) {
        if (set.contains(char)) {
            return char
        }
        set.add(char)
    }
    return Char.MIN_VALUE
}