package receive;

import sender_information.Packages;

import java.util.PriorityQueue;

public class PackageQueueManager {
    private PriorityQueue<Packages> packageQueue;
    private String filename = "F:/Project DSA/ProjectTSP/data.csv";

    public PackageQueueManager() {
        DataReader dataReader = new DataReader(filename);
        // Đọc dữ liệu từ file và thêm vào PriorityQueue
        this.packageQueue = dataReader.readPackagesFromFile();
    }

    public PriorityQueue<Packages> getPackageQueue() {
        return packageQueue;
    }
}
