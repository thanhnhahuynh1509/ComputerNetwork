package bai5;

public class Main {
    public static void main(String[] args) {
        getsAllNumberValid();
    }

    private static boolean checkNumberValid(int number) {
        String numberStr = number + "";
        String[] arr = numberStr.split("");
        int length = arr.length;
        for(int i = 0; i < length / 2; i++) {
            String numberFirst = arr[i];
            String numberLast = arr[length - 1 - i];
            if(!numberFirst.equals(numberLast))
                return false;
        }
        return true;
    }

    private static void getsAllNumberValid() {
        for(int i = 100000; i <= 999999; i++) {
            if(checkNumberValid(i))
                System.out.println(i);
        }
    }
}
