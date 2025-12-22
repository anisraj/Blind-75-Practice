package binary_tree

class ConstructBinaryTree {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        val map = mutableMapOf<Int, Int>()
        inorder.forEachIndexed { index, item ->
            map[item] = index
        }
        val root = buildTree(
            preorder, 0, preorder.lastIndex,
            inorder, 0, inorder.lastIndex,
            map
        )
        return root
    }

    private fun buildTree(
        preorder: IntArray, preStart: Int, preEnd: Int,
        inorder: IntArray, inStart: Int, inEnd: Int,
        map: Map<Int, Int>
    ): TreeNode? {
        if (preStart > preEnd || inStart > inEnd) {
            return null
        }
        val root = TreeNode(preorder[preStart])

        val inRoot = map.getValue(root.`val`)
        val numsLeft = inRoot - inStart

        root.left = buildTree(
            preorder, preStart + 1, preStart + numsLeft,
            inorder, inStart, inRoot - 1,
            map
        )

        root.right = buildTree(
            preorder, preStart + numsLeft + 1, preEnd,
            inorder, inRoot + 1, inEnd,
            map
        )

        return root
    }
}