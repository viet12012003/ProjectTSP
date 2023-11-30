package districtoffice;

import sender_information.Packages;

import java.util.LinkedList;
import java.util.Queue;

public class TayHoOffice implements Office {
    private String fileName = "Tây Hồ_packages.csv";
    private Queue<Packages> tayHoQueue;

    public TayHoOffice() {
        DataReader dataReader = new DataReader(fileName);
        this.tayHoQueue = dataReader.readDataFromFile();
    }

    @Override
    public void deliverToOffice(Packages packages) {
        tayHoQueue.add(packages);
    }

    @Override
    public Packages takePackageToDeliver() {
        return tayHoQueue.poll();
    }

    @Override
    public void printPackages() {
        for (Packages packages: tayHoQueue ) {
            System.out.println(packages.toString());
        }
    }

    @Override
    public Queue<Packages> getPackageQueue() {
        return tayHoQueue;
    }

    public static void main(String[] args) {
        Office tayHoOffice = new TayHoOffice();
        OfficeGUI tayHoGUI = new OfficeGUI(tayHoOffice);
        tayHoGUI.show();
    }
}
