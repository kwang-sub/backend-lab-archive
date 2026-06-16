package ch01.ko

data class NutritionFacts(
    var servingSize: Int = -1,
    var servings: Int = -1,
    var calories: Int = 0,
    var fat: Int = 0,
    var sodium: Int = 0,
    var carbohydrate: Int = 0,
) {

}