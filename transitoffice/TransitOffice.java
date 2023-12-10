package transitoffice;

import districtoffice.*;
import map.CalculateDistanceAndTime;
import sender_information.Packages;
import tsp.SimulatedAnnealing;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TransitOffice {
    private CalculateDistanceAndTime cal ;  // Để không phải khởi tạo nhiều lần, tốn thời gian
    private PriorityQueue<Packages> packages;

    private Map<String,Office> districtMap;  // Quản lý các quận

    public TransitOffice(PriorityQueue<Packages> packages) {
        this.packages = packages;
        districtMap = classifyPackages();
        cal = new CalculateDistanceAndTime();
    }

    public void transitMenu(JFrame frame){
        frame.dispose();
        frame = new JFrame();
        frame.setTitle("Xem lộ trình giao hàng");
        frame.setBounds(300,50,1100,800);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        // Tạo combobox chứa các quận
        String[] districts = {"Ba Đình", "Cầu Giấy", "Đống Đa", "Thanh Xuân", "Tây Hồ"};
        JComboBox<String> districtComboBox = new JComboBox<>(districts);
        districtComboBox.setBounds(180, 30, 150, 30);
        frame.add(districtComboBox);

        JButton selectButton = new JButton("Xem các đơn hàng và lộ trình tối ưu");
        selectButton.setBounds(330, 30, 400, 30);
        frame.add(selectButton);

        // Thêm bảng để hiển thị thông tin
        String[] columns = {"Mã đơn hàng", "Người gửi", "Người nhận", "Địa chỉ người nhận", "Tên hàng hóa", "Khối lượng", "Dịch vụ"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        // Đặt độ rộng cố định cho cột "Địa chỉ người nhận"
        table.getColumnModel().getColumn(3).setPreferredWidth(350);

        // Thêm bảng vào JScrollPane để có thể cuộn nếu cần
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 70, 960, 300);
        frame.add(scrollPane);

        // Đặt kiểu căn giữa cho ô trong JTable
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        selectButton.addActionListener(e -> {
            String selectedDistrict = districtComboBox.getSelectedItem().toString();
            // Thực hiện hành động dựa trên quận được chọn
            transitDistrict(selectedDistrict, table, model);
        });

        frame.setVisible(true);

    }
    // Các thao tác sẽ thực hiện khi chọn Quận
    public void transitDistrict( String district, JTable table, DefaultTableModel model) {
        // Hiển thị các đơn hàng có trong queue của các quận
        showDeliveryDetails(district,table, model);

        JFrame processingFrame = new JFrame();
        processingFrame.setTitle("Tối ưu hoá quãng đường giao hàng");
        processingFrame.setBounds(500,400,500,100);
        processingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Sử dụng hiệu ứng vô hạn cho progress bar
        progressBar.setString("Đang tính toán đưa ra lộ trình tối ưu nhất, vui lòng chờ...");
        progressBar.setStringPainted(true);

        processingFrame.add(progressBar);
        processingFrame.setVisible(true);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Các công việc xử lý dữ liệu, phân loại, và hiển thị dữ liệu ở đây
                // Test với Quận ĐỐng Đa
                Office districtOf = districtMap.get(district);
                double[][] graph = buildGraph(districtOf);
                SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();;
                System.out.println(Arrays.toString(simulatedAnnealing.simulatedAnnealing(graph)));
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

    private void showDeliveryDetails(String selectedDistrict, JTable table, DefaultTableModel model) {
        // Xóa dữ liệu hiện có trong bảng để hiển thị dữ liệu mới
        model.setRowCount(0);
        Office district = districtMap.get(selectedDistrict);

        // Tạo một bản sao của PriorityQueue của quận đã chọn để duyệt qua
        Queue<Packages> tempQueue = copyQueue(district.getPackageQueue());

        // Lấy dữ liệu từ PriorityQueue và hiển thị trên bảng
        while (!tempQueue.isEmpty()) {
            Packages pack = tempQueue.poll();
            model.addRow(new Object[]{pack.getId(), pack.getSender(), pack.getReceiver(), pack.getAddress(), pack.getGoods(), pack.getWeight(), pack.getService()});
        }
        table.setModel(model);
    }

    public Map<String, Office> classifyPackages() {
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


    // Xây dựng ma trận kề biểu diễn quãng đường giữa các địa điểm trong quận (Tính cả điểm xuất phát là bưu cục của từng quận)
    public double[][] buildGraph(Office district) {
        int numOfPack = district.getPackageQueue().size();
        // Sử dụng ma trận kề để lưu đồ thị
        double[][] graph = new double[numOfPack+1][numOfPack+1];
        // map để lưu các index tương ứng với gói hàng nào (địa chỉ nào)
        Map<Integer,Packages> mapId = new HashMap<>();
        // Địa điểm xuất phát là vị trí bưu cục của các quận
        mapId.put(0,district.getOFFICE_ADDRESS());
        // Tạo bản sao của queue chứa các packages để không làm mất dữ liệu
        Queue<Packages> tempQueue = copyQueue(district.getPackageQueue());
        for (int i = 1 ; i < numOfPack + 1 ; i++) {
            mapId.put(i ,tempQueue.poll());
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

    public Queue<Packages> copyQueue(Queue<Packages> originalQueue){
        // Tạo một bản sao của PriorityQueue của quận đã chọn để duyệt qua
        Queue<Packages> tempQueue = new LinkedList<>();

        for (Packages pack : originalQueue) {
            tempQueue.add(pack); // Thêm từng phần tử từ hàng đợi gốc vào hàng đợi mới
        }
        return tempQueue;
    }
}