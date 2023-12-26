package districtoffice;

import sender_information.Packages;
import shipper.Shipper;

import java.util.LinkedList;
import java.util.Queue;

public class DistrictOffice {
    private String name;
    private Queue<Packages> queue;

    private String officeAddress ;

    private Shipper shipper;

    public DistrictOffice(String name, String officeAddress) {
        this.name = name;
        this.officeAddress = officeAddress;
        queue = new LinkedList<>();
        shipper = new Shipper(this);
    }


    public void deliverToOffice(Packages packages) {
        queue.add(packages);
    }

    public Packages takePackageToDeliver() {
        return queue.poll();
    }


    public String getOfficeAddress() {
        return officeAddress;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public Queue<Packages> getPackageQueue() {
        return queue;
    }

    @Override
    public String toString() {
        return "DistrictOffice{" +
                "name='" + name + '\'' +
                ", address='" + officeAddress + '\'' +
                '}';
    }
}
