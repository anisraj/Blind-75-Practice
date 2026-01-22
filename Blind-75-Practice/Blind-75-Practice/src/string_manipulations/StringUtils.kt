package string_manipulations

fun main() {
    println(StringUtils.capitalize("abcd abcv"))
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

        fun areRotations(str1: String, str2: String): Boolean {
            if (str1.length != str2.length) {
                return false
            }
            if (!(str1.plus(str1).contains(str2))) {
                return false
            }
            return true
        }

        fun removeDuplicates(input: String): String {
            val stringBuilder = StringBuilder()
            val set = mutableSetOf<Char>()
            for (ch in input) {
                if (!set.contains(ch)) {
                    set.add(ch)
                    stringBuilder.append(ch)
                }
            }
            return stringBuilder.toString()
        }

        fun maxOccuringChar(input: String): Char {
            val ASCII_SIZE = 256
            val frequencies = IntArray(ASCII_SIZE)
            for (ch in input) {
                frequencies[ch.code]++
            }
            var max = 0
            var result = ' '
            for (i in frequencies.indices) {
                if (frequencies[i] > max) {
                    max = frequencies[i]
                    result = i.toChar()
                }
            }
            return result
        }

        fun capitalize(sentence: String): String {
            val words = sentence.split(" ").toMutableList()
            for (i in words.indices) {
                words[i] = words[i].substring(0, 1).uppercase() + words[i].substring(1).lowercase()
            }
            return words.joinToString(" ")
        }

        fun isAnagram(str1: String, str2: String): Boolean {
            val array = IntArray(26)
            for (ch in str1.lowercase()) {
                array[ch - 'a']++
            }
            for (ch in str2.lowercase()) {
                array[ch - 'a']--
            }
            for (value in array) {
                if (value != 0) {
                    return false
                }
            }
            return true
        }
    }
}