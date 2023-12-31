package shipper;

import districtoffice.DistrictOffice;
import map.CalculateDistanceAndTime;
import sender_information.Packages;
import tsp.SimulatedAnnealing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Shipper {
    private CalculateDistanceAndTime cal;  // Để tính toán quãng đường thực tế sử dụng API của Google Map
    private DistrictOffice district;  // Quận mà shipper đang giao hàng
    private Queue<Packages> queue;  // Hàng đợi lưu trữ các gói hàng của shipper

    // Khoi tao shipper cho tung quan
    public Shipper(DistrictOffice district) {
        this.district = district;
        cal = new CalculateDistanceAndTime();
        queue = new LinkedList<>();
    }

    public Queue<Packages> getQueue() {
        return queue;
    }


    // Chỉ lấy 5 gói hàng mỗi lần, sau khi lấy thực hiện việc tối ưu hoá quãng đường
    public void getPackages() {
        for (int i = 0; i < 5; i++) {
            // Lấy hàng từ bưu cục quận cho vào queue của shipper
            Packages packages = district.takePackageToDeliver();
            queue.add(packages);
        }
        //Tối ưu lộ trình
        findBestRoute();
    }

    // Lấy khoảng cách giữa hai địa điểm
    public double getDistance(Packages packages1, Packages packages2) {
        String distance = cal.calculateDistance(packages1.getAddress(), packages2.getAddress());
        return extractDistanceValue(distance);
    }

    private double extractDistanceValue(String jsonString) {
        int startIndex = jsonString.indexOf("[[") + 2;
        int endIndex = jsonString.indexOf("]]", startIndex);
        String distanceString = jsonString.substring(startIndex, endIndex);
        return Double.parseDouble(distanceString);
    }


    // Xây dựng ma trận kề biểu diễn quãng đường giữa các địa điểm trong quận (Tính cả điểm xuất phát là bưu cục của từng quận)
    // Sử dụng ma trận vừa tạo để tính toán quãng đường ngắn nhất
    public void findBestRoute() {
        int numOfPack = queue.size();
        // Sử dụng ma trận kề để lưu đồ thị
        double[][] graph = new double[numOfPack + 1][numOfPack + 1];
        // map để lưu các index tương ứng với gói hàng nào (địa chỉ nào)
        Map<Integer, Packages> mapId = new HashMap<>();
        // Địa điểm xuất phát là vị trí bưu cục của các quận
        Packages districtAddress = new Packages(-1, null,null, district.getOfficeAddress(), null,null,null);
        mapId.put(0, districtAddress);
        // Lấy các gói hàng từ queue của ship và thêm vào map
        for (int i = 1; i < numOfPack + 1; i++) {
            mapId.put(i, queue.poll());
        }
        // Update các trọng số của đồ thị vào ma trận
        graph = buildGraph(mapId, graph);
        // Gọi đến hàm tính toán đường đi tối ưu
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();
        // Lưu trữ lộ trình tối ưu trong 1 mảng
        int[] result = simulatedAnnealing.simulatedAnnealing(graph);
        // Lưu lại các gói hàng vào queue của shipper sau khi tối ưu
        for (int i = 1; i < result.length - 1; i++) {
            queue.add(mapId.get(result[i]));
        }
    }

    public double[][] buildGraph(Map<Integer, Packages> mapId, double[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if ( i == j ){
                    graph[i][j] = 0;
                    continue;
                }
                Packages pack1 = mapId.get(i);
                Packages pack2 = mapId.get(j);
                // Gọi đến hàm getDistance để lấy khoảng cách
                double distance = getDistance(pack1, pack2);
                graph[i][j] = distance;
            }
        }
        return graph;
    }

    public void ship(){
        // Xoá bớt đơn hàng trong queue
        queue.poll();
    }

}
