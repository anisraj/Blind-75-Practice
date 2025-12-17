package binary_tree

class SubtreeOfAnotherTree {

    fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
        if (subRoot == null) {
            return true
        }
        if (root == null) {
            return false
        }
        return isSameTree(root, subRoot) ||
                isSubtree(root.left, subRoot) ||
                isSubtree(root.right, subRoot)
    }

    private fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) {
            return true
        }
        return p?.`val` == q?.`val` &&
                isSameTree(p?.left, q?.left) &&
                isSameTree(p?.right, q?.right)
    }
}

