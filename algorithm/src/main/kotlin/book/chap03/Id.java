package book.chap03;

public class Id {
    private static int counter = 0;
    private int id;

    public Id() {
        this.id = ++counter;
    }

    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

}

