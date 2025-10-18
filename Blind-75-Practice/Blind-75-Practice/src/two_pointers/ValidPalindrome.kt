package two_pointers

fun main() {
    println(isPalindromeOptimal(" "))
}

// Time O(n), Space O(1)
fun isPalindromeOptimal(s: String): Boolean {
    var i = 0
    var j = s.length - 1
    while (i <= j) {
        while (i < j && !s[i].isLetterOrDigit()) {
            i++
        }
        while (j > i && !s[j].isLetterOrDigit()) {
            j--
        }
        if (s[i].lowercase() != s[j].lowercase()) {
            return false
        }
        i++
        j--
    }
    return true
}

// Time O(n), Space O(n)
fun isPalindromeBetter(s: String): Boolean {
    val newS = StringBuilder()
    for (ch in s) {
        if (ch.isLetterOrDigit()) {
            newS.append(ch.lowercase())
        }
    }
    var i = 0
    var j = newS.length - 1
    while (i <= j) {
        if (newS[i] != newS[j]) {
            return false
        }
        i++
        j--
    }
    return true
}

// Time O(n), Space O(n)
fun isPalindromeBruteForce(s: String): Boolean {
    val newS = StringBuilder()
    for (ch in s) {
        if (ch.isLetterOrDigit()) {
            newS.append(ch.lowercase())
        }
    }
    val reversedString = StringBuilder()
    for (i in s.length - 1 downTo 0) {
        if (s[i].isLetterOrDigit()) {
            reversedString.append(s[i].lowercase())
        }
    }
    return newS.toString() == reversedString.toString()
}

