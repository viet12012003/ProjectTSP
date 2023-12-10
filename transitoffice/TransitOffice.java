package transitoffice;

import districtoffice.*;
import map.CalculateDistanceAndTime;
import sender_information.Packages;

import java.util.*;
import javax.swing.*;

public class TransitOffice {
    private CalculateDistanceAndTime cal = new CalculateDistanceAndTime();  // Để không phải khởi tạo nhiều lần, tốn thời gian
    private PriorityQueue<Packages> packages;

    public TransitOffice(PriorityQueue<Packages> packages) {
        this.packages = packages;
    }


    // Các thao tác sẽ thực hiện
    public void transit(JFrame frame) {
        frame.dispose();
        JFrame processingFrame = new JFrame();
        processingFrame.setTitle("Trung chuyển hàng hoá đến các quận");
        processingFrame.setBounds(500,400,500,100);
        processingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Sử dụng hiệu ứng vô hạn cho progress bar
        progressBar.setString("Hàng hoá đang được trung chuyển đến các quận và xử lý, vui lòng chờ...");
        progressBar.setStringPainted(true);

        processingFrame.add(progressBar);
        processingFrame.setVisible(true);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Các công việc xử lý dữ liệu, phân loại, và hiển thị dữ liệu ở đây
                // Test với Quận ĐỐng Đa
                Map<String, Office> districtMap = classifyAndPrintPackages();
                Office dongda = districtMap.get("Đống Đa");
                printGraph(buildGraph(dongda));
                return null;
            }

            @Override
            protected void done() {
                processingFrame.dispose(); // Sau khi công việc đã xong, đóng frame hiển thị thông báo
                // Tiếp tục với các hành động sau khi công việc kết thúc (nếu cần)
            }
        };

        worker.execute(); // Bắt đầu thực hiện công việc trong background thread

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
        String distance = cal.calculateDistance(packages1.getAddress(),packages2.getAddress());
        return extractDistanceValue(distance);
    }

    private double extractDistanceValue(String jsonString) {
        int startIndex = jsonString.indexOf("[[") + 2;
        int endIndex = jsonString.indexOf("]]", startIndex);
        String distanceString = jsonString.substring(startIndex, endIndex);
        return Double.parseDouble(distanceString);
    }


    // Xây dựng ma trận kề biểu diễn quãng đường giữa các địa điểm trong quận ( Tính cả điểm xuất phát là bưu cục của từng quận )
    public double[][] buildGraph(Office district){
        ArrayList<Packages> list = new ArrayList<>();
        while (!district.getPackageQueue().isEmpty()){
            // Thêm gói hàng vào list
            Packages pack = district.getPackageQueue().poll();
            list.add(pack);
        }

        int numOfPack = list.size();

        double[][] graph = new double[numOfPack+1][numOfPack+1];        // Sử dụng ma trận kề để lưu đồ thị

        Map<Integer,Packages> mapId = new HashMap<>();       // map để lưu các index tương ứng với gói hàng nào (địa chỉ nào)

        mapId.put(0,district.getOFFICE_ADDRESS());

        for (int i = 0; i < numOfPack ; i++) {
            mapId.put(i+1,list.get(i));
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