package districtoffice;

import sender_information.Packages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OfficeGUI {
    private JFrame frame;
    private JTextArea packagesTextArea;
    private Office office;

    public OfficeGUI(Office office) {
        this.office = office;
        office.printPackages();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle(office.getClass().getSimpleName());
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        packagesTextArea = new JTextArea();
        packagesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(packagesTextArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh Packages");
        frame.getContentPane().add(refreshButton, BorderLayout.SOUTH);

        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshAndDisplayPackages();
            }
        });
    }

    private void refreshAndDisplayPackages() {
        StringBuilder result = new StringBuilder();

        for (Packages packages : office.getPackageQueue()) {
            result.append(packages.toString()).append("\n");
        }

        packagesTextArea.setText(result.toString());
    }

    public void show() {
        SwingUtilities.invokeLater(() -> {
            try {
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
