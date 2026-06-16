package edu.pattern.decorator;

public abstract class Beverage {

    public enum Size {TALL, GRANDE, VENTI};
    Size size = Size.TALL;
    protected String description = "제목 없음";

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
