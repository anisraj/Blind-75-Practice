package backtracking

fun main() {
    val sol = Solution()
    val result = sol.combinationSum(
        intArrayOf(2,3,6,7),
        7
    )
    println(result)
}

class Solution {
    private val result = mutableListOf<MutableList<Int>>()

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        findCombination(
            0,
            target,
            mutableListOf(),
            candidates
        )
        return result
    }

    private fun findCombination(
        index: Int,
        total: Int,
        innerList: MutableList<Int>,
        candidates: IntArray
    ) {
        if (total == 0) {
            result.add(ArrayList(innerList))
            return
        }
        if (index == candidates.size) {
            return
        }
        //pick up
        if (candidates[index] <= total) {
            innerList.add(candidates[index])
            findCombination(
                index,
                total - candidates[index],
                innerList,
                candidates
            )
            innerList.removeLast()
        }
        //not pick up
        findCombination(
            index + 1,
            total,
            innerList,
            candidates
        )
    }
}



