package budget.view;

public enum Messages {
    ALL("All:"),
    BALANCE("Balance: $"),
    ENTER_INCOME("Enter income:"),
    ENTER_PURCHASE_NAME("Enter Purchase name:"),
    ENTER_PURCHASE_PRICE("Enter its price:"),
    EXIT("Bye!"),
    INCOME_ADDED("Income was added!"),
    INVALID_COMMAND("Invalid command! Please try again:"),
    INVALID_NUMBER("Invalid number. Please enter a number:"),
    LIST_EMPTY("The Purchase list is empty"),
    LOADED("Purchases were loaded!"),
    MENU("""
            Choose your action:
            1) Add income
            2) Add purchase
            3) Show list of purchases
            4) Balance
            5) Save
            6) Load
            7) Analyze (Sort)
            0) Exit"""),
    PURCHASE_ADDED("Purchase was added!"),
    PURCHASE_ADD_MENU("""
            Choose the type of purchase
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) Back"""),
    PURCHASE_SHOW_MENU("""
            Choose the type of purchase
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) All
            6) Back"""),
    SAVED("Purchases were saved!"),
    SORT_MENU("""
            How do you want to sort?
            1) Sort all purchases
            2) Sort by type
            3) Sort certain type
            4) Back"""),
    SORT_TYPE_MENU("""
            Choose the type of purchase
            1) Food
            2) Clothes
            3) Entertainment
            4) Other"""),
    TOTAL_SUM("Total sum: $"),
    TYPES("Types:");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
