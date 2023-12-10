package districtoffice;

import sender_information.Packages;

import java.util.LinkedList;
import java.util.Queue;

public class CauGiayOffice implements Office{

    private Queue<Packages> cauGiayQueue;
    private final String OFFICE_ADDRESS = "165, Đường Cầu Giấy, Quan Hoa, Quận Cầu Giấy";

    public CauGiayOffice() {
        cauGiayQueue = new LinkedList<>();
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

    public Packages getOFFICE_ADDRESS() {
        return new Packages(-1,null,null,OFFICE_ADDRESS,null,-1,null);
    }

    @Override
    public Queue<Packages> getPackageQueue() {
        return cauGiayQueue;
    }


}