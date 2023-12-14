package districtoffice;

import sender_information.Packages;
import shipper.Shipper;

import java.util.Queue;

public  interface Office {

     void deliverToOffice(Packages packages);
     Packages takePackageToDeliver();
     void printPackages();
     Queue<Packages> getPackageQueue();

     String getOFFICE_ADDRESS();

     Shipper getShipper();


}
