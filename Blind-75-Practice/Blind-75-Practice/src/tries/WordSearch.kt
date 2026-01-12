package tries

fun main() {
    val wordSearch = WordSearch()
    val board = arrayOf(
        charArrayOf('o', 'a', 'a', 'n'),
        charArrayOf('e', 't', 'a', 'e'),
        charArrayOf('i', 'h', 'k', 'r'),
        charArrayOf('i', 'f', 'l', 'v')
    )
    val words = arrayOf("oath", "pea", "eat", "rain")
    println(wordSearch.findWords(board, words))
}

class WordSearch {
    private val root = Node(Char.MIN_VALUE)
    private val visited = mutableSetOf<Pair<Int, Int>>()
    private val set = mutableSetOf<String>()

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        for (word in words) {
            insert(word)
        }

        val rows = board.size
        val columns = board.first().size
        for (row in 0..<rows) {
            for (column in 0..<columns) {
                findWord(row, column, root, StringBuilder(""), board)
            }
        }

        return set.toList()
    }

    private fun findWord(row: Int, column: Int, node: Node, word: StringBuilder, board: Array<CharArray>) {
        if (node.isEndOfWord) {
            set.add(word.toString())
        }
        if (row < 0 ||
            column < 0 ||
            row >= board.size ||
            column >= board.first().size ||
            visited.contains(Pair(row, column)) ||
            !node.hasChild(board[row][column])) {
            return
        }
        visited.add(Pair(row, column))
        word.append(board[row][column])
        val nextNode = node.getChild(board[row][column])!!
        findWord(row + 1, column, nextNode, word, board)
        findWord(row - 1, column, nextNode, word, board)
        findWord(row, column + 1, nextNode, word, board)
        findWord(row, column - 1, nextNode, word, board)
        word.deleteCharAt(word.length - 1)
        visited.remove(Pair(row, column))
    }

    private fun insert(word: String) {
        var current = root
        for (ch in word) {
            if (!current.hasChild(ch)) {
                current.addChild(ch)
            }
            current = current.getChild(ch)!!
        }
        current.isEndOfWord = true
    }

    private data class Node(val char: Char) {
        private val children = mutableMapOf<Char, Node>()
        var isEndOfWord = false

        fun hasChild(char: Char): Boolean {
            return children.containsKey(char)
        }

        fun addChild(char: Char) {
            children[char] = Node(char)
        }

        fun getChild(char: Char): Node? {
            return children[char]
        }
    }
}


class WordSearchBruteForce {
    private val visited = mutableSetOf<Pair<Int, Int>>()

    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val list = mutableListOf<String>()
        for (word in words) {
            if (exists(board, word)) {
                list.add(word)
            }
        }
        return list
    }

    private fun exists(board: Array<CharArray>, word: String): Boolean {
        val columns = board.first().size
        val rows = board.size
        for (row in 0..<rows) {
            for (column in 0..<columns) {
                if (checkAllEdges(row, column, 0, word, board)) {
                    return true
                }
            }
        }
        return false
    }

    private fun checkAllEdges(
        row: Int,
        column: Int,
        wordIndex: Int,
        word: String,
        board: Array<CharArray>
    ): Boolean {
        if (wordIndex == word.length) {
            return true
        }
        if (row < 0 ||
            column < 0 ||
            row >= board.size ||
            column >= board.first().size ||
            board[row][column] != word[wordIndex] ||
            visited.contains(Pair(row, column))) {
            return false
        }
        visited.add(Pair(row, column))
        val result = checkAllEdges(row + 1, column, wordIndex + 1, word, board) ||
                checkAllEdges(row - 1, column, wordIndex + 1, word, board) ||
                checkAllEdges(row, column + 1, wordIndex + 1, word, board) ||
                checkAllEdges(row, column - 1, wordIndex + 1, word, board)
        visited.remove(Pair(row, column))
        return result
    }
}

