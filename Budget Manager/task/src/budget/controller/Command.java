package budget.controller;

public enum Command {
    ADD_INCOME("1"),
    ADD_PURCHASE("2"),
    SHOW_PURCHASES("3"),
    SHOW_BALANCE("4"),
    SAVE("5"),
    LOAD("6"),
    SORT("7"),
    EXIT("0");

    private final String code;

    Command(String code) {
        this.code = code;
    }

    public static Command from(String input) {
        for (Command command : values()) {
            if (command.code.equals(input)) {
                return command;
            }
        }
        return null;
    }
}
