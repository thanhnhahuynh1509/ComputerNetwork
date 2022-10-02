package training.bai3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Bai3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = 0;
        while(m <= 0) {
            try {
                sc = new Scanner(System.in);
                m = sc.nextInt();
                if(m <= 0)
                    throw new RuntimeException();
            } catch (Exception ex) {
                System.out.println("m phải là số và lớn hơn 0");
                m = 0;
            }
        }
        System.out.println("Thừa số nguyên tố của " + m + " là: " + listNumber(m));
        sc.close();
    }

    private static boolean isPrime(int number) {
        if(number < 2)
            return false;
        int count = 0;
        for(int i = 1; i < number; i++) {
            if(number % i == 0)
                count ++;
        }
        return count == 1;
    }

    private static List<Integer> listPrimes(int number) {
        List<Integer> list = new ArrayList<>();
        for(int i = 2; i <= number; i++) {
            if(isPrime(i))
                list.add(i);
        }
        return list;
    }

    private static String listNumber(int number) {
        StringJoiner stringJoiner = new StringJoiner(" * ");
        while(number > 1) {
            for(int i = 2; i <= number; i++) {
                if(isPrime(i)) {
                    if(number % i == 0) {
                        stringJoiner.add(i + "");
                        number /= i;
                        break;
                    }
                }
            }
        }
        return stringJoiner.toString();
    }
}
