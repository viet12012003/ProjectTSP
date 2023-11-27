package transitoffice;

import districtoffice.*;
import sender_information.Packages;

import java.util.PriorityQueue;


public class TransitOffice {
    public static void main(String[] args) {
    }

    public static void classifyPackageByDistrict(Packages packages) {
        // demo
        if (packages.getAddress().equals("Thanh Xuân")) {
            ThanhXuanOffice thanhXuanOffice = new ThanhXuanOffice();
            thanhXuanOffice.deliverToOffice(packages);
        } else if (packages.getAddress().equals("Đống Đa")) {
            DongDaOffice dongDaOffice = new DongDaOffice();
            dongDaOffice.deliverToOffice(packages);
        } else if (packages.getAddress().equals("Ba Đình")) {
            BaDinhOffice baDinhOffice = new BaDinhOffice();
            baDinhOffice.deliverToOffice(packages);
        } else if (packages.getAddress().equals("Tây Hồ")) {
            TayHoOffice tayHoOffice = new TayHoOffice();
            tayHoOffice.deliverToOffice(packages);
        } else if (packages.getAddress().equals("Cầu Giấy")) {
            CauGiayOffice cauGiayOffice = new CauGiayOffice();
            cauGiayOffice.deliverToOffice(packages);
        } else {
            System.out.println("ERROR !");
        }
    }

    public static void classifyPackage(PriorityQueue<Packages> packages) {
        for (int i = 0; i < packages.size(); i++) {
            classifyPackageByDistrict(packages.poll());
        }
    }
}
