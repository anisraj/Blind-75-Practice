package binary_tree

class BinaryTreeMaximumPathSum {
    private var result = Int.MIN_VALUE

    fun maxPathSum(root: TreeNode?): Int {
        dfs(root)
        return result
    }

    private fun dfs(root: TreeNode?) {
        root?.let {
            val left = getMax(root.left)
            val right = getMax(root.right)
            result = maxOf(result, root.`val` + left + right)

            dfs(root.left)
            dfs(root.right)
        }
    }

    private fun getMax(root: TreeNode?): Int {
        root?.let {
            val left = getMax(root.left)
            val right = getMax(root.right)
            val path = root.`val` + maxOf(left, right)
            return maxOf(0, path)
        } ?: run {
            return 0
        }
    }
}