package arrays_and_hashing

import kotlin.math.abs

fun main() {
    val map = HashMap()
    map.put(6, "A")
    map.put(8, "B")
    map.put(1, "C")
    map.put(11, "D")
    map.put(11, "E")
    println(map.size())
}

class HashMap {
    private val entries = Array<Entry?>(5) { null }
    private var count = 0

    fun put(key: Int, value: String) {
        if (entries[hash(key)] != null) {
            entries[hash(key)]!!.value = value
        }
        if (count == entries.size) {
            throw IllegalStateException()
        }
        var steps = 0
        while (steps < entries.size) {
            val index = getIndex(key, steps++)
            val entry = entries[index]
            if (entry == null) {
                entries[index] = Entry(key, value)
                count++
                return
            }
            if (entry.key == key) {
                entry.value = value
                return
            }
        }
        throw IllegalStateException()
    }

    fun get(key: Int): String? {
        var steps = 0
        while (steps < entries.size) {
            val index = getIndex(key, steps++)
            val entry = entries[index]
            if (entry != null && entry.key == key) {
                return entry.value
            }
        }
        return null
    }

    fun remove(key: Int) {
        var steps = 0
        while (steps < entries.size) {
            val index = getIndex(key, steps++)
            val entry = entries[index]
            if (entry != null && entry.key == key) {
                entries[index] = null
                count--
                return
            }
        }
    }

    fun size(): Int {
        return count
    }

    private fun getIndex(key: Int, steps: Int): Int {
        return (hash(key) + steps) % entries.size
    }

    private fun hash(key: Int): Int {
        return abs(key) % entries.size
    }

    private data class Entry(
        val key: Int,
        var value: String
    )
}