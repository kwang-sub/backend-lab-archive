package ch05.atom63

class Features {
    fun f1() = "feature1"
    fun f2() = "feature2"
}

class Form {
    private val features = Features()
    fun operation1() = features.f2() + features.f1()
    fun operation2() = features.f1() + features.f2()
}