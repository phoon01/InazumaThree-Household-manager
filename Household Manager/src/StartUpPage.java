import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpPage extends JFrame {
    private JButton deviceButton;
    private JButton plannerButton;
    private JButton productsButton;
    private databaseConnectionPlanner plannerData = new databaseConnectionPlanner();
    private databaseConnectionDevices devicesData =new databaseConnectionDevices();
    private databaseConnectionProducts productsData = new databaseConnectionProducts();
    private ImageIcon image;
    private JLabel labelImage;
    private JLabel title;
    private JLabel title1;


    StartUpPage() {
        this.setTitle("HOUSEHOLD MANAGER");
        this.setBounds(0, 0, 400, 600);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(128,178,128));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        title = new JLabel("HouseHold");
        title.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        title.setBounds(112, 215, 200, 50);
        this.getContentPane().add(title);
        title1= new JLabel("Planner");
        title1.setBounds(137, 250, 200, 50);
        title1.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        this.getContentPane().add(title1);

        deviceButton = new JButton("DEVICES");
        deviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Device[] devices = devicesData.getDeviceData();
                new DevicePage(devices);
                dispose();
            }
        });
        deviceButton.setSize(30, 30);
        deviceButton.setBounds(100, 325, 200, 50);
        deviceButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        deviceButton.setBackground(new Color(210, 220, 255));
        getContentPane().add(deviceButton);

        plannerButton = new JButton("SCHEDULE");
        plannerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Day[] days = plannerData.getPlannerData();
                new PlannerPage(days);
                dispose();
            }
        });
        plannerButton.setSize(30, 30);
        plannerButton.setBounds(100, 400, 200, 50);
        plannerButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        plannerButton.setBackground(new Color(210,220,255));
        getContentPane().add(plannerButton);

        productsButton = new JButton("PRODUCTS");
        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cooking[] cookings = productsData.getCookingData();
                new CookingPage(cookings);
                dispose();
            }
        });
        productsButton.setSize(30, 30);
        productsButton.setBounds(100, 475, 200, 50);
        productsButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        productsButton.setBackground(new Color(210, 220, 255));
        getContentPane().add(productsButton);

        image = new ImageIcon(getClass().getResource("house.png"));
        labelImage = new JLabel(image);
        labelImage.setBounds(100,0,200,200);
        getContentPane().add(labelImage);

        setVisible(true);
    }

}