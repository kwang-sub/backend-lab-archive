package ch01.ja;

public class NutritionFactsJava {
    private final int servingService;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;


    public static class Builder {
        private final int servingService;
        private final int servings;

        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;
        public Builder(int servingService, int servings) {
            this.servingService = servingService;
            this.servings = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFactsJava build() {
            return new NutritionFactsJava(
                    servingService,
                    servings,
                    calories,
                    fat,
                    sodium,
                    carbohydrate
            );
        }

    }


    private NutritionFactsJava(int servingService, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingService = servingService;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    @Override
    public String toString() {
        return "NutritionFactsJava{" +
                "servingService=" + servingService +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }
}
