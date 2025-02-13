package budget.view;

import java.util.Locale;
import java.util.Scanner;

public class BudgetView {
    private final Scanner sc = new Scanner(System.in);

    public void displayMessage(Object message) {
        System.out.println(message);
    }

    public void displayFormattedMessage(Messages messages, double message) {
        System.out.printf(Locale.US, "%s%.2f\n", messages, message);
    }

    public String getInput() {
        return sc.nextLine().trim();
    }
}
