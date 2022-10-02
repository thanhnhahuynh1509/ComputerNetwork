package bai1;

public class Converter {

    public static String convert(int number, int b) {
        if(b <= 1 || b > 24)
            return number + "";
        StringBuilder result = new StringBuilder();

        while(number > 0) {
            result.append(number % b);
            number /= b;
        }

        return reverseString(result.toString());
    }

    private static String reverseString(String str) {
        String[] split = str.split("");
        StringBuilder result = new StringBuilder();
        for(int i = split.length - 1; i >= 0; i--) {
            result.append(split[i]);
        }
        return result.toString();
    }
}
