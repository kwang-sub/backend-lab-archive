package edu.pattern.command.light;

public class Light {

    private String name;

    public Light(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(name + "조명이 켜졌습니다.");
    }

    public void off() {
        System.out.println(name + "조명이 꺼졌습니다.");
    }
}
