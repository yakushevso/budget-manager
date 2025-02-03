package budget.controller;

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

            Command command = Command.from(view.getInput());

            if (command == null) {
                view.displayMessage(Messages.INVALID_COMMAND);
                continue;
            }

            view.displayMessage("");

            switch (command) {
                case ADD_INCOME -> addIncome();
                case ADD_PURCHASE -> addPurchase();
                case SHOW_PURCHASES -> showPurchases();
                case SHOW_BALANCE -> showBalance();
                case EXIT -> {
                    view.displayMessage(Messages.EXIT);
                    return;
                }
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
        while (true) {
            int categoryCount = model.getCategoryCount();
            int menuSize = categoryCount + 1;
            int category = getNumOfCategory(Messages.PURCHASE_ADD_MENU, menuSize);

            if (category == menuSize) {
                return;
            }

            view.displayMessage("");

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

                    model.addPurchase(name, price, category);
                    view.displayMessage(Messages.PURCHASE_ADDED);
                    break;
                } catch (NumberFormatException e) {
                    view.displayMessage(Messages.INVALID_NUMBER);
                }
            }

            view.displayMessage("");
        }
    }

    private void showPurchases() {
        if (model.isCategoriesEmpty()) {
            view.displayMessage(Messages.LIST_EMPTY);
            return;
        }

        while (true) {
            int categoryCount = model.getCategoryCount();
            int menuSize = categoryCount + 2;

            int category = getNumOfCategory(Messages.PURCHASE_SHOW_MENU, menuSize);

            if (category == menuSize) {
                return;
            }

            view.displayMessage("");

            if (category == menuSize - 1) {
                view.displayMessage(Messages.ALL);

                double totalSum = 0;
                for (int i = 1; i <= categoryCount; i++) {
                    if (!model.isPurchasesEmpty(i)) {
                        model.getPurchases(i).forEach(view::displayMessage);
                        totalSum += model.getTotalCost(i);
                    }
                }

                view.displayMessage(Messages.TOTAL_SUM + String.format("%.2f", totalSum));
            } else {
                view.displayMessage(model.getCategoryName(category) + ":");

                if (model.isPurchasesEmpty(category)) {
                    view.displayMessage(Messages.LIST_EMPTY);
                } else {
                    model.getPurchases(category).forEach(view::displayMessage);
                    view.displayMessage(Messages.TOTAL_SUM + String.format("%.2f", model.getTotalCost(category)));
                }
            }

            view.displayMessage("");
        }
    }

    private int getNumOfCategory(Messages menu, int size) {
        while (true) {
            view.displayMessage(menu);
            String userChoice = view.getInput();

            try {
                int category = Integer.parseInt(userChoice);
                if (category >= 1 && category <= size) {
                    return category;
                }
            } catch (NumberFormatException ignored) {
            }

            view.displayMessage(Messages.INVALID_COMMAND);
        }
    }

    private void showBalance() {
        view.displayMessage(Messages.BALANCE + String.format("%.2f", model.getBalance()));
    }
}
