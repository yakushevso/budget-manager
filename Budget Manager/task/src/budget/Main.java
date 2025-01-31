package budget;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            double res = 0;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
                res += Double.parseDouble(line.substring(line.lastIndexOf("$") + 1));
            }

            System.out.println("Total: $" + res);
        }
    }
}
