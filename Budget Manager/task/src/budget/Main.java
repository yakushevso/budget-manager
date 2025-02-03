package budget;

import budget.controller.BudgetController;
import budget.model.BudgetModel;
import budget.view.BudgetView;

public class Main {
    public static void main(String[] args) {
        new BudgetController(new BudgetModel(), new BudgetView()).run();
    }
}
