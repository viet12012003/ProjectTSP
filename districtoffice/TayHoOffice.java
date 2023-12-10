package districtoffice;

import sender_information.Packages;

import java.util.LinkedList;
import java.util.Queue;

public class TayHoOffice implements Office {
    private Queue<Packages> tayHoQueue;
    private final String OFFICE_ADDRESS = "588, Đường Lạc Long Quân, Phường Nhật Tân, Tây Hồ";

    public TayHoOffice() {
        tayHoQueue = new LinkedList<>();
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

    public Packages getOFFICE_ADDRESS() {
        return new Packages(-1,null,null,OFFICE_ADDRESS,null,-1,null);
    }
}
