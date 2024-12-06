package lab8;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DataManager dataManager = new DataManager();

        dataManager.registerDataProcessor(new DataHandler.DataFilter());
        dataManager.registerDataProcessor(new DataHandler.DataTransformer());
        dataManager.registerDataProcessor(new DataHandler.DataAggregator());

        dataManager.loadData("lab8/ForLoadData.txt");
        dataManager.processData();
        dataManager.saveData("lab8/empty.txt", List.of());
    }
}