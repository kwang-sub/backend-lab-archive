package ch06

class MyService {
    fun performAction(): String = "foo"
}

class MyTest {
    private lateinit var  myService: MyService


    fun setUp() {
        myService = MyService()
    }
}