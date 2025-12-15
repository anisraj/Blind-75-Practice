package binary_tree

import kotlin.math.max

fun main() {
    val tree = Tree()
    tree.insert(7)
    tree.insert(4)
    tree.insert(9)
    tree.insert(1)
    tree.insert(6)
    tree.insert(8)
    tree.insert(10)
    tree.traverseLevelOrder()

}

class Tree {
    private var root: Node? = null

    fun insert(value: Int) {
        val node = Node(value)
        root?.let { rootNode ->
            var current = rootNode
            while (true) {
                if (value < current.value) {
                    if (current.leftChild == null) {
                        current.leftChild = node
                        break
                    }
                    current = current.leftChild!!
                } else {
                    if (current.rightChild == null) {
                        current.rightChild = node
                        break
                    }
                    current = current.rightChild!!
                }
            }
        } ?: run {
            root = node
        }
    }

    fun find(value: Int): Boolean {
        var current = root
        while (current != null) {
            if (value < current.value) {
                current = current.leftChild
            } else if (value > current.value) {
                current = current.rightChild
            } else {
                return true
            }
        }
        return false
    }

    fun traversePreOrder() {
        traversePreOrder(root)
    }

    private fun traversePreOrder(root: Node?) {
        root?.let {
            print("${it.value} ")
            traversePreOrder(it.leftChild)
            traversePreOrder(it.rightChild)
        }
    }

    fun traverseInOrder() {
        traverseInOrder(root)
    }

    private fun traverseInOrder(root: Node?) {
        root?.let {
            traverseInOrder(root.leftChild)
            print("${it.value} ")
            traverseInOrder(it.rightChild)
        }
    }

    fun traversePostOrder() {
        traversePostOrder(root)
    }

    private fun traversePostOrder(root: Node?) {
        root?.let {
            traversePostOrder(root.leftChild)
            traversePostOrder(it.rightChild)
            print("${it.value} ")
        }
    }

    fun height(): Int {
        return height(root)
    }

    private fun height(root: Node?): Int {
        root?.let {
            return 1 + max(height(it.leftChild), height(it.rightChild))
        } ?: run {
            return -1
        }
    }

    fun min(): Int {
        var current = root
        while (current?.leftChild != null) {
            current = current.leftChild
        }
        return current?.value ?: throw IllegalStateException()

        //if tree is not binary search tree then we need to use
        //following algorithm
        return min(root)
    }

    private fun min(root: Node?): Int {
        root?.let {
            if (isLeaf(it)) {
                return it.value
            }
            val left = min(it.leftChild)
            val right = min(it.rightChild)
            return kotlin.math.min(it.value, kotlin.math.min(left, right))
        }
        return Int.MAX_VALUE
    }

    fun equals(otherTree: Tree): Boolean {
        return equals(root, otherTree.root)
    }

    private fun equals(first: Node?, second: Node?): Boolean {
        if (first == null && second == null) {
            return true
        }
        return first?.value == second?.value &&
                equals(first?.leftChild, second?.leftChild) &&
                equals(first?.rightChild, second?.rightChild)
    }

    fun isBinarySearchTree(): Boolean {
        return isBinarySearchTree(root, Int.MIN_VALUE, Int.MAX_VALUE)
    }

    private fun isBinarySearchTree(node: Node?, min: Int, max: Int): Boolean {
        node?.let {
            if (it.value < min || it.value > max) {
                return false
            }
            return isBinarySearchTree(it.leftChild, min = min, max = it.value - 1) &&
                    isBinarySearchTree(it.rightChild, min = it.value + 1, max = max)
        } ?: run {
            return true
        }
    }

    fun printNodesAtDistance(k :Int) {
        printNodesAtDistance(root, k)
    }

    private fun printNodesAtDistance(node: Node?, k :Int) {
        node?.let {
            if (k == 0) {
                print("${it.value} ")
            } else {
                printNodesAtDistance(node.leftChild, k = k - 1)
                printNodesAtDistance(node.rightChild, k = k - 1)
            }
        }
    }

    private fun isLeaf(node: Node?): Boolean {
        return node?.leftChild == null && node?.rightChild == null
    }

    fun traverseLevelOrder() {
        for (i in 0..height()) {
            printNodesAtDistance(i)
        }
    }

    private data class Node(
        val value: Int
    ) {
        var leftChild: Node? = null
        var rightChild: Node? = null
    }
}