package arrays_and_hashing

fun main() {
    val array = intArrayOf(0,1,2,4,5,6,7)
    rotateArrayOptimal(array, 4)
    println(array.contentToString())

}

private fun rotateArrayOptimal(array: IntArray, places: Int) {
    val rotations = places % array.size
    reverseArray(array, 0, array.size - rotations - 1)
    reverseArray(array, array.size - rotations, array.size - 1)
    reverseArray(array, 0, array.size - 1)
}

private fun reverseArray(array: IntArray, low: Int, high: Int) {
    var i = low
    var j = high
    while (i <= j) {
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
        i++
        j--
    }
}

private fun rotateArrayBruteForce(array: IntArray, places: Int) {
    val rotations = places % array.size
    val temp = IntArray(rotations) { 0 }
    var countForTemp = rotations - 1
    for (i in array.size - 1 downTo array.size - rotations) {
        temp[countForTemp--] = array[i]
    }
    //shifting
    var shiftIndex = array.size - 1
    for (i in array.size - rotations - 1 downTo 0) {
        array[shiftIndex--] = array[i]
    }
    for (i in temp.indices) {
        array[i] = temp[i]
    }
}