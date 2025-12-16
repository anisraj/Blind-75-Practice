package binary_tree

fun main() {

}

fun invertTreeBreadthFirst(root: TreeNode?): TreeNode? {
    val queue = ArrayDeque<TreeNode?>()
    queue.add(root)
    while (queue.isNotEmpty()) {
        val node = queue.removeFirstOrNull()
        node?.let {
            val temp = it.right
            it.right = it.left
            it.left = temp

            queue.add(it.right)
            queue.add(it.left)
        }
    }
    return root
}

fun invertTreeDepthFirstSearch(root: TreeNode?): TreeNode? {
    root?.let {
        val temp = it.right
        it.right = it.left
        it.left = temp

        invertTreeDepthFirstSearch(it.right)
        invertTreeDepthFirstSearch(it.left)
    }
    return root
}