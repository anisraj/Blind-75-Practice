package backtracking

fun main() {
    val wordSearch = WordSearch()
    val board = arrayOf(
        charArrayOf('A', 'B', 'C', 'E'),
        charArrayOf('S', 'F', 'C', 'S'),
        charArrayOf('A', 'D', 'E', 'E')
    )
    println(wordSearch.exist(board, "EACH"))

}

class WordSearch {
    private val visited = mutableSetOf<Pair<Int, Int>>()

    fun exist(board: Array<CharArray>, word: String): Boolean {
        for (row in board.indices) {
            for (column in 0..<board[0].size) {
                if (checkAllEdges(row, column, 0, board, word)) {
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
        board: Array<CharArray>,
        word: String
    ): Boolean {
        if (wordIndex == word.length) {
            return true
        }
        if (
            row < 0 ||
            column < 0 ||
            row >= board.size ||
            column >= board[0].size ||
            board[row][column] != word[wordIndex] ||
            visited.contains(Pair(row, column))
        ) {
            return false
        }
        visited.add(Pair(row, column))
        val result = checkAllEdges(row, column + 1, wordIndex + 1, board, word) ||
                     checkAllEdges(row + 1, column, wordIndex + 1, board, word) ||
                     checkAllEdges(row, column - 1, wordIndex + 1, board, word) ||
                     checkAllEdges(row - 1, column, wordIndex + 1, board, word)
        visited.remove(Pair(row, column))

        return result
    }
}