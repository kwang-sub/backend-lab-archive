package ch01.ja;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class PizzaJava {
    public enum Topping {HAM, MUSHROOM, ONION, PERPPER, SAUSAGE}

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }
        abstract PizzaJava build();
        protected abstract T self();
    }

    public PizzaJava(Builder<?> builder) {
        this.toppings = builder.toppings.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
