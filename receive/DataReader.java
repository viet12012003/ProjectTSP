package receive;

import sender_information.Packages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DataReader {
    private String fileName;

    public DataReader(String fileName) {
        this.fileName = fileName;
    }

    public PriorityQueue<Packages> readPackagesFromFile() {
        // packageQueue ưu tiên Hỏa Tốc trước Thường, nếu cùng loại thì ưu tiên theo ID
        PriorityQueue<Packages> packageQueue = new PriorityQueue<>(Comparator
                .comparing((Packages p) -> p.getService().equalsIgnoreCase("Hỏa tốc") ? 0 : 1)
                .thenComparing(Packages::getId));

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 9) {
                    int id = Integer.parseInt(data[0]);
                    String sender = data[1];
                    String receiver = data[2];
                    String address = data[3] + ", " + data[4] + ", " + data[5];
                    String goods = data[6];
                    double weight = Double.parseDouble(data[7]);
                    String service = data[8];

                    Packages pack = new Packages(id, sender, receiver, address, goods, weight, service);
                    packageQueue.offer(pack);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return packageQueue;
    }
}