package transitoffice;

import districtoffice.*;
import sender_information.Packages;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransitOffice {
    private JFrame frame;
    private JButton classifyButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TransitOffice window = new TransitOffice();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TransitOffice() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Transit Office");
        frame.setBounds(100, 100, 1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        classifyButton = new JButton("Phân loại hàng hóa ra thành các bưu cục từng quận");
        frame.getContentPane().add(classifyButton);

        classifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm phân loại và in thông tin
//                classifyAndPrintPackages();
            }
        });

    }

    public static void classifyAndPrintPackages(PriorityQueue<Packages> packages) {
        Map<String, Office> districtOfficeMap = new HashMap<>();
        districtOfficeMap.put("Thanh Xuân", new ThanhXuanOffice());
        districtOfficeMap.put("Đống Đa", new DongDaOffice());
        districtOfficeMap.put("Ba Đình", new BaDinhOffice());
        districtOfficeMap.put("Tây Hồ", new TayHoOffice());
        districtOfficeMap.put("Cầu Giấy", new CauGiayOffice());

        // Phân loại gói hàng theo từng quận
        for (Packages p : packages) {
            classifyPackageByDistrict(p, districtOfficeMap);
        }

        // In thông tin theo từng quận
        for (Map.Entry<String, Office> entry : districtOfficeMap.entrySet()) {
            System.out.println("District: " + entry.getKey());
            entry.getValue().printPackages();
        }
    }

    public static void classifyPackageByDistrict(Packages packages, Map<String, Office> districtOfficeMap) {
        String district = packages.getAddress();
        if (districtOfficeMap.containsKey(district)) {
            Office office = districtOfficeMap.get(district);
            office.deliverToOffice(packages);
        } else {
            System.out.println("ERROR! Unknown district: " + district);
        }
    }
}
