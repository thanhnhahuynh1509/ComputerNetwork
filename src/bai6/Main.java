package bai6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        float[] arrays = {3.2f, 4.5f, 6.5f, 4.5f, 3.2f, 7, 5};
        listNumbersAppears(arrays);
    }

    private static int countNumberAppear(float number, float[] arrays) {
        int count = 0;
        for(float a : arrays) {
            if(number == a)
                count++;
        }
        return count;
    }

    private static List<Float> listNumbersAppearTime(float[] arrays, int time) {
        List<Float> result = new ArrayList<>();
        for (float array : arrays) {
           if(countNumberAppear(array, arrays) == time && !result.contains(array))
               result.add(array);
        }
        return result;
    }

    private static void listNumbersAppears(float[] arrays) {
        List<Float> result = new ArrayList<>();
        for (float array : arrays) {
            if(result.contains(array)) {
                continue;
            }
            result.add(array);
            System.out.println(array + " is appeared " + countNumberAppear(array, arrays) + " time(s)");
        }
    }
}
