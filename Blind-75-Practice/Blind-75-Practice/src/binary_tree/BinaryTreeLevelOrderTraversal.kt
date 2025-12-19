package binary_tree

import kotlin.math.max

private class BinaryTreeLevelOrderTraversal {

    private fun levelOrderOptimal(root: TreeNode?): List<List<Int>> {
        val resultList = mutableListOf<MutableList<Int>>()
        val queue = ArrayDeque<TreeNode>()
        root?.let {
            queue.addFirst(it)
        }

        while (queue.isNotEmpty()) {
            val length = queue.size
            val list = mutableListOf<Int>()
            for (i in 0..<length) {
                val node = queue.removeFirst()
                if (node.left != null) {
                    queue.addLast(node.left!!)
                }
                if (node.right != null) {
                    queue.addLast(node.right!!)
                }
                list.add(node.`val`)
            }
            resultList.add(list)
        }

        return resultList
    }

    private fun levelOrder(root: TreeNode?): List<List<Int>> {
        val resultList = mutableListOf<MutableList<Int>>()
        for (i in 0..getHeight(root)) {
            val list = mutableListOf<Int>()
            getNodesAtKDistance(root, i, list)
            if (list.isNotEmpty()) {
                resultList.add(list)
            }
        }
        return resultList
    }

    private fun getNodesAtKDistance(root: TreeNode?, k: Int, list: MutableList<Int>) {
        root?.let {
            if (k == 0) {
                list.add(it.`val`)
            } else {
                getNodesAtKDistance(root.left, k - 1, list)
                getNodesAtKDistance(root.right, k - 1, list)
            }
        }
    }

    private fun getHeight(root: TreeNode?): Int {
        root?.let {
            return 1 + max(getHeight(root.left), getHeight(root.right))
        } ?: run {
            return -1
        }
    }
}