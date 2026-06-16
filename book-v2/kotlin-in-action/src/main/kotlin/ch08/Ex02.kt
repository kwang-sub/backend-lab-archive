package ch08


fun main() {
    println(Delivery.STANDARD.code)
    val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
    println("${calculator(Order(3))}")

    val contacts = listOf(
        Person("kwang", "sub", "123-1521"),
        Person("kwang2", "sub2", null)
    )
    val contactListFilters = ContactListFilters()
    with(contactListFilters) {
        prefix = "k"
        onlyWithPhoneNumber = true
    }

    println(contacts.filter(contactListFilters.getPredicate()))

    val log = listOf(
        SiteVisit("/", 34.0, OS.WINDOWS),
        SiteVisit("/", 14.2, OS.MAC),
        SiteVisit("/login", 54.2, OS.WINDOWS),
        SiteVisit("/signup", 14.0, OS.LINUX),
        SiteVisit("/", 22.2, OS.ANDROID),
    )

    val averageWindowsDuration = log.filter { it.os == OS.WINDOWS }.map { it.duration }.average()

    println(averageWindowsDuration)

    println(log.averageDurationFor(OS.MAC))

    println(log.averageDurationForV2({os -> os.os in setOf(OS.ANDROID) && os.path == "/"}))
}



