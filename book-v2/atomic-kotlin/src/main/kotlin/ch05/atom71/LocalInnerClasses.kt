package ch05.atom71

fun interface Pet {
    fun speak(): String
}

object CreatePet {
    fun home() = " home!"
    fun dog(): Pet {
        val say = "Bark"

        class Dog : Pet {
            override fun speak(): String {
                return say + home()
            }
        }
        return Dog()
    }

    fun hamster(): Pet {
        val squeak = "Squeak"
        return Pet { squeak + home() }
    }
}