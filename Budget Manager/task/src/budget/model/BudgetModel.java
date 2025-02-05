package budget.model;

import java.util.*;

public class BudgetModel {
    private double balance;
    private double expenses;
    private final Map<Category, List<Purchase>> purchases = new HashMap<>();

    public BudgetModel() {
        initializePurchaseList();
    }

    private void initializePurchaseList() {
        for (Category category : Category.values()) {
            purchases.put(category, new ArrayList<>());
        }
    }

    public double getBalance() {
        return balance;
    }

    public double getExpenses() {
        return this.expenses;
    }

    public void addIncome(double income) {
        balance += income;
    }

    public void addPurchase(int category, String name, double price) {
        getPurchases(category).add(new Purchase(category, name, price));
        balance -= price;
        expenses += price;
    }

    public List<Purchase> getPurchases(int category) {
        return purchases.get(Category.fromId(category));
    }

    public boolean isPurchasesEmpty(int category) {
        return getPurchases(category).isEmpty();
    }

    public String getCategoryName(int category) {
        return Category.fromId(category).getName();
    }

    public boolean isCategoriesEmpty() {
        return purchases.values().stream().allMatch(List::isEmpty);
    }

    public int getCategoryCount() {
        return Category.values().length;
    }

    public double getCategoryExpenses(int category) {
        return getPurchases(category).stream()
                .mapToDouble(Purchase::price)
                .sum();
    }

    public List<String> getFormattedPurchases() {
        List<String> list = new ArrayList<>();

        for (List<Purchase> purchaseList : purchases.values()) {
            for (Purchase purchase : purchaseList) {
                list.add(purchase.category() + "," + purchase.name() + "," + purchase.price());
            }
        }

        return list;
    }
}
