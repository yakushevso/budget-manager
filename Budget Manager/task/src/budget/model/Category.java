package budget.model;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    FOOD(1, "Food"),
    CLOTHES(2, "Clothes"),
    ENTERTAINMENT(3, "Entertainment"),
    OTHER(4, "Other");

    private final int id;
    private final String name;
    private final List<Purchase> purchases = new ArrayList<>();

    Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
}
