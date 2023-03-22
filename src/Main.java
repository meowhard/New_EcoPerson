import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DataManager dataManager = new DataManager("c:\\data\\new_data\\data.csv", 100);
        dataManager.readData();
        dataManager.dataCreator();
        dataManager.getEcoDataArray();
        dataManager.writeData();
    }
}