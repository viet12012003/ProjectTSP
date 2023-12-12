package districtoffice;

import sender_information.Packages;
import shipper.Shipper;

import java.util.LinkedList;
import java.util.Queue;

public class ThanhXuanOffice implements Office {
    private Shipper shipper;
    private final String OFFICE_ADDRESS = "51, Đường Vũ Trọng Phụng, Phường Thanh Xuân Trung, Thanh Xuân";
    private Queue<Packages> thanhXuanQueue;

    public ThanhXuanOffice() {
        thanhXuanQueue = new LinkedList<>();
        this.shipper = new Shipper(this);
    }

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

    @Override
    public Shipper getShipper() {
        return shipper;
    }

    @Override
    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public Packages getOFFICE_ADDRESS() {
        return new Packages(-1,null,null,OFFICE_ADDRESS,null,null,null);
    }
}
