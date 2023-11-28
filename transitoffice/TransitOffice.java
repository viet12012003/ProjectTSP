package transitoffice;

import districtoffice.*;
import receive.PostOfficeManagement;
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
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        classifyButton = new JButton("Phân loại và In hàng hóa");
        frame.getContentPane().add(classifyButton);

        classifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm phân loại và in thông tin
                PostOfficeManagement postOfficeManagement = new PostOfficeManagement();
                PriorityQueue<Packages> packages = postOfficeManagement.getPackageQueue();
                classifyAndPrintPackages(packages);
            }
        });
    }

    private void classifyAndPrintPackages(PriorityQueue<Packages> packages) {
        Map<String, Office> districtOfficeMap = new HashMap<>();
        districtOfficeMap.put("Thanh Xuân", new ThanhXuanOffice());
        districtOfficeMap.put("Đống Đa", new DongDaOffice());
        districtOfficeMap.put("Ba Đình", new BaDinhOffice());
        districtOfficeMap.put("Tây Hồ", new TayHoOffice());
        districtOfficeMap.put("Cầu Giấy", new CauGiayOffice());

        // Phân loại gói hàng theo từng quận
        for (Packages p : packages) {
            String[] districtArray = p.getAddress().split(",");
            String district = districtArray[districtArray.length - 1].trim();
            classifyPackageByDistrict(p, districtOfficeMap);
        }

        // In thông tin theo từng quận
        for (Map.Entry<String, Office> entry : districtOfficeMap.entrySet()) {
            System.out.println("District: " + entry.getKey());
            entry.getValue().printPackages();
        }
    }

    private void classifyPackageByDistrict(Packages packages, Map<String, Office> districtOfficeMap) {
        String[] districtArray = packages.getAddress().split(",");
        String district = districtArray[districtArray.length - 1].trim();
        if (districtOfficeMap.containsKey(district)) {
            Office office = districtOfficeMap.get(district);
            office.deliverToOffice(packages);
        } else {
            System.out.println("ERROR! Unknown district: " + district);
        }
    }
}
