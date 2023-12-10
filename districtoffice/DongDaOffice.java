package districtoffice;

import sender_information.Packages;

import java.util.LinkedList;
import java.util.Queue;

public class DongDaOffice implements Office {
    private Queue<Packages> dongDaQueue;
    private final String OFFICE_ADDRESS = "87, Phố Thái Thịnh, Phường Thịnh Quang, Đống Đa";

    public DongDaOffice() {
        dongDaQueue = new LinkedList<>();
    }

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
        for (Packages packages : dongDaQueue) {
            System.out.println(packages.toString());
        }
    }

    @Override
    public Queue<Packages> getPackageQueue() {
        return dongDaQueue;
    }

    public Packages getOFFICE_ADDRESS() {
        return new Packages(-1,null,null,OFFICE_ADDRESS,null,-1,null);
    }
}
