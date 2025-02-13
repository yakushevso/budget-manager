package budget.controller;

import budget.model.BudgetModel;
import budget.model.FileManager;
import budget.view.BudgetView;
import budget.view.Messages;

import java.util.ArrayList;
import java.util.List;

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
                case SAVE -> saveToFile();
                case LOAD -> loadFromFile();
                case SORT -> sort();
                case EXIT -> {
                    view.displayMessage(Messages.EXIT);
                    return;
                }
            }

            view.displayMessage("");
        }
    }

    private void sort() {
        while (true) {
            int menuSize = 4;

            int typeOfSort = getNumOfCategory(Messages.SORT_MENU, menuSize);

            if (typeOfSort == menuSize) {
                return;
            }

            view.displayMessage("");

            switch (typeOfSort) {
                case 1 -> sortAll();
                case 2 -> sortByType();
                case 3 -> sortCertainType();
            }

            view.displayMessage("");
        }
    }

    private void sortCertainType() {
        int category = getNumOfCategory(Messages.SORT_TYPE_MENU, 4);

        view.displayMessage("");

        if (model.isPurchasesEmpty(category)) {
            view.displayMessage(Messages.LIST_EMPTY);
        } else {
            view.displayMessage(model.getCategoryName(category) + ":");

            model.getSortedPurchases(model.getPurchases(category)).forEach(view::displayMessage);

            view.displayFormattedMessage(Messages.TOTAL_SUM, model.getCategoryExpenses(category));
        }
    }

    private void sortByType() {
        view.displayMessage(Messages.TYPES);

        model.getSortedCategories().forEach(c -> view.displayMessage(c.getKey() + " - $" + c.getValue()));

        view.displayFormattedMessage(Messages.TOTAL_SUM, model.getExpenses());
    }

    private void sortAll() {
        if (model.isCategoriesEmpty()) {
            view.displayMessage(Messages.LIST_EMPTY);
            return;
        }

        view.displayMessage(Messages.ALL);

        model.getSortedPurchases(model.getAllPurchases()).forEach(view::displayMessage);

        view.displayFormattedMessage(Messages.TOTAL_SUM, model.getExpenses());
    }

    private void saveToFile() {
        List<String> data = new ArrayList<>();

        data.add(String.valueOf(model.getBalance()));
        data.add(String.valueOf(model.getExpenses()));
        data.addAll(model.getPurchasesForSave());

        FileManager.save(data);

        view.displayMessage(Messages.SAVED);
    }

    private void loadFromFile() {
        List<String> data = FileManager.load();

        model.addIncome(Double.parseDouble(data.get(0)) + Double.parseDouble(data.get(1)));

        for (int i = 2; i < data.size(); i++) {
            String[] line = data.get(i).split(",");
            model.addPurchase(Integer.parseInt(line[0]), line[1], Double.parseDouble(line[2]));
        }

        view.displayMessage(Messages.LOADED);
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
            int menuSize = model.getCategoryCount() + 1;
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

                    model.addPurchase(category, name, price);
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

                model.getAllPurchases().forEach(view::displayMessage);

                view.displayFormattedMessage(Messages.TOTAL_SUM, model.getExpenses());
            } else {
                view.displayMessage(model.getCategoryName(category) + ":");

                if (model.isPurchasesEmpty(category)) {
                    view.displayMessage(Messages.LIST_EMPTY);
                } else {
                    model.getPurchases(category).forEach(view::displayMessage);
                    view.displayFormattedMessage(Messages.TOTAL_SUM, model.getCategoryExpenses(category));
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
        view.displayFormattedMessage(Messages.BALANCE, model.getBalance());
    }
}
