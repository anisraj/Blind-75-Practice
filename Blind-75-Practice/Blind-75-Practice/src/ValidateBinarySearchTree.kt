import binary_tree.TreeNode

class ValidateBinarySearchTree {
    fun isValidBST(root: TreeNode?): Boolean {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    private fun isValidBST(node: TreeNode?, min: Long, max: Long): Boolean {
        node?.let {
            if (it.`val` <= min || it.`val` >= max) {
                return false
            }
            return isValidBST(it.left, min = min, max = it.`val`.toLong()) &&
                    isValidBST(it.right, min = it.`val`.toLong(), max = max)
        } ?: run {
            return true
        }
    }
}