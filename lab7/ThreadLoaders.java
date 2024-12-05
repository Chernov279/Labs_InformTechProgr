package lab7;

import java.util.*;
import java.util.concurrent.*;

public class ThreadLoaders {
    public static class Product {
        int weight;

        public Product(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }

    public static class Warehouse {
        private final List<Product> products;
        private int currentIndex = 0;

        public Warehouse(List<Product> products) {
            this.products = products;
        }

        public synchronized Product getNextProduct() {
            if (currentIndex < products.size()) {
                return products.get(currentIndex++);
            }
            return null;
        }
    }
    public static class Mover implements Runnable {
        private static int currentWeight = 0; // Общий вес товаров, которые переносятся
        private static int totalDeliveredWeight = 0; // Общий доставленный вес на втором складе
        private static final int MAX_SECOND_WAREHOUSE_WEIGHT = 150; // Лимит веса на втором складе
        private static final int MAX_WEIGHT_LOADERS = 150; // Лимит общего веса товаров, который может переноситься

        private final Warehouse firstWarehouse;
        private final CountDownLatch latch;

        private int localWeight = 0;// Вес товаров, которые переносит конкретный грузчик

        public Mover(Warehouse warehouse, CountDownLatch latch) {
            this.firstWarehouse = warehouse;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();

                while (true) {
                    Product product;

                    synchronized (firstWarehouse) {
                        product = firstWarehouse.getNextProduct();
                    }

                    if (product == null) {
                        break;
                    }

                    if (product.getWeight() > MAX_WEIGHT_LOADERS) {
                        System.out.println(Thread.currentThread().getName() + " пропустил товар весом: " + product.getWeight() + " кг.");
                        continue;
                    }

                    synchronized (this) {
                        while (currentWeight + product.getWeight() > MAX_WEIGHT_LOADERS) {
                            System.out.println(Thread.currentThread().getName() + " пока ожидает.");
                            wait();
                        }

                        currentWeight += product.getWeight();
                        localWeight += product.getWeight();
                        System.out.println(Thread.currentThread().getName() + " добавил товар весом: " + product.getWeight()
                                + " кг. Текущий вес у всех грузчиков: " + currentWeight + " кг. Текущий вес этого грузчика: " + localWeight);

                        // Задержка для имитации времени переноса товара
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " доставил груз на склад с весом: "
                                + localWeight + " кг.");

                        synchronized (Mover.class) {
                            totalDeliveredWeight += localWeight;
                            currentWeight -= localWeight;
                            notifyAll();
                            if (totalDeliveredWeight >= MAX_SECOND_WAREHOUSE_WEIGHT) {
                                System.out.println("На втором складе достигнут общий вес: " + totalDeliveredWeight + " кг. Работа завершена.");
                                System.exit(0);
                            }
                        localWeight = 0;

                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Product> products = Arrays.asList(
                new Product(1250),
                new Product(10),
                new Product(20),
                new Product(30),
                new Product(30),
                new Product(50),
                new Product(30),
                new Product(20),
                new Product(40),
                new Product(30),
                new Product(80)

        );

        Warehouse warehouse = new Warehouse(products);
        int numMovers = 3; // Количество грузчиков
        CountDownLatch latch = new CountDownLatch(numMovers);

        ExecutorService executor = Executors.newFixedThreadPool(numMovers);

        for (int i = 0; i < numMovers; i++) {
            executor.submit(() -> {
                latch.countDown();
                new Mover(warehouse, latch).run();
            });
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        System.out.println("Все грузчики завершили работу.");
    }
}
