package arrays_and_hashing

fun main() {
    val array = intArrayOf(10, 20, 30)
    println(array.size)
    println(array.contentToString())

    //dynamic arrays
    //vector grows by 100 %, methods in it are synchronized
    //arraylist grows by 50%
    val list = mutableListOf<Int>()
    list.add(10)
    list.add(20)
    list.add(30)
    println(list)
}