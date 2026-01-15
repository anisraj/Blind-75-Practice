package graph

import java.util.Stack

fun main() {
    val graph = Graph()
    graph.addNode("P")
    graph.addNode("X")
    graph.addNode("A")
    graph.addNode("B")

    graph.addEdge("X", "A")
    graph.addEdge("X", "B")
    graph.addEdge("A", "P")
    graph.addEdge("B", "P")

    println(graph.topologicalSort())


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

    fun topologicalSort(): List<String> {
        val list = mutableListOf<String>()
        val visitedNodes = mutableSetOf<Node>()
        val stack = Stack<Node>()
        for (node in nodes.values) {
            topologicalSort(node, stack, visitedNodes)
        }
        while (stack.isNotEmpty()) {
            list.add(stack.pop().label)
        }
        return list
    }

    private fun topologicalSort(node: Node, stack: Stack<Node>, visitedNodes: MutableSet<Node>) {
        if (visitedNodes.contains(node)) {
            return
        }
        visitedNodes.add(node)
        adjacencyList.getValue(node).forEach {
            if (!visitedNodes.contains(it)) {
                topologicalSort(it, stack, visitedNodes)
            }
        }
        stack.push(node)
    }

    fun hasCycle(): Boolean {
        val all = mutableSetOf<Node>()
        all.addAll(nodes.values)

        val visitingNodes = mutableSetOf<Node>()
        val visitedNodes = mutableSetOf<Node>()

        while (all.isNotEmpty()) {
            val current = all.iterator().next()
            if (hasCycle(current, all, visitingNodes, visitedNodes)) {
                return true
            }
        }
        return false
    }

    private fun hasCycle(node: Node, allNodes: MutableSet<Node>, visitingNodes: MutableSet<Node>, visitedNodes: MutableSet<Node>): Boolean {
        allNodes.remove(node)
        visitedNodes.add(node)
        for (neighbour in adjacencyList.getValue(node)) {
            if (visitedNodes.contains(neighbour)) {
                continue
            }
            if (visitingNodes.contains(neighbour)) {
                return true
            }
            if (hasCycle(neighbour, allNodes, visitingNodes, visitedNodes)) {
                return true
            }
        }

        visitingNodes.remove(node)
        visitedNodes.add(node)
        return false
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