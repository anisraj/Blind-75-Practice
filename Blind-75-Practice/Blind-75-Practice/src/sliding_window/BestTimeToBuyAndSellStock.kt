package sliding_window

import kotlin.math.max
import kotlin.math.min

fun main() {
    println(maxProfitOptimal(intArrayOf(2,4,1)))
}

// Time O(n), Space O(1) , this is DP solution
fun maxProfitOptimal(prices: IntArray): Int {
    var maxProfit = 0
    var minStock = prices.first()
    for (i in 1..prices.lastIndex) {
        if (prices[i] > minStock) {
            maxProfit = max(maxProfit, (prices[i] - minStock))
        }
        minStock = min(minStock, prices[i])
    }
    return maxProfit
}

// Time O(n), Space O(1)
fun maxProfitBetter(prices: IntArray): Int {
    var maxProfit = 0
    var i = 0
    var j = 0
    while (
        i <= j &&
        i <= prices.lastIndex &&
        j <= prices.lastIndex
    ) {
        if (i == j) {
            j++
        } else if (prices[i] > prices[j]) {
            i++
        } else {
            maxProfit = max(maxProfit, (prices[j] - prices[i]))
            j++
        }
    }
    return maxProfit
}

// Time O(n^2), Space O(1)
fun maxProfitBruteForce(prices: IntArray): Int {
    var maxProfit = 0
    for (i in 0..prices.lastIndex) {
        val buy = prices[i]
        for (j in i + 1..prices.lastIndex) {
            val sell = prices[j]
            if (sell > buy) {
                if (sell > maxProfit) {
                    maxProfit = sell
                }
            }
        }
    }

    return maxProfit
}