package bai2;

public class Main {
    public static void main(String[] args) {
        System.out.println(sumNumberSequence(8545604));
    }

    public static int sumNumberSequence(long number) {
        int result = 0;
        String numberStr = number + "";
        for(String str : numberStr.split("")) {
            result += Long.parseLong(str);
        }
        return result;
    }
}
