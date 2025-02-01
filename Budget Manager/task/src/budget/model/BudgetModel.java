package budget.model;

import java.util.ArrayList;
import java.util.List;

public class BudgetModel {
    private double totalIncome;
    private final List<Purchase> purchases = new ArrayList<>();

    public void addIncome(double income) {
        totalIncome += income;
    }

    public void addPurchase(String purchase, double cost) {
        purchases.add(new Purchase(purchase, cost));
        totalIncome -= cost;
    }

    public List<Purchase> getListOfPurchases() {
        return purchases;
    }

    public boolean isPurchasesEmpty() {
        return purchases.isEmpty();
    }

    public double getTotalCostOfPurchases() {
        double totalCost = 0;

        for (Purchase purchase : purchases) {
            totalCost += purchase.price();
        }

        return totalCost;
    }

    public double getBalance() {
        return totalIncome;
    }
}
