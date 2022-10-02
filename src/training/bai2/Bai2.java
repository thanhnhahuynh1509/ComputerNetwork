package training.bai2;

public class Bai2 {
    public static void main(String[] args) {
        System.out.println(sum(123));
    }

    private static int sum(int number) {
        int sum = 0;
        while(number != 0) {
            int n = number % 10;
            sum += n;
            number /= 10;
        }
        return sum;
    }
}
