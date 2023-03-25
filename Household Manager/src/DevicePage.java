import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class DevicePage extends JFrame {
    private JButton add;
    private JButton remove;
    private JButton off;
    private JButton on;
    private JComboBox chooseDevice;
    private JTextArea view;
    private JScrollPane scrollPane;
    private Device[] devices;
    String text = "a\n\n\n\n\n\n\n\n\naaaa\n\n\n\n\n1\n\n\n\n\n\n\n\n\n\n234";
    public static int SearchByName(String txt, Device[] devices){
        for(int i = 0; i < devices.length; i++){
            if(devices[i].getName().equals(txt)){
                return i;
            }
        }
        return 0;
    }

    public void Update(){
        this.view.setText("");
        for(int i = 0; i < this.devices.length; i++){
            if(devices[i] != null) {
                System.out.println(this.devices[i].toString());
                this.view.setText(this.view.getText() + this.devices[i].toString());
            }
        }
    }
    public void Add(){
        this.chooseDevice.removeAllItems();
        for(int i = 0; i < this.devices.length; i++){
            if(devices[i] != null) {
                System.out.println(this.devices[i].toString());
                this.chooseDevice.addItem(devices[i].getName());
            }
        }
    }

    public void AddDevice(String name, String status, LocalDateTime localDateTime){
        for(int i = 0; i < this.devices.length; i++) {
            if(this.devices[i] == null) {
                this.devices[i] = new Device();
                this.devices[i].setDateTime(localDateTime);
                this.devices[i].setName(name);
                this.devices[i].setStatus(status);
                i = 1000;
            }
        }
    }

    public void RemoveDevice(String name){
        int indexToRemove = SearchByName(name, this.devices);
        for(int i = indexToRemove; i < this.devices.length; i++){
            if(this.devices[i + i] == null){
                this.devices[i] = null;
                i = 1000;
            }
            else{
                this.devices[i] = this.devices[i + 1];
            }
        }
    }

    public DevicePage(){
        this.devices = new Device[101];
        devices[0] = new Device();
        devices[0].setName("Samsung TV");
        devices[0].setStatus("ON");
        devices[0].setDateTime(LocalDateTime.now());
        devices[1] = new Device();
        devices[1].setName("Coffee Machine");
        devices[1].setStatus("OFF");
        devices[1].setDateTime(LocalDateTime.now());
        devices[2] = new Device();
        devices[2].setName("PC");
        devices[2].setStatus("ON");
        devices[2].setDateTime(LocalDateTime.now());
        devices[3] = new Device();
        devices[3].setName("Bosch Fridge");
        devices[3].setStatus("ON");
        devices[3].setDateTime(LocalDateTime.now());


        this.setTitle("Devices");
        this.setBounds(0, 0, 700, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new StartUpPage();
            }
        });

        view = new JTextArea();
        view.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        view.setBounds(0, 0, 700, 400);
        Update();
        view.setBackground(new Color(180, 224, 240));
        this.getContentPane().add(view);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(0, 0, 700,400);
        this.getContentPane().add(scrollPane);

        chooseDevice = new JComboBox();
        chooseDevice.setBounds(0, 400, 400, 50);
        chooseDevice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        chooseDevice.setBackground(new Color(225, 225, 250));
        Add();
        this.getContentPane().add(chooseDevice);

        on = new JButton("ON");
        on.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        on.setBounds(400, 400, 100, 50);
        on.setBackground(new Color(50, 205, 50));
        on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int device = SearchByName(chooseDevice.getSelectedItem().toString(), devices);
                devices[device].setStatus("ON");
                devices[device].setDateTime(LocalDateTime.now());
                Update();
            }
        });
        this.getContentPane().add(on);

        off = new JButton("OFF");
        off.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        off.setBounds(500, 400, 100, 50);
        off.setBackground(new Color(255, 50, 50));
        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int device = SearchByName(chooseDevice.getSelectedItem().toString(), devices);
                devices[device].setStatus("OFF");
                devices[device].setDateTime(LocalDateTime.now());

                Update();
            }
        });
        this.getContentPane().add(off);

        remove = new JButton("Remove");
        remove.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        remove.setBounds(600, 400, 100, 50);
        remove.setBackground(new Color(225, 225, 250));
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = chooseDevice.getSelectedItem().toString();
                RemoveDevice(name);
                Update();
                Add();
            }
        });
        this.getContentPane().add(remove);

        add = new JButton("Add Device");
        add.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        add.setBounds(0, 450, 700, 111);
        add.setBackground(new Color(180, 224, 240));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(new JFrame(), "Name of device:");
                String status = "OFF";
                LocalDateTime localDateTime = LocalDateTime.now();
                if(name!=null && !name.toString().equals("")) {
                    AddDevice(name, status, localDateTime);
                    Update();
                    Add();
                }
                else{
                    JOptionPane.showMessageDialog(new JDialog(), "EMPTY FIELD!", "ERROR!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        this.getContentPane().add(add);
        this.setVisible(true);
    }
}
