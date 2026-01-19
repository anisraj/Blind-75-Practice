package undirected_graphs

class Solution {
    private val map = mutableMapOf<Int, Node>()

    fun cloneGraph(node: Node?): Node? {
        node?.let {
            return cloneNode(it)
        }
        return null
    }

    private fun cloneNode(node: Node): Node {
        if (map.containsKey(node.`val`)) {
            return map[node.`val`]!!
        }
        val clonedNode = Node(node.`val`)
        map[node.`val`] = clonedNode
        for (neighbour in node.neighbors) {
            neighbour?.let {
                clonedNode.neighbors.add(cloneNode(it))
            }
        }

        return clonedNode
    }

    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = ArrayList<Node?>()
    }
}