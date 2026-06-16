package ko.ch02

fun main() {
    primeNumber3()
}

fun primeNumber1() {
    var counter = 0
    for (n in 2..1000) {
        var i = 2
        while (i < n) {
            counter++
            if (n % i == 0) break
            i++
        }
        if (n == i)
            println(n)
    }

    println("나눗셈을 수행한 횟수: $counter")
}

fun primeNumber2() {
    var counter = 0
    var ptr = 0
    val prime = IntArray(500)

    prime[ptr++] = 2
    for (n in 3..1000 step 2) {
        var i = 1
        while (i < ptr) {
            counter++
            if (n % prime[i] == 0) break
            i++
        }
        if (ptr == i) {
            println(n)
            prime[ptr++] = n
        }
    }


    println("나눗셈을 수행한 횟수: $counter")
}

fun primeNumber3() {
    var counter = 0
    var ptr = 0
    val prime = IntArray(500)

    prime[ptr++] = 2
    prime[ptr++] = 3
    for (n in 5..1000 step 2) {
        var i = 1
        var flag = false
        while (prime[i] * prime[i] <= n) {
            counter += 2
            if (n % prime[i] == 0) {
                flag = true
                break
            }
            i++
        }

        if (!flag) {
            prime[ptr++] = n
            counter++
        }
    }

    for (i in 0..ptr) {
        println(prime[i])
    }

    println("나눗셈을 수행한 횟수: $counter")
}