package ch01.ja;

import java.util.Objects;

public class NyPizzaJavaJava extends PizzaJava {
    public enum Size {SMALL, MEDIUM, LARGE}

    public final Size size;

    public static class Builder extends PizzaJava.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public PizzaJava build() {
            return new NyPizzaJavaJava(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public NyPizzaJavaJava(Builder builder) {
        super(builder);
        size = builder.size;
    }

    @Override
    public String toString() {
        return toppings.toString() + " " + this.size;
    }
}
