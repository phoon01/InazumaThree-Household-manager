import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpPage extends JFrame {
    private JButton deviceButton;
    private JButton plannerButton;
    private JButton productsButton;


    StartUpPage() {
        this.setTitle("HOUSEHOLD MANAGER");
        this.setBounds(0, 0, 600, 400);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(0, 255, 255));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        deviceButton = new JButton("DEVICES");
        deviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DevicePage();
                dispose();
            }
        });
        deviceButton.setSize(30, 30);
        deviceButton.setBounds(240, 0, 100, 50);
        deviceButton.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        deviceButton.setBackground(new Color(255, 255, 255));
        getContentPane().add(deviceButton);

        plannerButton = new JButton("PLANNER");
        plannerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlannerPage();
                dispose();
            }
        });
        plannerButton.setSize(30, 30);
        plannerButton.setBounds(240, 160, 100, 50);
        plannerButton.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        plannerButton.setBackground(new Color(255, 255, 255));
        getContentPane().add(plannerButton);

        productsButton = new JButton("PRODUCTS");
        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        productsButton.setSize(30, 30);
        productsButton.setBounds(240, 310, 100, 50);
        productsButton.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        productsButton.setBackground(new Color(255, 255, 255));
        getContentPane().add(productsButton);

        setVisible(true);
    }

}
