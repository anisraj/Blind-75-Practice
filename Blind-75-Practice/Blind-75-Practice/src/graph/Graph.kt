package graph

import queues.ArrayQueue
import java.util.Stack

fun main() {
    val graph = Graph()
    graph.addNode("A")
    graph.addNode("B")
    graph.addNode("C")
    graph.addNode("D")
    graph.addNode("E")

    graph.addEdge("C", "A")
    graph.addEdge("C", "B")
    graph.addEdge("C", "D")
    graph.addEdge("A", "B")
    graph.addEdge("B", "E")
    graph.addEdge("A", "E")

    graph.traverseBreadthFirst("C")


}

class Graph {
    private val adjacencyList = mutableMapOf<Node, MutableList<Node>>()
    private val nodes = mutableMapOf<String, Node>()

    fun addNode(label: String) {
        val node = Node(label)
        nodes.putIfAbsent(label, node)
        adjacencyList.putIfAbsent(node, mutableListOf())
    }

    fun removeNode(label: String) {
        val node = nodes[label]
        node?.let {
            for (entry in adjacencyList) {
                entry.value.remove(node)
            }
            nodes.remove(label)
            adjacencyList.remove(node)
        }
    }

    fun addEdge(from: String, to: String) {
        val fromNode = nodes[from]
        val toNode = nodes[to]
        if (fromNode == null || toNode == null) {
            throw IllegalStateException()
        }

        adjacencyList.getValue(fromNode).add(toNode)

    }

    fun removeEdge(from: String, to: String) {
        val fromNode = nodes[from]
        val toNode = nodes[to]
        if (fromNode == null || toNode == null) {
            throw IllegalStateException()
        }

        adjacencyList.getValue(fromNode).remove(toNode)
        
    }

    fun traverseDepthFirst(from: String) {
        val node = nodes[from]
        node?.let {
            traverseDepthFirst(it, mutableSetOf())
        }
    }

    private fun traverseDepthFirst(node: Node, visitedNodes: MutableSet<Node>) {
        print("$node ")
        visitedNodes.add(node)
        adjacencyList.getValue(node).forEach {
            if (!visitedNodes.contains(it)) {
                traverseDepthFirst(it, visitedNodes)
            }
        }
    }

    fun traverseDepthFirstIteratively(from: String) {
        val node = nodes[from]
        node?.let { fromNode ->
            val stack = Stack<Node>()
            val visitedNodes = mutableSetOf<Node>()

            stack.push(fromNode)
            while (stack.isNotEmpty()) {
                val current = stack.pop()

                if (visitedNodes.contains(current)) {
                    continue
                }

                print("$current ")
                visitedNodes.add(current)

                adjacencyList.getValue(current).forEach {
                    if (!visitedNodes.contains(it)) {
                        stack.push(it)
                    }
                }
            }
        }
    }

    fun traverseBreadthFirst(from: String) {
        val node = nodes[from]
        node?.let { fromNode ->
            val queue = ArrayDeque<Node>()
            val visitedNodes = mutableSetOf<Node>()

            queue.add(node)
            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()
                if (visitedNodes.contains(current)) {
                    continue
                }
                print("$current ")
                visitedNodes.add(current)
                adjacencyList.getValue(current).forEach {
                    if (!visitedNodes.contains(it)) {
                        queue.add(it)
                    }
                }
            }
        }
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        for (entry in adjacencyList) {
            stringBuilder.append("${entry.key} is connected to ${entry.value}\n")
        }
        return stringBuilder.toString()
    }

    private data class Node(val label: String) {
        override fun toString(): String {
            return label
        }
    }
}