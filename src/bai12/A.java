package bai12;

public class A {
    private int id;
    private String name;

    public A(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println(getClass().getSimpleName());
    }
}
