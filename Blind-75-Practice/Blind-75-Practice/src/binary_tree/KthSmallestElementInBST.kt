package binary_tree

class KthSmallestElementInBSTOptimal {
    var count = 0
    private var result = 0
    private fun kthSmallest(root: TreeNode?, k: Int): Int {
        traverseTreeInOrder(root, k)
        return result
    }

    private fun traverseTreeInOrder(node: TreeNode?, k: Int) {
        node?.let {
            traverseTreeInOrder(it.left, k)
            count += 1
            if (count == k) {
                result = it.`val`
                return
            }
            traverseTreeInOrder(it.right, k)
        }
    }
}

class KthSmallestElementInBST {
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        val list = mutableListOf<Int>()
        traverseTreeInOrder(root, list)
        return list[k - 1]
    }

    private fun traverseTreeInOrder(node: TreeNode?, list: MutableList<Int>) {
        node?.let {
            traverseTreeInOrder(it.left, list)
            list.add(it.`val`)
            traverseTreeInOrder(it.right, list)
        }
    }
}