package ch05.item33

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FavoritesTest {

    @Test
    fun 이중_컨테이너_테스트() {
        // given
        val favorites = Favorites()
        // when
        favorites.putFavorite(String::class, "test")
        favorites.putFavorite(Int::class, 1)
        favorites.putFavorite(Double::class, 1.1)
        val stringResult = favorites.getFavorite(String::class)
        val intResult = favorites.getFavorite(Int::class)
        val longResult = favorites.getFavorite(Double::class)
        //then
        assertThat(stringResult).isEqualTo("test")
        assertThat(intResult).isEqualTo(1)
        assertThat(longResult).isEqualTo(1.1)
    }
}