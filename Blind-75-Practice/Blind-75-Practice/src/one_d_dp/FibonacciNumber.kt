package one_d_dp

fun main() {
    val fibonacciNumber = FibonacciNumber()
    val number = 10
    for (i in 0..number) {
        println(fibonacciNumber.getFibonacci(i))
    }
    println()
}

class FibonacciNumber {
    lateinit var dp: IntArray

    fun getFibonacci(n: Int): Int{
        dp = IntArray(n + 1) { -1 }
        return fib(n)
    }

    private fun fib(n: Int): Int {
        if (dp[n] != -1) {
            return dp[n]
        }
        if (n <= 1) {
            dp[n] = n
            return n
        }
        dp[n] = fib(n-1) + fib(n-2)
        return dp[n]
    }
}

