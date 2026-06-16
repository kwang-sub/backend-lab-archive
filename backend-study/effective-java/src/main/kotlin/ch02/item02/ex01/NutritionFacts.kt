package ch02.item02.ex01

class NutritionFacts private constructor(
    private val _servingSize: Int,
    private val _servings: Int,
    private val _calories: Int? = null,
    private val _fat: Int? = null,
    private val _sodium: Int? = null,
    private val _carbohydrate: Int? = null,
) {
    val servingSize: Int
        get() = _servingSize
    val servings: Int
        get() = _servings
    val calories: Int?
        get() = _calories
    val fat: Int?
        get() = _fat
    val sodium: Int?
        get() = _sodium
    val carbohydrate: Int?
        get() = _carbohydrate

    constructor(builder: Builder) : this(
        _servingSize = builder.servingSize,
        _servings = builder.servings,
        _calories = builder.calories,
        _fat = builder.fat,
        _sodium = builder.sodium,
        _carbohydrate = builder.carbohydrate,
    )

    class Builder(
        val servingSize: Int,
        val servings: Int,
    ) {
        var calories: Int = 0
        var fat: Int = 0
        var sodium: Int = 0
        var carbohydrate: Int = 0
        fun calories(value: Int): Builder {
            this.calories = value
            return this
        }

        fun fat(value: Int): Builder {
            this.fat = value
            return this
        }

        fun sodium(value: Int): Builder {
            this.sodium = value
            return this
        }

        fun carbohydrate(value: Int): Builder {
            this.carbohydrate = value
            return this
        }

        fun build(): NutritionFacts = NutritionFacts(this)
    }
}