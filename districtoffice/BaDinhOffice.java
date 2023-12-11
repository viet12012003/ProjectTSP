package districtoffice;

import sender_information.Packages;
import shipper.Shipper;

import java.util.LinkedList;
import java.util.Queue;

public class BaDinhOffice implements Office{
    private Queue<Packages> baDinhQueue;

    private final String OFFICE_ADDRESS = "218, Doi Can St, Phường Liễu Giai, Ba Đình";

    private Shipper shipper;

    public BaDinhOffice() {
        baDinhQueue = new LinkedList<>();
        shipper = new Shipper(this);
    }


    @Override
    public void deliverToOffice(Packages packages) {
        baDinhQueue.add(packages);
    }

    @Override
    public Packages takePackageToDeliver() {
        return baDinhQueue.poll();
    }

    @Override
    public void printPackages() {
        for (Packages packages :
                baDinhQueue) {
            System.out.println(packages.toString());
        }
    }

    public Packages getOFFICE_ADDRESS() {
        return new Packages(-1,null,null,OFFICE_ADDRESS,null,-1,null);
    }

    @Override
    public Shipper getShipper() {
        return shipper;
    }

    @Override
    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }


    @Override
    public Queue<Packages> getPackageQueue() {
        return baDinhQueue;
    }


}
