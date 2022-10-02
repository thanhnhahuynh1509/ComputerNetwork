package tcp;

public class Fibonacci {
    public static int getFi(int n) {
        int f0 = 0, f1= 1, f = 1;
        for(int i = 0; i < n - 1; i++) {
            f = f0 + f1;
            f0 = f1;
            f1 = f;
        }
        return f;
    }

}
