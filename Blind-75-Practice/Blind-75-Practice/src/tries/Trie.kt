package tries

fun main() {
    val trie = Trie()
    trie.insert("card")
    trie.insert("care")
    println(trie.longestCommonPrefix())
}

class Trie {
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

    fun contains(word: String): Boolean {
        var current = root
        for (ch in word) {
            if (!current.hasChild(ch)) {
                return false
            }
            current = current.getChild(ch)!!
        }
        return current.isEndOfWord
    }

    fun containsRecursive(word: String): Boolean {
        return containsRecursive(root, 0, word)
    }

    private fun containsRecursive(node: Node?, index: Int, word: String): Boolean {
        node?.let {
            if (index == word.length) {
                return node.isEndOfWord
            }
            val ch = word[index]
            if (!node.hasChild(ch)) {
                return false
            }
            return containsRecursive(node.getChild(ch), index + 1, word)
        } ?: run {
            return false
        }
    }

    fun traversePreOrder() {
        traversePreOrder(root)
    }

    private fun traversePreOrder(node: Node) {
        println(node.value)
        for (childNode in node.getChildren()) {
            traversePreOrder(childNode)
        }
    }

    fun traversePostOrder() {
        traversePostOrder(root)
    }

    private fun traversePostOrder(node: Node) {
        for (childNode in node.getChildren()) {
            traversePostOrder(childNode)
        }
        println(node.value)
    }

    fun remove(word: String) {
        if (word.isEmpty()) {
            return
        }
        remove(root, word, 0)
    }

    private fun remove(node: Node, word: String, index: Int) {
        val ch = word[index]
        val child = node.getChild(ch)
        child?.let {
            if (index == word.lastIndex) {
                it.isEndOfWord = false
            } else {
                remove(it, word, index + 1)
            }
            if (!it.isEndOfWord && it.getChildren().isEmpty()) {
                node.removeChild(ch)
            }
        }
    }

    fun findWords(prefix: String): List<String> {
        val words = mutableListOf<String>()
        val lastNode = findLastNode(prefix)
        findWords(lastNode, StringBuilder(prefix), words)
        return words
    }

    private fun findWords(node: Node?, word: StringBuilder, words: MutableList<String>) {
        node?.let {
            if (it.isEndOfWord) {
                words.add(word.toString())
            }

            for (child in it.getChildren()) {
                word.append(child.value)
                findWords(child, word, words)
                word.deleteCharAt(word.lastIndex)
            }
        }
    }

    fun countWords(): Int {
        return countWords(root)
    }

    private fun countWords(node: Node): Int {
        var total = 0
        if (node.isEndOfWord) {
            total++
        }
        for (child in node.getChildren()) {
            total += countWords(child)
        }
        return total
    }

    private fun findLastNode(prefix: String): Node? {
        var current = root
        for (ch in prefix) {
            current = current.getChild(ch) ?: return null
        }
        return current
    }

    fun longestCommonPrefix(): String {
        val shortestWord = getShortestWord(findWords(""))
        val stringBuilder = StringBuilder()
        var current = root
        var count = 0
        while (current.getChildren().size == 1 && count < shortestWord.length) {
            current = current.getChildren().first()
            stringBuilder.append(current.value)
            count++
        }
        return stringBuilder.toString()
    }

    private fun getShortestWord(words: List<String>): String {
        var shortest = words.first()
        for (word in words) {
            if (word.length < shortest.length) {
                shortest = word
            }
        }
        return shortest
    }

    private data class Node(val value: Char) {
        private val children = mutableMapOf<Char, Node>()
        var isEndOfWord = false

        fun hasChild(ch: Char): Boolean {
            return children.containsKey(ch)
        }

        fun addChild(ch: Char) {
            children[ch] = Node(ch)
        }

        fun getChild(ch: Char): Node? {
            return children[ch]
        }

        fun removeChild(ch: Char) {
            children.remove(ch)
        }

        fun getChildren(): Array<Node> {
            return children.values.toTypedArray()
        }
    }
}