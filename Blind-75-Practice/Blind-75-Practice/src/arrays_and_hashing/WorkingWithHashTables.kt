package arrays_and_hashing

fun main() {
    val map = mutableMapOf<Int, String>()
    map[1] = "aqib"
    map[2] = "anis"
    map[3] = "arshiya"
    map[4] = "jamadar"
    map.remove(4)

    println(map)

    println(map.containsKey(3)) // O(1)
    println(map.containsValue("anis")) // O(n)
}