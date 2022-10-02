package training.bai5;

import java.util.Scanner;

public class Bai5 {
    public static void main(String[] args) {
        int n = 0, m = 0;
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Nhap n: ");
            while(n <= 0) {
                try {
                    sc = new Scanner(System.in);
                    n = sc.nextInt();
                    if(n <= 0)
                        throw new RuntimeException();

                } catch (Exception ex) {
                    System.out.println("n phải là số và lớn hơn 0");
                    n = 0;
                }
            }

            System.out.println("Nhap m: ");
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

            int[][] a = new int[n][m];

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    System.out.println("Nhap phan tu a[" + i + "][" + j + "]: ");
                    int number = sc.nextInt();
                    while(number < 0 || number >= 100) {
                        System.out.println("Số phải lớn hơn 0 và nhỏ hơn 100 (0 < x < 100)");
                        number = sc.nextInt();
                    }
                    a[i][j] = number;
                }
            }

            System.out.println("Ma trận đã nhập");
            print(a);

            String[] rowAndCol = maxIndex(a).split("-");
            int row = Integer.parseInt(rowAndCol[0]);
            int col = Integer.parseInt(rowAndCol[1]);
            System.out.println("Số nguyên lớn nhất của ma trận là " + a[row][col] + " với chỉ số là: [" + row + "][" + col + "]") ;

            System.out.println("Ma trận số nguyên tố");
            print(primeMatrix(a));

            sortColumn(a);
            System.out.println("Ma trận đã được sắp xếp theo cột");
            print(a);

        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
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

    private static int[][] primeMatrix(int a[][]) {
        int b[][] = new int[a.length][a[0].length];
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                if(isPrime(a[i][j]))
                    b[i][j] = a[i][j];
                else
                    b[i][j] = 0;
            }
        }
        return b;
    }

    private static String maxIndex(int a[][]) {
        int n = 0;
        int m = 0;
        int max = 0;
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                if(max < a[i][j]) {
                    max = a[i][j];
                    n = i;
                    m = j;
                }
            }
        }
        return n + "-" + m;
    }

    private static void sortColumn(int a[][]) {
        for(int i = 0; i < a[0].length; i++) {
            for(int j = 0; j < a.length -1; j++) {
               for(int k = 0; k < a.length - 1; k++) {
                   if(a[k][i] > a[k + 1][i]) {
                       int tmp = a[k][i];
                       a[k][i] = a[k + 1][i];
                       a[k + 1][i] = tmp;
                   }
               }
            }
        }
    }

    private static void print(int a[][] ) {
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}
