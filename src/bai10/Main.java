package bai10;

public class Main {
    public static void main(String[] args) {
        listWhenTotalEquals(32);
    }

    private static boolean isPrime(int number) {
        int count = 0;
        for(int i = 1; i <= number; i++) {
            if(count > 2)
                return false;
            if(number % i == 0)
                count ++;
        }
        return true;
    }

    private static int sumNumber(int number) {
        int result = 0;
        while(number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }

    private static void listWhenTotalEquals(int total) {
        for(int i = 10000; i < 99999; i++) {
            if(isPrime(i) && sumNumber(i) == total) {
                System.out.println(i + " = " + sumNumber(i));
            }
        }
    }
}
