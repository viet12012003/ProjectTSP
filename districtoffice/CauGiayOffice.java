package districtoffice;

import sender_information.Packages;
import shipper.Shipper;

import java.util.LinkedList;
import java.util.Queue;

public class CauGiayOffice implements Office{
    private Shipper shipper;

    private Queue<Packages> cauGiayQueue;
    private final String OFFICE_ADDRESS = "165, Đường Cầu Giấy, Quan Hoa, Quận Cầu Giấy";

    public CauGiayOffice() {
        cauGiayQueue = new LinkedList<>();
        shipper = new Shipper(this);
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

    public String getOFFICE_ADDRESS() {
        return OFFICE_ADDRESS;
    }

    @Override
    public Shipper getShipper() {
        return shipper ;
    }


    @Override
    public Queue<Packages> getPackageQueue() {
        return cauGiayQueue;
    }


}