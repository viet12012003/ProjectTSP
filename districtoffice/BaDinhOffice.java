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

    @Override
    public void printPackages() {
        for (Packages packages :
                baDinhQueue) {
            System.out.println(packages.toString());
        }
    }

    @Override
    public Queue<Packages> getPackageQueue() {
        return baDinhQueue;
    }

    public static void main(String[] args) {

    }
}
