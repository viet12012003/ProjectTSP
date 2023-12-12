package districtoffice;

import sender_information.Packages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class DataReader {
    private String filename;

    public DataReader(String filename) {
        this.filename = filename;
    }

    public Queue<Packages> readDataFromFile(){
        Queue<Packages> queue = new LinkedList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null ){
                String[] data = line.split(",");
                if (data.length == 9){
                    int id = Integer.parseInt(data[0]);
                    String sender = data[1];
                    String receiver = data[2];
                    String address = data[3] + ", " + data[4] + ", " + data[5];
                    String goods = data[6];
                    String phone = data[7];
                    String service = data[8];
                    Packages pack = new Packages(id, sender, receiver, address, goods, phone, service);
                    queue.offer(pack);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return queue;
    }
}
