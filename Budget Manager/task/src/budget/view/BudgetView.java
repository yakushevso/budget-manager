package budget.view;

import java.util.Scanner;

public class BudgetView {
    private final Scanner sc = new Scanner(System.in);

    public void displayMessage(Object message) {
        System.out.println(message);
    }

    public String getInput() {
        return sc.nextLine().trim();
    }
}
