package lab6;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SalesTracker {
    private final ConcurrentHashMap<String, Integer> salesData;
    private double totalSalesAmount;


    public SalesTracker() {
        salesData = new ConcurrentHashMap<>();
        totalSalesAmount = 0.0;
    }


    public double getTotalSalesAmount() {
        return totalSalesAmount;
    }


    public void addSale(String product, int quantity, double pricePerUnit) {
        salesData.put(product, salesData.getOrDefault(product, 0) + quantity);
        totalSalesAmount += quantity * pricePerUnit;
    }


    public void showSales() {
        System.out.println("Список проданных товаров:");
        for (Map.Entry<String, Integer> entry : salesData.entrySet()) {
            System.out.println("Товар: " + entry.getKey() + ", Количество: " + entry.getValue());
        }
    }


    public String getMostPopularProduct() {
        String mostPopular = null;
        int maxSales = 0;

        for (Map.Entry<String, Integer> entry : salesData.entrySet()) {
            int sales = entry.getValue();

            if (sales > maxSales) {
                mostPopular = entry.getKey();
                maxSales = sales;
            }
        }
        return mostPopular != null ? mostPopular : "Нет данных о продажах";
    }

    public static void main(String[] args) {
        SalesTracker tracker = new SalesTracker();

        tracker.addSale("Апельсины", 10, 1.5);
        tracker.addSale("Яблоки", 5, 2.0);
        tracker.addSale("Апельсины", 7, 1.5);
        tracker.addSale("Бананы", 12, 1.2);
        tracker.addSale("Яблоки", 3, 2.0);

        tracker.showSales();

        System.out.println("Общая сумма продаж: " + tracker.getTotalSalesAmount());
        System.out.println("Самый популярный товар: " + tracker.getMostPopularProduct());
    }
}