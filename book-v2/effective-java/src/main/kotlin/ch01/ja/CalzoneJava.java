package ch01.ja;

public class CalzoneJava extends PizzaJava {
    private final boolean sauceInside;

    public static class Builder extends PizzaJava.Builder<Builder> {
        private boolean sauceInside = false;

        public Builder sauceInside() {
            this.sauceInside = true;
            return this;
        }

        @Override
        public PizzaJava build() {
            return new CalzoneJava(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public CalzoneJava(Builder builder) {
        super(builder);
        this.sauceInside = builder.sauceInside;
    }

    @Override
    public String toString() {
        return super.toppings.toString() + " " + sauceInside;
    }
}
