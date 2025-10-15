package arrays_and_hashing

fun main() {
    val input = listOf("we","say",":","yes")
    val encodedString = encode(input)
    println(decode(encodedString))
}

fun encode(strs: List<String>): String {
    val stringBuilder = StringBuilder()
    for (str in strs) {
        stringBuilder.append(str + Char.MIN_VALUE)
    }
    return stringBuilder.toString()
}

fun decode(str: String): List<String> {
    val resultList = mutableListOf<String>()
    var stringBuilder = StringBuilder()
    for (char in str) {
        if (char == Char.MIN_VALUE) {
            resultList.add(stringBuilder.toString())
            stringBuilder = StringBuilder()
            continue
        }
        stringBuilder.append(char)
    }
    return resultList
}