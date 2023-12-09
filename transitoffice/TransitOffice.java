package transitoffice;

import districtoffice.*;
import map.CalculateDistanceAndTime;
import sender_information.Packages;

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class TransitOffice {
    private PriorityQueue<Packages> packages;

    public TransitOffice(PriorityQueue<Packages> packages) {
        this.packages = packages;
    }

    public void initialize(JFrame frame) {
        frame = new JFrame();
        frame.setTitle("Transit Office");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        Map<String,Office> districtMap = classifyAndPrintPackages();


        // Tớ in tạm ma trận ra console để mọi người dễ theo dõi cái ma trận kề biểu diễn đồ thị nha
        Office dongda = districtMap.get("Thanh Xuân");
        printGraph(buildNornalGraph(dongda));
    }

    public Map<String, Office> classifyAndPrintPackages() {
        Map<String, Office> districtOfficeMap = new HashMap<>();
        districtOfficeMap.put("Thanh Xuân", new ThanhXuanOffice());
        districtOfficeMap.put("Đống Đa", new DongDaOffice());
        districtOfficeMap.put("Ba Đình", new BaDinhOffice());
        districtOfficeMap.put("Tây Hồ", new TayHoOffice());
        districtOfficeMap.put("Cầu Giấy", new CauGiayOffice());

        // Phân loại gói hàng theo từng quận
        while (!packages.isEmpty()) {
            classifyPackageByDistrict(packages.poll(), districtOfficeMap);
        }

        // In thông tin theo từng quận
        for (Map.Entry<String, Office> entry : districtOfficeMap.entrySet()) {
            String district = entry.getKey();
            Office office = entry.getValue();
            System.out.println("Quận : " + district);
            office.printPackages();
        }
        return districtOfficeMap;
    }

    public void classifyPackageByDistrict(Packages packages, Map<String, Office> districtOfficeMap) {
        String[] districtArray = packages.getAddress().split(",");
        String district = districtArray[districtArray.length - 1].trim();
        if (districtOfficeMap.containsKey(district)) {
            Office office = districtOfficeMap.get(district);
            office.deliverToOffice(packages);
        } else {
            System.out.println("ERROR! Unknown district: " + district);
        }
    }

    // Lấy khoảng cách giữa hai địa điểm
    public double getDistance(Packages packages1, Packages packages2) {
        CalculateDistanceAndTime cal = new CalculateDistanceAndTime(packages1.getAddress(), packages2.getAddress());
        String distance = cal.calculateDistance();
        return extractDistanceValue(distance);
    }

    private double extractDistanceValue(String jsonString) {
        int startIndex = jsonString.indexOf("[[") + 2;
        int endIndex = jsonString.indexOf("]]", startIndex);
        String distanceString = jsonString.substring(startIndex, endIndex);
        return Double.parseDouble(distanceString);
    }


    // Xây dựng ma trận kề biểu diễn quãng đường giữa các địa điểm trong quận ( Đối với đơn hoả tốc )
//    public double[][] buildExpressGraph(Office district){
//
//        ArrayList<Packages> list = new ArrayList<>(); // Tạm thời lưu trữ các gói hàng hoả tốc
//        while ( district.getPackageQueue().peek().getService().equals("Hoả tốc")){
//            // Thêm gói hàng vào list
//            Packages pack = district.getPackageQueue().poll();
//            list.add(pack);
//        }
//
//        int numOfPack = list.size();
//
//        double[][] graph = new double[numOfPack][numOfPack];        // Sử dụng ma trận kề để lưu đồ thị
//
//        Map<Integer,Packages> mapId = new HashMap<>();       // map để lưu các index tương ứng với gói hàng nào
//
//        for (int i = 0; i < numOfPack; i++) {
//            mapId.put(i,list.get(i));
//        }
//
//        // Update các trọng số của đồ thị
//        for (int i = 0; i < graph.length; i++) {
//            for (int j = 0; j < graph.length; j++) {
//                Packages pack1 = mapId.get(i);
//                Packages pack2 = mapId.get(j);
//                // Gọi đến hàm getDistance để lấy khoảng cách
//                double distance = getDistance(pack1,pack2);
//                graph[i][j] = distance;
//            }
//        }
//
//        return graph;
//    }

    // Xây dựng ma trận kề biểu diễn quãng đường giữa các địa điểm trong quận ( Đối với đơn giao hàng thường )
    public double[][] buildNornalGraph(Office district){

        ArrayList<Packages> list = new ArrayList<>(); // Tạm thời lưu trữ các gói hàng thường
        // Vì đã lấy hết đơn hoả tốc nên chỉ cần xét đến khi queue trống
        while (!district.getPackageQueue().isEmpty()){
            // Thêm gói hàng vào list
            Packages pack = district.getPackageQueue().poll();
            list.add(pack);
        }

        int numOfPack = list.size();

        double[][] graph = new double[numOfPack][numOfPack];        // Sử dụng ma trận kề để lưu đồ thị

        Map<Integer,Packages> mapId = new HashMap<>();       // map để lưu các index tương ứng với gói hàng nào

        for (int i = 0; i < numOfPack; i++) {
            mapId.put(i,list.get(i));
        }

        // Update các trọng số của đồ thị
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                Packages pack1 = mapId.get(i);
                Packages pack2 = mapId.get(j);
                // Gọi đến hàm getDistance để lấy khoảng cách
                double distance = getDistance(pack1,pack2);
                graph[i][j] = distance;
            }
        }

        return graph;
    }

    public void printGraph(double[][] graph){
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j]+"\t \t");
            }
            System.out.println();
        }
        System.out.println();
    }

}