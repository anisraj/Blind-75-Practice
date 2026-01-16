package undirected_graphs

fun main() {
    val graph = WeightedGraph()
    graph.addNode("A")
    graph.addNode("B")
    graph.addNode("C")
    graph.addNode("D")
    graph.addNode("E")

    graph.addEdge("A", "B", 3)
    graph.addEdge("A", "C", 4)
    graph.addEdge("A", "D", 2)
    graph.addEdge("C", "D", 1)
    graph.addEdge("B", "D", 6)
    graph.addEdge("B", "E", 1)
    graph.addEdge("D", "E", 5)

    graph.print()


}

class WeightedGraph {
    private val nodes = mutableMapOf<String, Node>()

    fun addNode(label: String) {
        nodes.putIfAbsent(label, Node(label))
    }

    fun addEdge(from: String, to: String, weight: Int) {
        val fromNode = nodes[from]
        val toNode = nodes[to]
        if (fromNode == null || toNode == null) {
            throw IllegalStateException()
        }
        fromNode.addEdge(toNode, weight)
        toNode.addEdge(fromNode, weight)
    }

    fun print() {
        for (node in nodes.values) {
            val edges = node.edges
            println("$node is connected to $edges")
        }
    }

    private data class Node(val label: String) {
        val edges = mutableListOf<Edge>()

        fun addEdge(toNode: Node, weight: Int) {
            edges.add(Edge(this, toNode, weight))
        }

        override fun toString(): String {
            return label
        }
    }

    private data class Edge(
        val from: Node,
        val to: Node,
        val weight: Int
    ) {
        override fun toString(): String {
            return "$from -> $to"
        }
    }
}