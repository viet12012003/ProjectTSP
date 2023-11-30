package transitoffice;

import districtoffice.*;
import receive.PackageQueueManager;
import sender_information.Packages;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransitOffice {
    private PackageQueueManager packageQueueManager = new PackageQueueManager();
    private PriorityQueue<Packages> packages = packageQueueManager.getPackageQueue();
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
        while (!packages.isEmpty()) {
            classifyPackageByDistrict(packages.poll(), districtOfficeMap);
        }

        // In thông tin theo từng quận
        for (Map.Entry<String, Office> entry : districtOfficeMap.entrySet()) {
            String district = entry.getKey();
            Office office = entry.getValue();
            System.out.println("Quận : " + district);
            office.printPackages();
            writePackagesToCSV(district, office, district + "_packages.csv");
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
    public static void writePackagesToCSV(String district, Office office, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Packages packages : office.getPackageQueue()) {
                writer.append(String.join(",",
                                String.valueOf(packages.getId()),
                                packages.getSender(),
                                packages.getReceiver(),
                                packages.getAddress(),
                                packages.getGoods(),
                                String.valueOf(packages.getWeight()),
                                packages.getService()))
                        .append("\n");
            }

            System.out.println("CSV file has been created successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }

}