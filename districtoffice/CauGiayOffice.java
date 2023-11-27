package districtoffice;

import sender_information.Packages;

import java.util.LinkedList;
import java.util.Queue;

public class CauGiayOffice implements Office{
    private Queue<Packages> cauGiayQueue = new LinkedList<>();
    @Override
    public void deliverToOffice(Packages packages) {
        cauGiayQueue.add(packages);
    }

    @Override
    public Packages takePackageToDeliver() {
        return cauGiayQueue.poll();
    }

}
