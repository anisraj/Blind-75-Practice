package graph

fun main() {
    val solution = Solution()
    val grid = arrayOf(
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '1', '0', '0'),
        charArrayOf('0', '0', '0', '1', '1'),
    )
    println(solution.numOfIslands(grid))
}

class Solution {
    fun numOfIslands(grid: Array<CharArray>): Int {
        var result = 0
        val rows = grid.size
        val columns = grid.first().size
        for (row in 0..<rows) {
            for (column in 0..<columns) {
                if (grid[row][column] == '1') {
                    result++
                    dfs(row, column, grid)
                }
            }
        }
        return result
    }

    private fun dfs(row: Int, column: Int, grid: Array<CharArray>) {
        if (row < 0 ||
            column < 0 ||
            row >= grid.size ||
            column >= grid.first().size ||
            grid[row][column] == '0') {
            return
        }
        grid[row][column] = '0'
        dfs(row + 1, column, grid)
        dfs(row - 1, column, grid)
        dfs(row, column + 1, grid)
        dfs(row, column - 1, grid)
    }
}