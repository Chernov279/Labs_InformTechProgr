package lab8;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DataManager {
    private final List<Object> processors = new ArrayList<>();
    private List<String> data = new ArrayList<>();

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    public void loadData(String source) {
        try {
            data = Files.readAllLines(Path.of(source));
            System.out.println("Data loaded from file: " + data);
        } catch (IOException e) {
            System.err.println("Failed to load data from file: " + e.getMessage());
        }
    }

    public void processData() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (Object processor : processors) {
            for (Method method : processor.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    executor.submit(() -> {
                        try {
                            List<String> localData = new ArrayList<>(data);

                            List<String> processedData = (List<String>) method.invoke(processor, localData);

                            System.out.println("Processed by " + method.getName() + ": " + processedData);

                            String fileName = "lab8/ProcessedBy_" + method.getName() + ".txt";
                            saveData(fileName, processedData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

    public static void saveData(String destination, List<String> data) {
        try {
            Files.write(
                    Path.of(destination),
                    data,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
            System.out.println("Data saved to " + destination + ": " + data);
        } catch (IOException e) {
            System.err.println("Error saving data to " + destination);
            e.printStackTrace();
        }
    }
}
