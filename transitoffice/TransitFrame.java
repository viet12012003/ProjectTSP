/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package transitoffice;

import districtoffice.*;
import sender_information.Packages;
import shipper.Shipper;

import java.awt.Font;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 *
 * @author HAI YEN
 */
public class TransitFrame extends javax.swing.JFrame {

    private PriorityQueue<Packages> packages;

    private Map<String, Office> districtMap;  // Quản lý các quận

    public TransitFrame(PriorityQueue<Packages> packages) {
        this.packages = packages;
        districtMap = classifyPackages();
        initComponents();
        // Đặt kiểu căn giữa cho ô trong JTable
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        shipTable.setDefaultRenderer(Object.class, centerRenderer);
        districtTable.setDefaultRenderer(Object.class, centerRenderer);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        title = new javax.swing.JLabel();
        districtLabel = new javax.swing.JLabel();
        districtComboBox = new javax.swing.JComboBox<>();
        inforTable = new javax.swing.JScrollPane();
        shipTable = new javax.swing.JTable();
        inforTable1 = new javax.swing.JScrollPane();
        districtTable = new javax.swing.JTable();
        titleDistrictTable = new javax.swing.JLabel();
        showButton = new javax.swing.JButton();
        titleShipperTable = new javax.swing.JLabel();
        getPackageButton = new javax.swing.JButton();
        sendPackageButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("THEO DÕI ĐƠN HÀNG");
        setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        setResizable(false);

        title.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        title.setText("THEO DÕI CÁC ĐƠN HÀNG TRONG BƯU CỤC QUẬN VÀ ĐƠN HÀNG TRONG GIỎ HÀNG SHIPPER");

        districtLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        districtLabel.setText(" Quận :");

        districtComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ba Đình", "Đống Đa", "Cầu Giấy", "Thanh Xuân", "Tây Hồ" }));

        shipTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Mã đơn", "Người gửi", "Người nhận", "Địa chỉ nhận", "Tên hàng hoá", "Số điện thoại", "Dịch vụ"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        shipTable.getTableHeader().setReorderingAllowed(false);
        inforTable.setViewportView(shipTable);
        if (shipTable.getColumnModel().getColumnCount() > 0) {
            shipTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            shipTable.getColumnModel().getColumn(1).setPreferredWidth(110);
            shipTable.getColumnModel().getColumn(2).setPreferredWidth(110);
            shipTable.getColumnModel().getColumn(3).setPreferredWidth(320);
        }

        districtTable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        districtTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Mã đơn", "Người gửi", "Người nhận", "Địa chỉ nhận", "Tên hàng hoá", "Số điện thoại", "Dịch vụ"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        districtTable.setGridColor(new java.awt.Color(0, 0, 0));
        districtTable.getTableHeader().setReorderingAllowed(false);
        inforTable1.setViewportView(districtTable);
        if (districtTable.getColumnModel().getColumnCount() > 0) {
            districtTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            districtTable.getColumnModel().getColumn(1).setPreferredWidth(110);
            districtTable.getColumnModel().getColumn(2).setPreferredWidth(110);
            districtTable.getColumnModel().getColumn(3).setPreferredWidth(320);
        }

        titleDistrictTable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        titleDistrictTable.setText("CÁC ĐƠN HÀNG HIỆN TẠI TRONG BƯU CỤC QUẬN");

        showButton.setText("Xem thông tin");
        showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });

        titleShipperTable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        titleShipperTable.setText("CÁC ĐƠN HÀNG HIỆN TẠI TRONG GIỎ HÀNG SHIPPER");

        getPackageButton.setText("Lấy hàng từ bưu cục quận");
        getPackageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getPackageButtonActionPerformed(evt);
            }
        });

        sendPackageButton.setText("Giao hàng");
        sendPackageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendPackageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(inforTable)
                                                        .addComponent(inforTable1)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(287, 287, 287)
                                                .addComponent(districtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(districtComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(68, 68, 68)
                                                .addComponent(showButton)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(195, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(184, 184, 184))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(titleShipperTable, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(230, 230, 230))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(titleDistrictTable, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(178, 178, 178)
                                .addComponent(getPackageButton)
                                .addGap(281, 281, 281)
                                .addComponent(sendPackageButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(districtLabel)
                                        .addComponent(districtComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(showButton))
                                .addGap(40, 40, 40)
                                .addComponent(titleDistrictTable, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inforTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(titleShipperTable, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inforTable, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(getPackageButton)
                                        .addComponent(sendPackageButton))
                                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedDistrict = districtComboBox.getSelectedItem().toString();
        // Thực hiện hành động dựa trên quận được chọn
        Office district = districtMap.get(selectedDistrict);
        titleDistrictTable.setText("CÁC ĐƠN HÀNG HIỆN TẠI TRONG BƯU CỤC QUẬN "+selectedDistrict.toUpperCase());
        titleShipperTable.setText("CÁC ĐƠN HÀNG TRONG GIỎ HÀNG CỦA SHIPPER QUẬN "+selectedDistrict.toUpperCase());
        showPackageQueue(district.getShipper());
        transitDistrict();
    }

    private void getPackageButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Office district = districtMap.get(districtComboBox.getSelectedItem().toString());
        showPackageQueue(district.getShipper());
        // Các công việc xử lý dữ liệu, phân loại, và hiển thị dữ liệu ở đây
        // Nếu shipper của quận đó đang có đơn hàng trong queue chưa giao hết thì không được lấy thêm gói hàng mới
        if (!district.getShipper().getQueue().isEmpty()) {
            showPackageQueue(district.getShipper());
            showDeliveryDetails();
            // Hiển thị thông báo shipper không thể lấy thêm hàng
            JOptionPane.showMessageDialog(rootPane, "Shipper chưa hoàn thành các đơn hàng nên chưa thể nhận đơn mới!");
            return;
        } else if (district.getPackageQueue().isEmpty()) {
            // Không còn đơn hàng nào ở bưu cục thì không thể nhận đơn hàng mới
            // Hiển thị thông báo shipper không thể lấy thêm hàng
            JOptionPane.showMessageDialog(rootPane, "Hiện tại chưa có đơn nào cần được giao cả");
            return;
        }

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
                // Shipper sẽ lấy hàng ở quận để mang đi ship
                // Shipper sẽ được tính toán để đưa ra lộ trình tối ưu nhất
                district.getShipper().getPackages();
                showPackageQueue(district.getShipper());
                // Show các đơn hàng còn lại sau khi shipper đã lấy đi mất 1 số
                showDeliveryDetails();
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

    private void sendPackageButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Office district = districtMap.get(districtComboBox.getSelectedItem().toString());
        if (district.getShipper().getQueue().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Không có đơn hàng nào cần hoàn thành cả, hãy nhận đơn mới nhé!");
        } else {
            // Hiển thị hộp thoại xác nhận
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn hoàn thành "+district.getShipper().getQueue().peek()+"?", "Xác nhận hoàn thành", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // Thực hiện các lệnh sau khi xác nhận
                district.getShipper().ship();
                showPackageQueue(district.getShipper());
            } else {
                // Đóng thông báo và không làm gì
            }
        }
    }




    // Variables declaration - do not modify
    private javax.swing.JComboBox<String> districtComboBox;
    private javax.swing.JLabel districtLabel;
    private javax.swing.JTable districtTable;
    private javax.swing.JButton getPackageButton;
    private javax.swing.JScrollPane inforTable;
    private javax.swing.JScrollPane inforTable1;
    private javax.swing.JButton sendPackageButton;
    private javax.swing.JTable shipTable;
    private javax.swing.JButton showButton;
    private javax.swing.JLabel title;
    private javax.swing.JLabel titleDistrictTable;
    private javax.swing.JLabel titleShipperTable;
    // End of variables declaration


    public void transitDistrict() {
        // Hiển thị các đơn hàng có trong queue của các quận
        showDeliveryDetails();
        showPackageQueue( districtMap.get(districtComboBox.getSelectedItem().toString()).getShipper());
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

    // Show các đơn hàng có trong quận bằng cách lấy từ queue
    public void showDeliveryDetails() {
        // Xóa dữ liệu hiện có trong bảng để hiển thị dữ liệu mới
        Office selectedDistrict = districtMap.get(districtComboBox.getSelectedItem().toString());
        ((DefaultTableModel) districtTable.getModel()).setRowCount(0);

        // Tạo một bản sao của PriorityQueue của quận đã chọn để duyệt qua
        Queue<Packages> tempQueue = copyQueue(selectedDistrict.getPackageQueue());

        // Lấy dữ liệu từ PriorityQueue và hiển thị trên bảng
        while (!tempQueue.isEmpty()) {
            Packages pack = tempQueue.poll();
            ((DefaultTableModel) districtTable.getModel()).addRow(new Object[]{pack.getId(), pack.getSender(), pack.getReceiver(), pack.getAddress(), pack.getGoods(), pack.getPhonenumber(), pack.getService()});
        }
    }

    public void showPackageQueue(Shipper shipper) {
        if (shipper.getQueue().size() == 0){
            while ((shipTable.getModel()).getRowCount() > 0) {
                ((DefaultTableModel) shipTable.getModel()).removeRow(0);
            }
            return;
        }
        // Xoá tất cả các hàng trong bảng trước khi in dữ liệu mới
        while ((shipTable.getModel()).getRowCount() > 0) {
            ((DefaultTableModel) shipTable.getModel()).removeRow(0);
        }
        Queue<Packages> tempQueue = copyQueue(shipper.getQueue());
        // Lấy dữ liệu từ PriorityQueue và hiển thị trên bảng
        while (!tempQueue.isEmpty()) {
            Packages pack = tempQueue.poll();
            ((DefaultTableModel) shipTable.getModel()).addRow(new Object[]{pack.getId(), pack.getSender(), pack.getReceiver(), pack.getAddress(), pack.getGoods(), pack.getPhonenumber(), pack.getService()});
        }
    }


    private Queue<Packages> copyQueue(Queue<Packages> originalQueue) {
        // Tạo một bản sao của PriorityQueue của quận đã chọn để duyệt qua
        Queue<Packages> tempQueue = new LinkedList<>();

        for (Packages pack : originalQueue) {
            tempQueue.add(pack); // Thêm từng phần tử từ hàng đợi gốc vào hàng đợi mới
        }
        return tempQueue;
    }
    private void settingTable(){
        // Lấy JTableHeader của bảng
        JTableHeader header = districtTable.getTableHeader();

        // Tạo một Font mới
        Font headerFont = new Font("Times New Roman", Font.BOLD, 12); // Thay đổi font tại đây

        // Đặt font cho tiêu đề cột
        header.setFont(headerFont);

        // Tạo một TableCellRenderer tùy chỉnh để căn giữa tiêu đề cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa

        // Đặt renderer cho tất cả các cột trong JTableHeader
        for (int i = 0; i < districtTable.getColumnCount(); i++) {
            districtTable.getColumnModel().getColumn(i).setHeaderRenderer(centerRenderer);
        }

        // Lấy JTableHeader của bảng
        JTableHeader header1 = shipTable.getTableHeader();

        // Đặt font cho tiêu đề cột
        header.setFont(headerFont);

        // Đặt renderer cho tất cả các cột trong JTableHeader
        for (int i = 0; i < shipTable.getColumnCount(); i++) {
            shipTable.getColumnModel().getColumn(i).setHeaderRenderer(centerRenderer);
        }

        shipTable.setDefaultRenderer(Object.class, centerRenderer);
        districtTable.setDefaultRenderer(Object.class, centerRenderer);

    }


}
