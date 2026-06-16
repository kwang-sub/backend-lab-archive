package ch08

data class SiteVisit(
    val path: String,
    val duration: Double,
    val os: OS,
)

enum class OS {
    WINDOWS, LINUX, MAC, IOS, ANDROID
}


fun List<SiteVisit>.averageDurationFor(os: OS) =
    this.filter { it.os == os }
        .map { it.duration }
        .average()

fun List<SiteVisit>.averageDurationForV2(predicate: (SiteVisit) -> Boolean) =
    this.filter(predicate)
        .map { it.duration }
        .average()
