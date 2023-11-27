package districtoffice;

import sender_information.Packages;

import java.util.LinkedList;
import java.util.Queue;

public class BaDinhOffice implements Office{
    private Queue<Packages> baDinhQueue = new LinkedList<>();
    @Override
    public void deliverToOffice(Packages packages) {
        baDinhQueue.add(packages);
    }

    @Override
    public Packages takePackageToDeliver() {
        return baDinhQueue.poll();
    }
}
