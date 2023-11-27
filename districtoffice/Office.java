package districtoffice;

import sender_information.Packages;

public  interface Office {
     void deliverToOffice(Packages packages);
     Packages takePackageToDeliver();
}
