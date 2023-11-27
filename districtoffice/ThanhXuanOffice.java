package districtoffice;

import sender_information.Packages;

import java.util.LinkedList;
import java.util.Queue;

public class ThanhXuanOffice implements Office {
    private Queue<Packages> thanhXuanQueue = new LinkedList<>();
    public void deliverToOffice(Packages packages) {
        thanhXuanQueue.add(packages);
    }

    @Override
    public Packages takePackageToDeliver() {
        return thanhXuanQueue.poll();
    }

}
