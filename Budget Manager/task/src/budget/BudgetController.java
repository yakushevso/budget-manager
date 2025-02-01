package budget;

import budget.model.BudgetModel;
import budget.view.BudgetView;
import budget.view.Messages;

public class BudgetController {
    private final BudgetModel model;
    private final BudgetView view;

    public BudgetController(BudgetModel model, BudgetView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        while (true) {
            view.displayMessage(Messages.MENU);
            String userChoice = view.getInput();

            view.displayMessage("");

            switch (userChoice) {
                case "1" -> addIncome();
                case "2" -> addPurchase();
                case "3" -> getListOfPurchases();
                case "4" -> getBalance();
                case "0" -> {
                    view.displayMessage("");
                    view.displayMessage(Messages.EXIT);
                    return;
                }
                default -> view.displayMessage(Messages.INVALID_COMMAND);
            }

            view.displayMessage("");
        }
    }

    private void addIncome() {
        while (true) {
            try {
                view.displayMessage(Messages.ENTER_INCOME);
                double income = Double.parseDouble(view.getInput());

                if (income < 0) {
                    view.displayMessage(Messages.INVALID_NUMBER);
                    continue;
                }

                model.addIncome(income);
                view.displayMessage(Messages.INCOME_ADDED);

                break;
            } catch (NumberFormatException e) {
                view.displayMessage(Messages.INVALID_NUMBER);
            }
        }
    }

    private void addPurchase() {
        view.displayMessage(Messages.ENTER_PURCHASE_NAME);
        String name = view.getInput();

        while (true) {
            try {
                view.displayMessage(Messages.ENTER_PURCHASE_PRICE);
                double price = Double.parseDouble(view.getInput());

                if (price < 0) {
                    view.displayMessage(Messages.INVALID_NUMBER);
                    continue;
                }

                model.addPurchase(name, price);
                view.displayMessage(Messages.PURCHASE_ADDED);

                break;
            } catch (NumberFormatException e) {
                view.displayMessage(Messages.INVALID_NUMBER);
            }
        }
    }

    private void getListOfPurchases() {
        if (model.isPurchasesEmpty()) {
            view.displayMessage(Messages.LIST_EMPTY);
        } else {
            model.getListOfPurchases().forEach(view::displayMessage);
            view.displayMessage(Messages.TOTAL_SUM + String.format("%.2f", model.getTotalCostOfPurchases()));
        }
    }

    private void getBalance() {
        view.displayMessage(Messages.BALANCE + String.format("%.2f", model.getBalance()));
    }
}
