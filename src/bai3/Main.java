package bai3;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        System.out.println(analyse(24));
    }

    private static boolean isPrime(int number) {
        int count = 0;
        for(int i = 1; i <= number; i++) {
            if(count > 2)
                break;
            if(number % i == 0)
                count ++;
        }
        return count == 2;
    }

    private static List<Integer> getPrimeNumbersInRange(int length) {
        List<Integer> primeNumbers = new ArrayList<>();
        for(int i = 2; i <= length; i++) {
            if(isPrime(i))
                primeNumbers.add(i);
        }
        return primeNumbers;
    }

    public static String analyse(int number) {
        StringJoiner result = new StringJoiner(" * ");
        List<Integer> primeNumbers = getPrimeNumbersInRange(number);
        while(number > 1) {
            for(int primeNumber : primeNumbers) {
                if(number % primeNumber == 0) {
                    result.add(primeNumber + "");
                    number /= primeNumber;
                    break;
                }
            }
        }
        return result.toString();
    }
}
