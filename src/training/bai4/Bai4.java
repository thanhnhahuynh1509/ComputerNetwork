package training.bai4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bai4 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter your string: ");
            String str = sc.nextLine();
            System.out.println(countWords(str.trim()));
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static int countWords(String str) {
        List<String> strings = new ArrayList<>();
        for(String s : str.split(" ")) {
            if(s.isBlank())
                continue;
            strings.add(s);
        }
        return strings.size();
    }
}
