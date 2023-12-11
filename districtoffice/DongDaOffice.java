package districtoffice;

import sender_information.Packages;
import shipper.Shipper;

import java.util.LinkedList;
import java.util.Queue;

public class DongDaOffice implements Office {
    private Shipper shipper;
    private Queue<Packages> dongDaQueue;
    private final String OFFICE_ADDRESS = "87, Phố Thái Thịnh, Phường Thịnh Quang, Đống Đa";

    public DongDaOffice() {
        dongDaQueue = new LinkedList<>();
    }

    @Override
    public void deliverToOffice(Packages packages) {
        dongDaQueue.add(packages);
        this.shipper = new Shipper(this);
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

    @Override
    public Shipper getShipper() {
        return shipper;
    }

    @Override
    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public Queue<Packages> getDongDaQueue() {
        return dongDaQueue;
    }

    public void setDongDaQueue(Queue<Packages> dongDaQueue) {
        this.dongDaQueue = dongDaQueue;
    }

    public Packages getOFFICE_ADDRESS() {
        return new Packages(-1, null, null, OFFICE_ADDRESS, null, -1, null);
    }
}
