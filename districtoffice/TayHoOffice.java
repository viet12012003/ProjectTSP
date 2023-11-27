package districtoffice;

import sender_information.Packages;

import java.util.LinkedList;
import java.util.Queue;

public class TayHoOffice implements Office {
    private Queue<Packages> tayHoQueue = new LinkedList<>();
    @Override
    public void deliverToOffice(Packages packages) {
        tayHoQueue.add(packages);
    }

    @Override
    public Packages takePackageToDeliver() {
        return tayHoQueue.poll();
    }
}
