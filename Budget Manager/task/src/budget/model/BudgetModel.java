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
        Arrays.stream(Category.values()).forEach(category -> purchases.put(category, new ArrayList<>()));
    }

    public double getBalance() {
        return balance;
    }

    public double getExpenses() {
        return expenses;
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

    public List<Purchase> getAllPurchases() {
        return purchases.values().stream().flatMap(Collection::stream).toList();
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
        return getPurchases(category).stream().mapToDouble(Purchase::price).sum();
    }

    public List<String> getPurchasesForSave() {
        return getAllPurchases().stream().map(Purchase::toSave).toList();
    }

    public List<Purchase> getSortedPurchases(List<Purchase> list) {
        return list.stream().sorted(Comparator.comparingDouble(Purchase::price).reversed()).toList();
    }

    public List<Map.Entry<String, Double>> getSortedCategories() {
        return purchases.keySet().stream()
                .map(list -> Map.entry(list.getName(), getCategoryExpenses(list.ordinal() + 1)))
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList();
    }
}
