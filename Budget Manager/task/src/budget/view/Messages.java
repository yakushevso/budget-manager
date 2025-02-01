package budget.view;

public enum Messages {
    BALANCE("Balance: $"),
    ENTER_INCOME("Enter income:"),
    ENTER_PURCHASE_NAME("Enter Purchase name:"),
    ENTER_PURCHASE_PRICE("Enter its price:"),
    EXIT("Bye!"),
    INCOME_ADDED("Income was added!"),
    INVALID_COMMAND("Invalid command! Please try again:"),
    INVALID_NUMBER("Invalid number. Please enter a number:"),
    LIST_EMPTY("The Purchase list is empty"),
    MENU("""
            Choose your action:
            1) Add income
            2) Add purchase
            3) Show list of purchases
            4) Balance
            0) Exit"""),
    PURCHASE_ADDED("Purchase was added!"),
    TOTAL_SUM("Total sum: $");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
