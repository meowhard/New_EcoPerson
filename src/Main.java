import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Result result = new Result();
        result.processing("c:\\date\\data.csv", 100);
    }
}

class Result {
    public Result() {
    }

    public void processing(String dataSource, int ecoConsumptionMaxValue) throws IOException {
        DataCollector dataCollector = new DataCollector(dataSource);
        dataCollector.collectDataInLines();
    }
}

class Data {
    private int id;
    private String name;
    private int gasCount1;
    private int gasCount2;
    private int electroCount1;
    private int electroCount2;

    public Data(int id, String name, int gasCount1, int gasCount2, int electroCount1, int electroCount2) {
        this.id = id;
        this.name = name;
        this.gasCount1 = gasCount1;
        this.gasCount2 = gasCount2;
        this.electroCount1 = electroCount1;
        this.electroCount2 = electroCount2;
    }

    public Data() {
    }
}

class DataCollector {
    private String header;
    private final String dataSource;
    private String[] dataLines;

    public DataCollector(String dataSource) {
        this.dataSource = dataSource;
    }

    private BufferedReader dataReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(dataSource));
    }

    public String[] collectDataInLines() throws IOException {
        String[] lines = new String[0];
        String line;
        int i = 0;

        dataReader().readLine(); //скипаю заголовок

        while ((line = dataReader().readLine()) != null) {
            lines = Arrays.copyOf(lines, lines.length + 1);
            lines[i] = line;
            i++;
        }
        dataReader().close();
        return lines;
    }

    public void collectHeader() throws IOException {
        this.header = dataReader().readLine();
        dataReader().close();
    }
}

class DataConstructor {
    DataCollector dataCollector = new DataCollector();
    public void construct() {

    }
}

