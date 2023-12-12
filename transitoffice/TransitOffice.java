package transitoffice;

import districtoffice.*;
import sender_information.Packages;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Queue;
import java.util.*;

public class TransitOffice {
    private PriorityQueue<Packages> packages;

    private Map<String, Office> districtMap;  // Quản lý các quận

    public TransitOffice(PriorityQueue<Packages> packages) {
        this.packages = packages;
        districtMap = classifyPackages();
    }

    public void transitMenu(JFrame frame) {
        frame.dispose();
        frame = new JFrame();
        frame.setTitle("Xem lộ trình giao hàng");
        frame.setBounds(300, 25, 1100, 830);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);


        // Tạo combobox chứa các quận
        String[] districts = {"Ba Đình", "Cầu Giấy", "Đống Đa", "Thanh Xuân", "Tây Hồ"};
        JComboBox<String> districtComboBox = new JComboBox<>(districts);
        districtComboBox.setBounds(180, 30, 150, 30);
        frame.add(districtComboBox);

        JButton selectButton = new JButton("Xem các đơn hàng trong bưu cục quận và đơn hàng của shipper");
        selectButton.setBounds(330, 30, 600, 30);
        frame.add(selectButton);

        JLabel districtLabel = new JLabel("CÁC ĐƠN HÀNG CÒN LẠI TRONG BƯU CỤC QUẬN");
        districtLabel.setBounds(400, 70, 500, 30);
        frame.add(districtLabel);

        // Thêm bảng để hiển thị thông tin đơn hàng trong bưu cục quận
        String[] columns = {"Mã đơn hàng", "Người gửi", "Người nhận", "Địa chỉ người nhận", "Tên hàng hóa", "Khối lượng", "Dịch vụ"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        // Đặt độ rộng cố định cho cột "Địa chỉ người nhận"
        table.getColumnModel().getColumn(3).setPreferredWidth(350);

        // Thêm bảng vào JScrollPane để có thể cuộn nếu cần
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 960, 250);
        frame.add(scrollPane);

        // Đặt kiểu căn giữa cho ô trong JTable
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        JLabel shipperLabel = new JLabel("CÁC ĐƠN HÀNG SHIPPER ĐANG GIAO");
        shipperLabel.setBounds(420, 450, 500, 30);
        frame.add(shipperLabel);

        // Thêm bảng để hiển thị thông tin queue của Shipper
        String[] columnsShip = {"Mã đơn hàng", "Người gửi", "Người nhận", "Địa chỉ người nhận", "Tên hàng hóa", "Khối lượng", "Dịch vụ"};
        DefaultTableModel modelShip = new DefaultTableModel(columnsShip, 0);
        JTable tableShip = new JTable(modelShip);

        // Đặt độ rộng cố định cho cột "Địa chỉ người nhận"
        tableShip.getColumnModel().getColumn(3).setPreferredWidth(350);

        // Thêm bảng vào JScrollPane để có thể cuộn nếu cần
        JScrollPane scrollPaneShip = new JScrollPane(tableShip);
        scrollPaneShip.setBounds(50, 480, 960, 250);
        frame.add(scrollPaneShip);

        // Đặt kiểu căn giữa cho ô trong JTable
        DefaultTableCellRenderer centerRendererShip = new DefaultTableCellRenderer();
        centerRendererShip.setHorizontalAlignment(JLabel.CENTER);
        tableShip.setDefaultRenderer(Object.class, centerRendererShip);


        JFrame finalFrame = frame;
        selectButton.addActionListener(e -> {
            String selectedDistrict = districtComboBox.getSelectedItem().toString();
            // Thực hiện hành động dựa trên quận được chọn
            Office district = districtMap.get(selectedDistrict);
            district.getShipper().show(tableShip, modelShip);
            transitDistrict(district, table, model, finalFrame, tableShip, modelShip,districtComboBox);
        });

        frame.setVisible(true);

    }

    // Các thao tác sẽ thực hiện khi chọn Quận
    public void transitDistrict(Office district, JTable table, DefaultTableModel model, JFrame frame, JTable table2, DefaultTableModel model2,JComboBox<String>districtComboBox) {
        // Hiển thị các đơn hàng có trong queue của các quận
        showDeliveryDetails(district, table, model);
        System.out.println("Selected District: " + district);
        JButton shipperSelectButton = new JButton("Shipper lấy hàng (5 đơn 1 lượt)");
        shipperSelectButton.setBounds(330, 400, 400, 30);
        frame.add(shipperSelectButton);

        shipperSelectButton.addActionListener(e -> {
            JFrame processingFrame = new JFrame();
            processingFrame.setTitle("Shipper đang lấy hàng");
            processingFrame.setBounds(500, 400, 600, 100);
            processingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JProgressBar progressBar = new JProgressBar();
            progressBar.setIndeterminate(true); // Sử dụng hiệu ứng vô hạn cho progress bar
            progressBar.setString("Đang tính toán đưa ra lộ trình tối ưu nhất, vui lòng không thoát chương trình...");
            progressBar.setStringPainted(true);

            processingFrame.add(progressBar);
            processingFrame.setVisible(true);

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    Office newDistrict = districtMap.get(districtComboBox.getSelectedItem().toString());
                    newDistrict.getShipper().show(table2, model2);
                    // Các công việc xử lý dữ liệu, phân loại, và hiển thị dữ liệu ở đây
                    // Nếu shipper của quận đó đang có đơn hàng trong queue chưa giao hết thì không được lấy thêm gói hàng mới
                    if (!newDistrict.getShipper().getQueue().isEmpty()) {
                        newDistrict.getShipper().show(table2, model2);
                        showDeliveryDetails(newDistrict, table, model);
                        // Hiển thị thông báo shipper không thể lấy thêm hàng
                        JOptionPane.showMessageDialog(frame, "Shipper chưa hoàn thành các đơn hàng nên chưa thể nhận đơn mới!");
                    } else if (newDistrict.getPackageQueue().isEmpty()){
                        // Không còn đơn hàng nào ở bưu cục thì không thể nhận đơn hàng mới
                        // Hiển thị thông báo shipper không thể lấy thêm hàng
                        JOptionPane.showMessageDialog(frame, "Hiện tại chưa có đơn nào cần được giao cả");
                    } else {
                        // Shipper sẽ lấy hàng ở quận để mang đi ship
                        // Shipper sẽ được tính toán để đưa ra lộ trình tối ưu nhất
                        newDistrict.getShipper().getPackages();
                        newDistrict.getShipper().show(table2, model2);
                        // Show các đơn hàng còn lại sau khi shipper đã lấy đi mất 1 số
                        showDeliveryDetails(newDistrict, table, model);
                    }
                    return null;
                }
                @Override
                protected void done() {
                    processingFrame.dispose(); // Sau khi công việc đã xong, đóng frame hiển thị thông báo
                    // Tiếp tục với các hành động sau khi công việc kết thúc (nếu cần)

                    // Thêm nút giao hàng và nút xác nhận cho shipper
                    JButton shipButton = new JButton("Giao hàng");
                    shipButton.setBounds(450, 750, 100, 30);
                    frame.add(shipButton);
                    // Sau khi nhấn nút Giao đơn, sẽ có thông báo bạn có chắc chắn đã hoàn thành giao đơn hàng [...]
                    // Nếu không có đơn hàng nào trong queue sẽ hiển thị thông báo
                    JFrame finalFrame1 = frame;
                    shipButton.addActionListener(k -> {
                        Office newDistrict3 = districtMap.get(districtComboBox.getSelectedItem().toString());
                        if (newDistrict3.getShipper().getQueue().isEmpty()) {
                            JOptionPane.showMessageDialog(finalFrame1, "Không có đơn hàng nào cần hoàn thành cả, hãy nhận đơn mới nhé!");
                        } else {
                            // Hiển thị hộp thoại xác nhận
                            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn hoàn thành đơn hàng "+newDistrict3.getShipper().getQueue().peek()+"?", "Xác nhận hoàn thành", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                // Thực hiện các lệnh sau khi xác nhận
                                newDistrict3.getShipper().ship(finalFrame1);
                                newDistrict3.getShipper().show(table2, model2);
                            } else {
                                // Đóng thông báo và không làm gì
                            }
                        }
                    });
                    frame.revalidate(); // Cập nhật giao diện
                    frame.repaint(); // Vẽ lại giao diện
                    frame.setVisible(true); // Hiển thị lại khung cảnh sau khi thêm các nút
                }
            };
            worker.execute(); // Bắt đầu thực hiện công việc trong background thread

        });

    }

    // Show các đơn hàng có trong quận bằng cách lấy từ queue
    private void showDeliveryDetails(Office selectedDistrict, JTable table, DefaultTableModel model) {
        // Xóa dữ liệu hiện có trong bảng để hiển thị dữ liệu mới
        model.setRowCount(0);

        // Tạo một bản sao của PriorityQueue của quận đã chọn để duyệt qua
        Queue<Packages> tempQueue = copyQueue(selectedDistrict.getPackageQueue());

        // Lấy dữ liệu từ PriorityQueue và hiển thị trên bảng
        while (!tempQueue.isEmpty()) {
            Packages pack = tempQueue.poll();
            model.addRow(new Object[]{pack.getId(), pack.getSender(), pack.getReceiver(), pack.getAddress(), pack.getGoods(), pack.getWeight(), pack.getService()});
        }
        table.setModel(model);
    }


    // Phân chia các gói hàng vào các quận
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

    public Queue<Packages> copyQueue(Queue<Packages> originalQueue) {
        // Tạo một bản sao của PriorityQueue của quận đã chọn để duyệt qua
        Queue<Packages> tempQueue = new LinkedList<>();

        for (Packages pack : originalQueue) {
            tempQueue.add(pack); // Thêm từng phần tử từ hàng đợi gốc vào hàng đợi mới
        }
        return tempQueue;
    }


}