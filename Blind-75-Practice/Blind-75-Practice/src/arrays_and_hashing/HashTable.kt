package arrays_and_hashing

import java.util.LinkedList
import kotlin.math.abs

fun main() {
    val hashTable = HashTable()
    hashTable.put(6, "A")
    hashTable.put(8, "B")
    hashTable.put(1, "C")
    hashTable.put(8, "B+")
    hashTable.remove(18)
    println()
}

private class HashTable {
    private val entries = Array<LinkedList<Entry>?>(5) { null }

    fun put(key: Int, value: String) {
        val index = hash(key)
        if (entries[index] == null) {
            entries[index] = LinkedList()
        }
        val bucket = entries[index]
        for (entry in bucket!!) {
            if (entry.key == key) {
                entry.value = value
                return
            }
        }
        bucket.addLast(Entry(key, value))
    }

    fun get(key: Int): String? {
        val index = hash(key)
        val bucket = entries[index]
        bucket?.let {
            for (entry in it) {
                if (entry.key == key) {
                    return entry.value
                }
            }
        }
        return null
    }

    fun remove(key: Int) {
        val index = hash(key)
        val bucket = entries[index]
        bucket?.let {
            for (entry in it) {
                if (entry.key == key) {
                    bucket.remove(entry)
                    return
                }
            }
        }
        throw IllegalStateException()
    }

    private fun hash(key: Int): Int {
        return abs(key) % entries.size
    }

    private data class Entry(
        val key: Int,
        var value: String
    )
}
