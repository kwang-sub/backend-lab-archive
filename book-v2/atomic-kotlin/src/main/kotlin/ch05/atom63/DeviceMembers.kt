package ch05.atom63

interface Device {
    val model: String
    val productionYear: Int
}

fun Device.overpriced() = model.startsWith("i")
fun Device.outdated() = productionYear < 2050

class MyDevice(
    override val model: String,
    override val productionYear: Int
) : Device

fun main() {
    val gadget: Device = MyDevice("my first phone", 2000)
    println(gadget.outdated())
    println(gadget.overpriced())
}