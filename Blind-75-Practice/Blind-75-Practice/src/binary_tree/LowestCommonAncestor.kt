package binary_tree

import kotlin.math.min

private class Solution {
    fun lowestCommonAncestorOptimal(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) {
            return null
        }
        val maxNode: TreeNode
        val minNode: TreeNode
        if (p.`val` <= q.`val`) {
            maxNode = q
            minNode = p
        } else {
            maxNode = p
            minNode = q
        }

        if (minNode.`val` <= root.`val` && root.`val` <= maxNode.`val`) {
            return root
        } else if (minNode.`val` <= root.`val` && maxNode.`val` <= root.`val`) {
            return lowestCommonAncestorOptimal(root.left, p, q)
        } else {
            return lowestCommonAncestorOptimal(root.right, p, q)
        }
    }

    fun lowestCommonAncestorBruteForce(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        val queue1 = ArrayDeque<TreeNode>()
        val queue2 = ArrayDeque<TreeNode>()
        searchAndPopulate(root, p!!, queue1)
        searchAndPopulate(root, q!!, queue2)
        var lastQueuedElement: TreeNode? = null
        while (queue1.isNotEmpty() && queue2.isNotEmpty()) {
            val r = queue1.removeFirst()
            if (r == queue2.removeFirst()) {
                lastQueuedElement = r
            }
        }
        return lastQueuedElement
    }

    private fun searchAndPopulate(root: TreeNode?, p: TreeNode, queue: ArrayDeque<TreeNode>) {
        root?.let {
            queue.addLast(it)
            if (p.`val` < it.`val`) {
                searchAndPopulate(it.left, p, queue)
            } else if (p.`val` > it.`val`) {
                searchAndPopulate(it.right, p, queue)
            } else {
                return
            }
        }
    }
}