import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

class DataManager {

    private final int ECO_VALUE;
    private String[] dataLines;
    private Data[] dataArray;
    private Data[] ecoDataArray;
    private final Path DATA_RESULT;
    private final BufferedReader BR;

    public DataManager(String dataSource, int ecoValue) throws FileNotFoundException {
        Path dataPath = Path.of(dataSource);
        this.DATA_RESULT = Path.of(dataPath.getParent() + "\\result.csv");
        this.ECO_VALUE = ecoValue;
        this.BR =  new BufferedReader(new FileReader(dataSource));
    }

    public void readData() throws IOException {

        String[] data = new String[0];
        String line;

        for (int i = 0; (line = BR.readLine()) != null; i++) {
            data = Arrays.copyOf(data, data.length + 1);
            data[i] = line;
        }
        System.out.println(data.length);
        BR.close();
        dataLines = data;
    }

    public void dataCreator() {
        int id;
        String name;
        int waterCount;
        int gasCount1;
        int gasCount2;
        int electroCount1;
        int electroCount2;

        Data[] data = new Data[0];

        for (int i = 1; i < dataLines.length; i++) {
            String[] paramsArr = dataLines[i].split("\\|");
            data = Arrays.copyOf(data, data.length + 1);

            id = Integer.parseInt(paramsArr[0]);
            name = paramsArr[1];
            waterCount = Integer.parseInt(paramsArr[2]);
            gasCount1 = Integer.parseInt(paramsArr[3]);
            gasCount2 = Integer.parseInt(paramsArr[4]);
            electroCount1 = Integer.parseInt(paramsArr[5]);
            electroCount2 = Integer.parseInt(paramsArr[6]);

            data[i - 1] = new Data(id, name, waterCount, gasCount1, gasCount2, electroCount1, electroCount2);
        }
        dataArray = data;
    }

    public String getHeader() {
        return dataLines[0];
    }

    public void getEcoDataArray() {
        Data[] ecoData = new Data[0];
        int j = 0;

        for (int i = 0; i < dataArray.length; i++) {
            if ((dataArray[i].getWaterCount() < ECO_VALUE) &&
                    (dataArray[i].getGasCount1() < ECO_VALUE) &&
                    (dataArray[i].getGasCount2() < ECO_VALUE) &&
                    (dataArray[i].getElectroCount1() < ECO_VALUE) &&
                    (dataArray[i].getElectroCount2() < ECO_VALUE)) {
                ecoData = Arrays.copyOf(ecoData, ecoData.length + 1);
                ecoData[j] = dataArray[i];
                j++;
            }
        }
        ecoDataArray = ecoData;
    }

    public void writeData() throws IOException {
        Files.write(DATA_RESULT, (getHeader() + "\n").getBytes());
        for (int i = 0; i < ecoDataArray.length; i++) {
            Files.write(DATA_RESULT, (ecoDataArray[i].getId() + "|" +
                    ecoDataArray[i].getName() + "|" +
                    ecoDataArray[i].getWaterCount() + "|" +
                    ecoDataArray[i].getGasCount1() + "|" +
                    ecoDataArray[i].getGasCount2() + "|" +
                    ecoDataArray[i].getElectroCount1() + "|" +
                    ecoDataArray[i].getElectroCount2() + "\n").getBytes(), StandardOpenOption.APPEND);
        }
    }
}