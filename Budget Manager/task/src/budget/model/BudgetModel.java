package budget.model;

import java.util.*;

public class BudgetModel {
    private double balance;
    private final Map<Integer, Category> categories = new HashMap<>();

    public BudgetModel() {
        initializePurchaseList();
    }

    private void initializePurchaseList() {
        for (Category category : Category.values()) {
            categories.put(category.getId(), category);
        }
    }

    public void addIncome(double income) {
        balance += income;
    }

    public void addPurchase(String name, double cost, int category) {
        getPurchases(category).add(new Purchase(name, cost));
        balance -= cost;
    }

    public String getCategoryName(int category) {
        return categories.get(category).getName();
    }

    public List<Purchase> getPurchases(int category) {
        return categories.get(category).getPurchases();
    }

    public boolean isPurchasesEmpty(int category) {
        return getPurchases(category).isEmpty();
    }

    public boolean isCategoriesEmpty() {
        boolean isEmpty = true;

        for (Category category : Category.values()) {
            if (!isPurchasesEmpty(category.getId())) {
                return false;
            }
        }

        return isEmpty;
    }

    public double getTotalCost(int category) {
        return getPurchases(category).stream()
                .mapToDouble(Purchase::price)
                .sum();
    }

    public double getBalance() {
        return balance;
    }

    public int getCategoryCount() {
        return Category.values().length;
    }
}
