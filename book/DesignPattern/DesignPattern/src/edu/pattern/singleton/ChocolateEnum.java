package edu.pattern.singleton;

public enum ChocolateEnum {
    INSTANCE(true, false);
    private boolean empty;
    private boolean boiled;

    ChocolateEnum(boolean empty, boolean boiled) {
        this.empty = empty;
        this.boiled = boiled;
    }

    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
        }
    }

    public void drain() {
        if (!isEmpty() && isBoiled()) {
            empty = true;
        }
    }

    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            boiled = true;
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
