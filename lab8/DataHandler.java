package lab8;

import java.util.List;
import java.util.stream.Collectors;

public class DataHandler {

    public static class DataFilter {
        @DataProcessor
        public List<String> filterData(List<String> data) {
            return data.stream()
                    .filter(line -> line.contains("text"))
                    .collect(Collectors.toList());
        }
    }

    public static class DataTransformer {
        @DataProcessor
        public List<String> transformData(List<String> data) {
            return data.stream()
                    .map(line -> line.replace(" ", "-").toUpperCase())
                    .collect(Collectors.toList());
        }
    }

    public static class DataAggregator {
        @DataProcessor
        public List<String> aggregateData(List<String> data) {
            int wordCount = data.stream()
                    .mapToInt(line -> line.split("\\s+").length)
                    .sum();
            return List.of("Total Word Count: " + wordCount);
        }
    }
}