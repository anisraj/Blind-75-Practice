package string_manipulations

fun main() {
    println(StringUtils.reverseWords("Hello word"))
}

class StringUtils {
    companion object {
        fun countVowels(input: String): Int {
            var count = 0
            val vowels = "aeiou"
            for (ch in input.lowercase()) {
                if (vowels.indexOf(ch) != -1) {
                    count++
                }
            }
            return count
        }

        fun reverseString(input: String): String {
            val stringBuilder = StringBuilder()
            for (i in input.length - 1 downTo 0) {
                stringBuilder.append(input[i])
            }
            return stringBuilder.toString()
        }

        fun reverseWords(input: String): String {
            val stringBuilder = StringBuilder()
            val words = input.split(" ")
            for (word in words.size - 1 downTo 0) {
                stringBuilder.append("${words[word]} ")
            }
            return stringBuilder.toString().trim()
        }
    }
}