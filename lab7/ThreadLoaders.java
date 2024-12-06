package lab7;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

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
        private static final int MAX_WEIGHT_LOADERS = 150; // Лимит общего веса товаров, который может переноситься
        private static int allWeight = 0; // Общий вес товаров, которые переносятся
        private final Warehouse warehouse;
        private final CountDownLatch latch;
        private final AtomicBoolean workDone;

        public Mover(Warehouse warehouse, CountDownLatch latch, AtomicBoolean workDone) {
            this.warehouse = warehouse;
            this.latch = latch;
            this.workDone = workDone;
        }

        @Override
        public void run() {
            try {
                latch.await();

                while (true) {
                    Product product;

                    synchronized (warehouse) {
                        product = warehouse.getNextProduct();
                    }

                    if (product == null) {
                        synchronized (workDone) {
                            if (!workDone.get()) {
                                workDone.set(true);
                                System.out.println(Thread.currentThread().getName() + " доставлен груз на склад с весом: "
                                        + currentWeight + " кг.");
                                allWeight += currentWeight;
                                System.out.println("Все грузчики завершили работу. Всего на втором складе: " + allWeight + " кг.");
                            }
                        }
                        break;
                    }

                    if (product.getWeight() > MAX_WEIGHT_LOADERS) {
                        System.out.println(Thread.currentThread().getName() + " пропустил товар весом: " + product.getWeight() + " кг.");
                        continue;
                    }

                    synchronized (Mover.class) {
                        if (currentWeight + product.getWeight() <= MAX_WEIGHT_LOADERS) {
                            currentWeight += product.getWeight();
                            System.out.println(Thread.currentThread().getName() + " добавил товар весом: " + product.getWeight()
                                    + " кг. Текущий вес у всех грузчиков: " + currentWeight + " кг.");
                        } else {
                            System.out.println(Thread.currentThread().getName() + " доставлен груз на склад с весом: "
                                    + currentWeight + " кг.");
                            allWeight += currentWeight;
                            currentWeight = product.getWeight();
                            System.out.println(Thread.currentThread().getName() + " добавил товар весом: " + product.getWeight()
                                    + " кг. Текущий вес у всех грузчиков: " + currentWeight + " кг.");
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
                new Product(40),
                new Product(50),
                new Product(60),
                new Product(70),
                new Product(80),
                new Product(90),
                new Product(100)

        );

        Warehouse warehouse = new Warehouse(products);
        int numMovers = 3; // Количество грузчиков
        CountDownLatch latch = new CountDownLatch(numMovers);
        AtomicBoolean getDone = new AtomicBoolean(false);

        ExecutorService executor = Executors.newFixedThreadPool(numMovers);

        for (int i = 0; i < numMovers; i++) {
            executor.submit(() -> {
                latch.countDown();
                new Mover(warehouse, latch, getDone).run();
            });
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);


    }
}
