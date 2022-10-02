package bai11;

public class Main {
    public static void main(String[] args) {
        listFibonaci(100);
    }

    private static boolean isPrime(int number) {
        if(number < 2)
            return false;
        int count = 0;
        for(int i = 1; i <= number; i++) {
            if(count > 2)
                return false;
            if(number % i == 0)
                count ++;
        }
        return true;
    }

    private static void listFibonaci(int n) {
        int f0 = 0;
        int f1 = 1;
        int f;
        for(int i = 0; i < n; i++) {
            f = f0 + f1;
            if(isPrime(f) && f < n)
                System.out.println(f);
            f0 = f1;
            f1 = f;
        }
    }
}
