package bai9;

public class Main {
    public static void main(String[] args) {
        System.out.println(countWords("Trường học"));
    }
    private static int countWords(String str) {
        return str.split(" ").length;
    }
}
