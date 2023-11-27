package districtoffice;

import sender_information.Packages;

import java.util.LinkedList;
import java.util.Queue;

public class DongDaOffice implements Office {

    private Queue<Packages> dongDaQueue = new LinkedList<>();
    @Override
    public void deliverToOffice(Packages packages) {
        dongDaQueue.add(packages);
    }

    @Override
    public Packages takePackageToDeliver() {
        return dongDaQueue.poll();
    }
}
