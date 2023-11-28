package districtoffice;

import sender_information.Packages;

import java.util.LinkedList;
import java.util.Queue;

public class DongDaOffice implements Office {

    private Queue<Packages> dongDaQueue = new LinkedList<>();

    @Override
    public void deliverToOffice(Packages packages) {
        dongDaQueue.add(packages);
    }

    @Override
    public Packages takePackageToDeliver() {
        return dongDaQueue.poll();
    }

    @Override
    public void printPackages() {
        for (Packages packages : dongDaQueue ) {
            System.out.println(packages.toString());
        }
    }

    @Override
    public Queue<Packages> getPackageQueue() {
        return dongDaQueue;
    }

    public static void main(String[] args) {
        Office dongDaOffice = new DongDaOffice();
        OfficeGUI dongDaGUI = new OfficeGUI(dongDaOffice);
        dongDaGUI.show();
    }
}
