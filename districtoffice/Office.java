package districtoffice;

import sender_information.Packages;

import java.util.List;
import java.util.Queue;

public  interface Office {
     void deliverToOffice(Packages packages);
     Packages takePackageToDeliver();
     void printPackages();
     Queue<Packages> getPackageQueue();

}
