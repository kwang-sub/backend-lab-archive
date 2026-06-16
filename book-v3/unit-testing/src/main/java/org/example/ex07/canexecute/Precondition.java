package org.example.ex07.canexecute;

public class Precondition {
    public static void requires(boolean precondition, String message) {
        if (!precondition) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void requires(boolean precondition) {
        requires(precondition, null);
    }
}
