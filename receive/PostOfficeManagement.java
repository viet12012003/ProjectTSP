package receive;

import sender_information.Packages;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PostOfficeManagement {
    private PriorityQueue<Packages> packageQueue;
    private JFrame frame;
    private String fileName = "D:\\Workspace\\code\\src\\ProjectTSP\\data.csv";
    public PostOfficeManagement() {
        // packageQueue ưu tiên Hỏa Tốc trước Thường, nếu cùng loại thì ưu tiên theo ID
        this.packageQueue = new PriorityQueue<>(Comparator
                .comparing((Packages p) -> p.getService().equalsIgnoreCase("Hỏa tốc") ? 0 : 1)
                .thenComparing(Packages::getId));
    }

    public void showPostOfficeUI() {
        frame = new JFrame("BƯU CỤC TIẾP NHẬN");
        frame.setSize(1000, 800);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLayout(new BorderLayout());

         // data.inputData();
        // Đọc dữ liệu từ file và thêm vào PriorityQueue
        readPackagesFromFile(fileName);
        // Hiển thị thông tin đã tiếp nhận
        showReceivedPackages();

        frame.setVisible(true);
    }

    private void showReceivedPackages() {
        // Xóa nội dung của frame
        frame.getContentPane().removeAll();

        // Thêm bảng để hiển thị thông tin
        String[] columns = {"Mã đơn hàng", "Người gửi", "Người nhận", "Địa chỉ người nhận", "Tên hàng hóa", "Khối lượng", "Dịch vụ"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        // Đặt độ rộng cố định cho cột "Địa chỉ người nhận"
        table.getColumnModel().getColumn(3).setPreferredWidth(350);

        // Thêm bảng vào JScrollPane để có thể cuộn nếu cần
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 960, 600);
        frame.add(scrollPane);

        // Đặt kiểu căn giữa cho ô trong JTable
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Tạo một bản sao của PriorityQueue để duyệt qua
        PriorityQueue<Packages> tempQueue = new PriorityQueue<>(packageQueue);

        // Lấy dữ liệu từ PriorityQueue và hiển thị trên bảng
        while (!tempQueue.isEmpty()) {
            Packages pack = tempQueue.poll();
            model.addRow(new Object[]{pack.getId(), pack.getSender(), pack.getReceiver(), pack.getAddress(), pack.getGoods(), pack.getWeight(), pack.getService()});
        }


        // Vẽ lại frame
        frame.revalidate();
        frame.repaint();
    }

    public void readPackagesFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 9) {
                    int id = Integer.parseInt(data[0]);
                    String sender = data[1];
                    String receiver = data[2];
                    String address = data[3] + ", " + data[4] + ", " + data[5];
                    String goods = data[6];
                    double weight = Double.parseDouble(data[7]);
                    String service = data[8];

                    Packages pack = new Packages(id, sender, receiver, address, goods, weight, service);
                    packageQueue.offer(pack);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public PriorityQueue<Packages> getPackageQueue() {
        readPackagesFromFile(fileName);
        return packageQueue;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PostOfficeManagement().showPostOfficeUI();
        });
    }
}