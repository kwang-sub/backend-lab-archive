package ch05.item33

import kotlin.reflect.KClass
import kotlin.reflect.cast

class Favorites {
    private val favorites: MutableMap<KClass<*>, Any> = hashMapOf()


    fun <T : Any> putFavorite(type: KClass<T>, instance: T) {
        favorites[type] = type.cast(instance)
    }

    fun <T : Any> getFavorite(type: KClass<T>): T {
        return type.cast(favorites[type])
    }
}

