package tries

fun main() {
    val trie = ImplementTrie()
    trie.insert("apple")
    println(trie.startsWith("app"))
}

class ImplementTrie {
    private val root = Node(Char.MIN_VALUE)

    fun insert(word: String) {
        var current = root
        for (ch in word) {
            if (!current.hasChild(ch)) {
                current.addChild(ch)
            }
            current = current.getChild(ch)!!
        }
        current.isEndOfWord = true
    }

    fun search(word: String): Boolean {
        var current = root
        for (ch in word) {
            if (!current.hasChild(ch)) {
                return false
            }
            current = current.getChild(ch)!!
        }
        return current.isEndOfWord
    }

    fun startsWith(prefix: String): Boolean {
        var current = root
        for (ch in prefix) {
            if (!current.hasChild(ch)) {
                return false
            }
            current = current.getChild(ch)!!
        }
        return true
    }

    private data class Node(val value: Char) {
        private val children = mutableMapOf<Char, Node>()
        var isEndOfWord = false

        fun addChild(char: Char) {
            children[char] = Node(char)
        }

        fun hasChild(char: Char): Boolean {
            return children.containsKey(char)
        }

        fun getChild(char: Char): Node? {
            return children[char]
        }
    }
}