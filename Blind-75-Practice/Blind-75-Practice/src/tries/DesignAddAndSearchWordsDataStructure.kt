package tries

fun main() {
    val wordDictionary = WordDictionary()
    wordDictionary.addWord("at")
    wordDictionary.addWord("and")
    wordDictionary.addWord("an")
    wordDictionary.addWord("add")
    println(wordDictionary.search("a")) //false
    println(wordDictionary.search(".at")) //false
    wordDictionary.addWord("bat")
    println(wordDictionary.search(".at"))//true
    println(wordDictionary.search("an."))//true
    println(wordDictionary.search("a.d."))//false
    println(wordDictionary.search("b."))//false
    println(wordDictionary.search("a.d"))//true
    println(wordDictionary.search("."))//false
}

class WordDictionary {
    private val root = Node(Char.MIN_VALUE)

    fun addWord(word: String) {
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
        return search(word, 0, root)
    }

    private fun search(word: String, index: Int, node: Node): Boolean {
        var current = node
        for (i in index..<word.length) {
            val ch = word[i]
            if (ch == '.') {
                for (child in current.getChildren()) {
                    if (search(word, i + 1, child)) {
                        return true
                    }
                }
                return false
            } else {
                if (!current.hasChild(ch)) {
                    return false
                }
                current = current.getChild(ch)!!
            }
        }

        return current.isEndOfWord
    }

    private data class Node(val char: Char) {
        private val children = mutableMapOf<Char, Node>()
        var isEndOfWord = false

        fun addChild(char: Char) {
            children[char] = Node(char)
        }

        fun getChildren(): Array<Node> {
            return children.values.toTypedArray()
        }

        fun hasChild(char: Char): Boolean {
            return children.containsKey(char)
        }

        fun getChild(char: Char): Node? {
            return children[char]
        }
    }

}

class WordDictionaryBruteForce {
    private val list = mutableListOf<String>()

    fun addWord(word: String) {
        list.add(word)
    }

    fun search(word: String): Boolean {
        outerLoop@ for (item in list) {
            if (word.length != item.length) {
                continue
            }
            innerLoop@ for (i in word.indices) {
                if (word[i] == '.' && i != word.lastIndex) {
                    continue@innerLoop
                } else if (word[i] != '.' && word[i] != item[i]) {
                    continue@outerLoop
                } else if (i == word.lastIndex) {
                    return true
                }
            }
        }
        return false
    }

}