package budget.model;

import java.util.Locale;

public record Purchase(int category, String name, double price) {

    public String toSave() {
        return String.format(Locale.US, "%s,%s,%.2f", category, name, price);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s $%.2f", name, price);
    }
}
