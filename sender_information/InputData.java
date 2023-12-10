package sender_information;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class InputData {
    public int autoId = 1;

    public String inputData() {
        //Tao giao dien
        JFrame frame = new JFrame();
        frame.setTitle("THÔNG TIN GỬI HÀNG - BƯU CỤC HÀ NỘI");
        frame.setSize(1100, 800);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Tiêu đề cho phần nhập thông tin
        JLabel infoLabel = new JLabel("ĐĂNG KÝ CHUYỂN PHÁT");
        infoLabel.setBounds(370, 20, 200, 30);
        frame.add(infoLabel);

        JLabel senderLabel = new JLabel("Người gửi:");
        senderLabel.setBounds(250, 50, 100, 30);
        frame.add(senderLabel);

        JTextField senderField = new JTextField();
        senderField.setBounds(350, 50, 200, 30);
        frame.add(senderField);

        JLabel receiverLabel = new JLabel("Người nhận:");
        receiverLabel.setBounds(250, 100, 100, 30);
        frame.add(receiverLabel);

        JTextField receiverField = new JTextField();
        receiverField.setBounds(350, 100, 200, 30);
        frame.add(receiverField);

        JLabel receiverAddressLabel = new JLabel("Địa chỉ nhận:");
        receiverAddressLabel.setBounds(250, 150, 120, 30);
        frame.add(receiverAddressLabel);

        JTextField receiverAddressField = new JTextField();
        receiverAddressField.setBounds(350, 150, 200, 30);
        frame.add(receiverAddressField);

        JLabel goodsNameLabel = new JLabel("Tên hàng hoá:");
        goodsNameLabel.setBounds(250, 200, 100, 30);
        frame.add(goodsNameLabel);

        JTextField goodsNameField = new JTextField();
        goodsNameField.setBounds(350, 200, 200, 30);
        frame.add(goodsNameField);

        JLabel weightLabel = new JLabel("Khối lượng (kg):");
        weightLabel.setBounds(250, 250, 150, 30);
        frame.add(weightLabel);

        JTextField weightField = new JTextField();
        weightField.setBounds(350, 250, 200, 30);
        frame.add(weightField);

        //Chọn Quận
        JLabel districtLabel = new JLabel("Quận:");
        districtLabel.setBounds(250, 300, 150, 30);
        frame.add(districtLabel);

        String[] district = {"Ba Đình", "Cầu Giấy", "Đống Đa", "Thanh Xuân", "Tây Hồ"};
        JComboBox<String> districtCombox = new JComboBox<>(district);
        districtCombox.setBounds(350, 300, 200, 30);
        frame.add(districtCombox);

        //Chọn Phường
        JLabel wardsLabel = new JLabel("Phường: ");
        wardsLabel.setBounds(250, 350, 100, 30);
        frame.add(wardsLabel);

        JComboBox<String> wardCombox = new JComboBox<>();
        wardCombox.setBounds(350, 350, 200, 30);
        frame.add(wardCombox);

        // Tạo bản sao của danh sách phường theo từng quận
        HashMap<String, String[]> wardMap = new HashMap<>();
        wardMap.put("Ba Đình", new String[]{"Cống Vị", "Điện Biên", "Đội Cấn", "Giảng Võ", "Kim Mã", "Liễu Giai", "Ngọc Hà",
                "Ngọc Khánh", "Nguyễn Trung Trực", "Phúc Xá", "Quán Thánh", "Thành Công", "Trúc Bạch", "Vĩnh Phúc"});
        // Thêm phường cho các quận khác tương tự
        wardMap.put("Cầu Giấy",new String[]{"Nghĩa Đô", "Nghĩa Tân", "Mai Dịch", "Dịch Vọng", "Dịch Vọng Hậu", "Quan Hoa", "Yên Hoà", "Trung Hoà"});
        wardMap.put("Đống Đa",new String[]{"Cát Linh", "Văn Miếu", "Quốc Tử Giám", "Láng Thượng", "Láng Hạ", "Ô Chợ Dừa", "Văn Chương",
                "Hàng Bột", "Khâm Thiên", "Thổ Quan", "Nam Đồng", "Trung Phụng", "Quang Trung", "Trung Liệt",
                "Phương Liên", "Thịnh Quang", "Trung Tự", "Kim Liên", "Phương Mai", "Ngã Tư Sở", "Khương Thượng"});
        wardMap.put("Thanh Xuân",new String[]{"Thượng Đình", "Hạ Đình", "Thanh Xuân Bắc", "Thanh Xuân Trung", "Thanh Xuân Nam", "Nhân Chính", "Khương Trung",
                "Khương Mai", "Khương Đình", "Kim Giang", "Phương Liệt"});
        wardMap.put("Tây Hồ",new String[]{"Bưởi", "Nhật Tân", "Phú Thượng", "Quảng An", "Thuỵ Khê", "Tứ Liên", "Xuân La", "Yên Phụ"});
        // Sự kiện khi chọn quận
        districtCombox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDistrict = districtCombox.getSelectedItem().toString();
                String[] wards = wardMap.get(selectedDistrict); // Lấy danh sách phường tương ứng với quận được chọn
                if (wards != null) {
                    wardCombox.removeAllItems();
                    for (String ward : wards) {
                        wardCombox.addItem(ward); // Thêm các phường vào combobox phường
                    }
                }
            }
        });

        JLabel serviceLabel = new JLabel("Dịch vụ:");
        serviceLabel.setBounds(250, 400, 100, 30);
        frame.add(serviceLabel);

        String[] services = {"Hỏa tốc", "Giao hàng thường"};
        JComboBox<String> serviceComboBox = new JComboBox<>(services);
        serviceComboBox.setBounds(350, 400, 200, 30);
        frame.add(serviceComboBox);

        JButton submitButton = new JButton("Xác nhận");
        submitButton.setBounds(390, 450, 100, 30);
        frame.add(submitButton);

        // Thêm bảng để hiển thị thông tin
        String[] columns = {"Mã đơn hàng", "Người gửi", "Người nhận", "Địa chỉ người nhận", "Phường", "Quận", "Tên hàng hoá","Khối lượng", "Dịch vụ"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        // Thêm bảng vào JScrollPane để có thể cuộn nếu cần
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 500, 1000, 200);
        frame.add(scrollPane);


        // Lấy ngày và giờ hiện tại
        Date currentDate = new Date();

        // Định dạng ngày và giờ thành chuỗi không có khoảng trắng
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formattedDateTime = dateFormat.format(currentDate);

        // Tạo tên file với ngày và giờ không có khoảng trắng và định dạng là CSV
        String fileName = formattedDateTime + "_data.csv";

        try {
            FileWriter writer = new FileWriter(fileName, true);
            //  đặt tên cho các cột
            String header = "Người gửi, Người nhận, Địa chỉ cụ thể, Phường, Quận, Tên hàng hoá, Khối lượng,Dịch vụ";
            writer.write(header);
            writer.write(System.lineSeparator());
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        submitButton.addActionListener(e -> {
            String idGoods = Integer.toString(autoId);
            String sender = senderField.getText();
            String receiver = receiverField.getText();
            String receiverAddress = receiverAddressField.getText();
            String wardsAddress = wardCombox.getSelectedItem().toString();
            String districtAddress = districtCombox.getSelectedItem().toString();
            String goodsName = goodsNameField.getText();
            String weight = weightField.getText();
            String service = serviceComboBox.getSelectedItem().toString();


            // Hiển thị thông tin trong bảng
            model.addRow(new Object[]{idGoods, sender, receiver, receiverAddress, wardsAddress, districtAddress, goodsName, weight, service});

            // Xóa nội dung trong các ô nhập để nập dữ liệu mới
            senderField.setText("");
            receiverField.setText("");
            receiverAddressField.setText("");
            goodsNameField.setText("");
            weightField.setText("");
            districtCombox.setSelectedIndex(0);
            wardCombox.setSelectedIndex(0);
            serviceComboBox.setSelectedIndex(0);


            // Ghi dữ liệu vào file CSV
            try {
                FileWriter writer = new FileWriter(fileName, true);
                // Ghi thông tin theo định dạng CSV vào file
                String line = idGoods + "," + sender + "," + receiver + "," + receiverAddress + "," + wardsAddress + "," + districtAddress + "," + goodsName + "," + weight + "," + service;
                writer.write(line);
                writer.write(System.lineSeparator()); // Xuống dòng cho dữ liệu mới
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            autoId++;
        });

        JButton transhipButton = new JButton("Trung chuyển");
        transhipButton.setBounds(390, 710, 120, 30);
        frame.add(transhipButton);

        // Sau khi ấn nút trung chuyển, hàng hoá sẽ được phân loại và xử lí theo thứ tự: đơn hoả tốc -> đơn thường
        transhipButton.addActionListener( e -> {
            frame.dispose();
        });

        frame.setVisible(true);

        return fileName;
    }

    public static void main(String[] args) {
        InputData inputData = new InputData();
        String filename = inputData.inputData();
    }

}