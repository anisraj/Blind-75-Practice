package binary_tree

import kotlin.math.max

fun main() {

}

fun maxDepth(root: TreeNode?): Int {
    root?.let {
        return 1 + max(maxDepth(it.left), maxDepth(it.right))
    } ?: run {
        return 0
    }
}