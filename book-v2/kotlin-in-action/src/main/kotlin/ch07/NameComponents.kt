package ch07

data class NameComponents(
    val name: String,
    val extension: String,
) {

}

fun splitFileName(fullName: String): NameComponents {
    val (name, ext) = fullName.split('.', limit = 2)
    return NameComponents(name, ext)
}