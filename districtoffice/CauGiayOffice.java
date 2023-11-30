package districtoffice;

import sender_information.Packages;
import java.util.Queue;

public class CauGiayOffice implements Office{
    private Queue<Packages> cauGiayQueue ;
    private String fileName = "D:\\Workspace\\code\\src\\ProjectTSP\\Cầu Giấy_packages.csv";

    public CauGiayOffice() {
        DataReader dataReader = new DataReader(fileName);
        this.cauGiayQueue = dataReader.readDataFromFile();
    }

    @Override
    public void deliverToOffice(Packages packages) {
        cauGiayQueue.add(packages);
    }

    @Override
    public Packages takePackageToDeliver() {
        return cauGiayQueue.poll();
    }

    @Override
    public void printPackages() {
        for (Packages packages:
                cauGiayQueue) {
            System.out.println(packages.toString());
        }
    }

    @Override
    public Queue<Packages> getPackageQueue() {
        return cauGiayQueue;
    }
    public static void main(String[] args) {
        Office cauGiayOffice = new CauGiayOffice();
        OfficeGUI cauGiayGUI = new OfficeGUI(cauGiayOffice);
        cauGiayGUI.show();
    }

}