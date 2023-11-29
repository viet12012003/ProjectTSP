package receive;

import sender_information.Packages;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.PriorityQueue;

public class PostOfficeUI {
    private JFrame frame;
    private PackageQueueManager packageQueueManager;

    public PostOfficeUI() {
        frame = new JFrame("BƯU CỤC TIẾP NHẬN");
        frame.setSize(1000, 800);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        packageQueueManager = new PackageQueueManager();

        // Hiển thị thông tin đã tiếp nhận
        showReceivedPackages();

        frame.setVisible(true);
    }

    public void showReceivedPackages() {
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
        PriorityQueue<Packages> tempQueue = new PriorityQueue<>(packageQueueManager.getPackageQueue());

        // Lấy dữ liệu từ PriorityQueue và hiển thị trên bảng
        while (!tempQueue.isEmpty()) {
            Packages pack = tempQueue.poll();
            model.addRow(new Object[]{pack.getId(), pack.getSender(), pack.getReceiver(), pack.getAddress(), pack.getGoods(), pack.getWeight(), pack.getService()});
        }

        // Vẽ lại frame
        frame.revalidate();
        frame.repaint();
    }
}
