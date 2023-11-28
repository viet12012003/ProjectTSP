package districtoffice;

import sender_information.Packages;

import java.util.LinkedList;
import java.util.Queue;

public class ThanhXuanOffice implements Office {
    private Queue<Packages> thanhXuanQueue = new LinkedList<>();
    public void deliverToOffice(Packages packages) {
        thanhXuanQueue.add(packages);
    }

    @Override
    public Packages takePackageToDeliver() {
        return thanhXuanQueue.poll();
    }

    @Override
    public void printPackages() {
        for(Packages packages : thanhXuanQueue){
            System.out.println(packages.toString());
        }
    }

    @Override
    public Queue<Packages> getPackageQueue() {
        return thanhXuanQueue;
    }

    public static void main(String[] args) {
        Office thanhXuanOffice = new ThanhXuanOffice();
        OfficeGUI thanhXuanGUI = new OfficeGUI(thanhXuanOffice);
        thanhXuanGUI.show();
    }
}
