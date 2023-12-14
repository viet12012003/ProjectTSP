/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sender_information;

import receive.PackageQueueManager;
import transitoffice.ProcessFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author HAI YEN
 */
public class InputFrame extends javax.swing.JFrame {
    int autoId = 1;
    String filename = "data2.csv";
    public InputFrame() {
//        setfileName();
        initComponents();
    }
    public void setfileName() {
        // Lấy ngày và giờ hiện tại
        Date currentDate = new Date();

        // Định dạng ngày và giờ thành chuỗi không có khoảng trắng
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedDateTime = dateFormat.format(currentDate);

        // Tạo tên file với ngày và giờ không có khoảng trắng và định dạng là CSV để lưu trữ thông tin mỗi lần chạy chương trình
        String fileName = formattedDateTime + "_data.csv";
        this.filename = fileName;

        try {
            FileWriter writer = new FileWriter(fileName, true);
            //  đặt tên cho các cột
            String header = "Người gửi, Người nhận, Địa chỉ cụ thể, Phường, Quận, Tên hàng hoá, Khối lượng,Dịch vụ";
            writer.write(header);
            writer.write(System.lineSeparator());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        senderLabel = new javax.swing.JLabel();
        receiverLabel = new javax.swing.JLabel();
        addressLable = new javax.swing.JLabel();
        goodLable = new javax.swing.JLabel();
        phoneNumberLabel = new javax.swing.JLabel();
        districtLable = new javax.swing.JLabel();
        wardLable = new javax.swing.JLabel();
        serviceLabel = new javax.swing.JLabel();
        senderText = new javax.swing.JTextField();
        receiverText = new javax.swing.JTextField();
        addressText = new javax.swing.JTextField();
        goodsText = new javax.swing.JTextField();
        phoneNumberText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        inforTable = new javax.swing.JTable();
        confirm = new javax.swing.JButton();
        districtComboBox = new javax.swing.JComboBox<>();
        wardComboBox = new javax.swing.JComboBox<>();
        serviceComboBox = new javax.swing.JComboBox<>();
        processButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("THÔNG TIN GỬI HÀNG - BƯU CỤC HÀ NỘI");
        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        setName("inputFrame"); // NOI18N
        setResizable(false);

        titleLabel.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        titleLabel.setText("ĐĂNG KÝ CHUYỂN PHÁT");

        senderLabel.setText("Người gửi");

        receiverLabel.setText("Người nhận");

        addressLable.setText("Địa chỉ nhận");

        goodLable.setText("Tên hàng hoá");

        phoneNumberLabel.setText("Số điện thoại");

        districtLable.setText("Quận");

        wardLable.setText("Phường");

        serviceLabel.setText("Dịch vụ");

        inforTable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        inforTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Mã đơn ", "Người gửi", "Người nhận", "Địa chỉ", "Phường", "Quận", "Tên hàng hoá", "SĐT", "Dịch vụ"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        inforTable.setSelectionBackground(new java.awt.Color(48, 125, 153));
        inforTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(inforTable);
        if (inforTable.getColumnModel().getColumnCount() > 0) {
            inforTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            inforTable.getColumnModel().getColumn(1).setPreferredWidth(110);
            inforTable.getColumnModel().getColumn(2).setPreferredWidth(110);
            inforTable.getColumnModel().getColumn(3).setPreferredWidth(180);
            inforTable.getColumnModel().getColumn(7).setPreferredWidth(100);
        }

        confirm.setText("Xác nhận");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        districtComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Ba Đình", "Đống Đa", "Cầu Giấy", "Thanh Xuân", "Tây Hồ"}));
        districtComboBox.setAutoscrolls(true);
        districtComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                districtComboBoxItemStateChanged(evt);
            }
        });
        districtComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                districtComboBoxActionPerformed(evt);
            }
        });

        wardComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"None"}));

        serviceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Hoả tốc", "Giao hàng thường"}));

        processButton.setText("Phân loại và sắp xếp");
        processButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(408, 408, 408))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(confirm)
                                                .addGap(455, 455, 455))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(processButton)
                                                .addGap(417, 417, 417))))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(280, 280, 280)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(phoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(wardLable)
                                                                        .addComponent(serviceLabel)
                                                                        .addComponent(districtLable))
                                                                .addGap(34, 34, 34)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(districtComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(wardComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(serviceComboBox, 0, 252, Short.MAX_VALUE)
                                                                        .addComponent(phoneNumberText)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(receiverLabel)
                                                                .addGap(45, 45, 45)
                                                                .addComponent(receiverText, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(addressLable)
                                                                        .addComponent(goodLable, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(goodsText, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                                                        .addComponent(addressText)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(senderLabel)
                                                                .addGap(54, 54, 54)
                                                                .addComponent(senderText, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 354, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(senderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(senderText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(receiverLabel)
                                        .addComponent(receiverText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addressLable))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(goodsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(goodLable))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(phoneNumberText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(phoneNumberLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(districtComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(districtLable, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(wardComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(wardLable))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(serviceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(serviceLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(confirm)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(processButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
        );

        pack();
        settingTable();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void districtComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {
        // TODO add your handling code here:
        // Tạo bản sao của danh sách phường theo từng quận
        HashMap<String, String[]> wardMap = new HashMap<>();
        wardMap.put("Ba Đình", new String[]{"Cống Vị", "Điện Biên", "Đội Cấn", "Giảng Võ", "Kim Mã", "Liễu Giai", "Ngọc Hà",
                "Ngọc Khánh", "Nguyễn Trung Trực", "Phúc Xá", "Quán Thánh", "Thành Công", "Trúc Bạch", "Vĩnh Phúc"});
        // Thêm phường cho các quận khác tương tự
        wardMap.put("Cầu Giấy", new String[]{"Nghĩa Đô", "Nghĩa Tân", "Mai Dịch", "Dịch Vọng", "Dịch Vọng Hậu", "Quan Hoa", "Yên Hoà", "Trung Hoà"});
        wardMap.put("Đống Đa", new String[]{"Cát Linh", "Văn Miếu", "Quốc Tử Giám", "Láng Thượng", "Láng Hạ", "Ô Chợ Dừa", "Văn Chương",
                "Hàng Bột", "Khâm Thiên", "Thổ Quan", "Nam Đồng", "Trung Phụng", "Quang Trung", "Trung Liệt",
                "Phương Liên", "Thịnh Quang", "Trung Tự", "Kim Liên", "Phương Mai", "Ngã Tư Sở", "Khương Thượng"});
        wardMap.put("Thanh Xuân", new String[]{"Thượng Đình", "Hạ Đình", "Thanh Xuân Bắc", "Thanh Xuân Trung", "Thanh Xuân Nam", "Nhân Chính", "Khương Trung",
                "Khương Mai", "Khương Đình", "Kim Giang", "Phương Liệt"});
        wardMap.put("Tây Hồ", new String[]{"Bưởi", "Nhật Tân", "Phú Thượng", "Quảng An", "Thuỵ Khê", "Tứ Liên", "Xuân La", "Yên Phụ"});
        // Sự kiện khi chọn quận
        String selectedDistrict = (String) districtComboBox.getSelectedItem();
        String[] wards = wardMap.get(selectedDistrict); // Lấy danh sách phường tương ứng với quận được chọn
        if (wards != null) {
            wardComboBox.removeAllItems();
            for (String ward : wards) {
                wardComboBox.addItem(ward); // Thêm các phường vào combobox phường
            }
        }
    }

    private void processButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        PackageQueueManager packageQueueManager = new PackageQueueManager(filename);
        new ProcessFrame(packageQueueManager);
    }

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {
        String idGoods = Integer.toString(autoId);
        String sender = senderText.getText();
        String receiver = receiverText.getText();
        String receiverAddress = addressText.getText();
        String wardsAddress = wardComboBox.getSelectedItem().toString();
        String districtAddress = districtComboBox.getSelectedItem().toString();
        String goodsName = goodsText.getText();
        String phone = phoneNumberText.getText();
        String service = serviceComboBox.getSelectedItem().toString();

        if (sender.length() == 0 || receiver.length() == 0 || receiverAddress.length() == 0 || goodsName.length() == 0 || phone.length() == 0 || wardsAddress.equals("None")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng không để trống thông tin!");
            return;
        }
        if (!isValidAddress(receiverAddress)) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng không sử dụng dấu ',' trong mục địa chỉ nhận!");
            addressText.setText("");
            return;
        }
        if (!isValidPhoneNumber(phone)) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập đúng định dạng số điện thoại!");
            phoneNumberText.setText("");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) inforTable.getModel();

        // Tạo một mảng chứa dữ liệu cho hàng mới
        Object[] newRow = {idGoods, sender, receiver, receiverAddress, wardsAddress, districtAddress, goodsName, phone, service};

        // Thêm hàng mới vào table thông qua model
        model.addRow(newRow);

        // Ghi dữ liệu vào file CSV
        try {
            FileWriter writer = new FileWriter(filename, true);
            // Ghi thông tin theo định dạng CSV vào file
            String line = idGoods + "," + sender + "," + receiver + "," + receiverAddress + "," + wardsAddress + "," + districtAddress + "," + goodsName + "," + phone + "," + service;
            writer.write(line);
            writer.write(System.lineSeparator()); // Xuống dòng cho dữ liệu mới
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Xóa nội dung trong các ô nhập để nhập dữ liệu mới
        senderText.setText("");
        receiverText.setText("");
        addressText.setText("");
        goodsText.setText("");
        phoneNumberText.setText("");
        districtComboBox.setSelectedIndex(0);
        wardComboBox.setSelectedIndex(0);
        serviceComboBox.setSelectedIndex(0);

        //Tăng mã đơn hàng
        autoId++;
    }

    private void districtComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        // Tạo bản sao của danh sách phường theo từng quận
        HashMap<String, String[]> wardMap = new HashMap<>();
        wardMap.put("Ba Đình", new String[]{"Cống Vị", "Điện Biên", "Đội Cấn", "Giảng Võ", "Kim Mã", "Liễu Giai", "Ngọc Hà",
                "Ngọc Khánh", "Nguyễn Trung Trực", "Phúc Xá", "Quán Thánh", "Thành Công", "Trúc Bạch", "Vĩnh Phúc"});
        // Thêm phường cho các quận khác tương tự
        wardMap.put("Cầu Giấy", new String[]{"Nghĩa Đô", "Nghĩa Tân", "Mai Dịch", "Dịch Vọng", "Dịch Vọng Hậu", "Quan Hoa", "Yên Hoà", "Trung Hoà"});
        wardMap.put("Đống Đa", new String[]{"Cát Linh", "Văn Miếu", "Quốc Tử Giám", "Láng Thượng", "Láng Hạ", "Ô Chợ Dừa", "Văn Chương",
                "Hàng Bột", "Khâm Thiên", "Thổ Quan", "Nam Đồng", "Trung Phụng", "Quang Trung", "Trung Liệt",
                "Phương Liên", "Thịnh Quang", "Trung Tự", "Kim Liên", "Phương Mai", "Ngã Tư Sở", "Khương Thượng"});
        wardMap.put("Thanh Xuân", new String[]{"Thượng Đình", "Hạ Đình", "Thanh Xuân Bắc", "Thanh Xuân Trung", "Thanh Xuân Nam", "Nhân Chính", "Khương Trung",
                "Khương Mai", "Khương Đình", "Kim Giang", "Phương Liệt"});
        wardMap.put("Tây Hồ", new String[]{"Bưởi", "Nhật Tân", "Phú Thượng", "Quảng An", "Thuỵ Khê", "Tứ Liên", "Xuân La", "Yên Phụ"});
        // Sự kiện khi chọn quận
        String selectedDistrict = (String) districtComboBox.getSelectedItem();
        String[] wards = wardMap.get(selectedDistrict); // Lấy danh sách phường tương ứng với quận được chọn
        if (wards != null) {
            wardComboBox.removeAllItems();
            for (String ward : wards) {
                wardComboBox.addItem(ward); // Thêm các phường vào combobox phường
            }
        }
    }

    private boolean isValidAddress(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ',') {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPhoneNumber(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) < '0' || text.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    private void settingTable() {
        // Lấy JTableHeader của bảng
        JTableHeader header = inforTable.getTableHeader();
        // Tạo một Font mới
        Font headerFont = new Font("Times New Roman", Font.BOLD, 12); // Thay đổi font tại đây
        // Đặt font cho tiêu đề cột
        header.setFont(headerFont);
        // Tạo một TableCellRenderer tùy chỉnh để căn giữa tiêu đề cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        inforTable.setDefaultRenderer(Object.class, centerRenderer);
        // Đặt renderer cho tất cả các cột trong JTableHeader
        for (int i = 0; i < inforTable.getColumnCount(); i++) {
            inforTable.getColumnModel().getColumn(i).setHeaderRenderer(centerRenderer);
        }
    }


    // Variables declaration - do not modify
    private javax.swing.JLabel addressLable;
    private javax.swing.JTextField addressText;
    private javax.swing.JButton confirm;
    private javax.swing.JComboBox<String> districtComboBox;
    private javax.swing.JLabel districtLable;
    private javax.swing.JLabel goodLable;
    private javax.swing.JTextField goodsText;
    private javax.swing.JTable inforTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JTextField phoneNumberText;
    private javax.swing.JButton processButton;
    private javax.swing.JLabel receiverLabel;
    private javax.swing.JTextField receiverText;
    private javax.swing.JLabel senderLabel;
    private javax.swing.JTextField senderText;
    private javax.swing.JComboBox<String> serviceComboBox;
    private javax.swing.JLabel serviceLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JComboBox<String> wardComboBox;
    private javax.swing.JLabel wardLable;
    // End of variables declaration
}
