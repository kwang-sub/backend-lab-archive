package modernjavainaction.study.ch09

import java.util.function.Consumer


class OnlineBanking {
    fun processCustomer(id: Int, makeCustomerHappy: Consumer<Customer>) {
        val c = Database.getCustomerWithId(id)
        makeCustomerHappy.accept(c)
    }

//    abstract fun makeCustomerHappy(c: Customer)
}


// 더미 Customer 클래스
class Customer(
    var name: String = "kwnag"
)


// 더미 Database 클래스
object Database {
    fun getCustomerWithId(id: Int): Customer {
        return Customer()
    }
}


fun main() {
    OnlineBanking().processCustomer(2) { println("Hello ${it.name}")}
}