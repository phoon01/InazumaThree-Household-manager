import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Objects;

public class DevicePage extends JFrame {
    private JButton add;
    private JButton remove;
    private JButton off;
    private JButton on;
    private JComboBox chooseDevice;
    private JTextArea view;
    private JScrollPane scrollPane;
    private Device[] memory;
    private databaseConnectionDevices devicesData = new databaseConnectionDevices();
    String text = "a\n\n\n\n\n\n\n\n\naaaa\n\n\n\n\n1\n\n\n\n\n\n\n\n\n\n234";
    public static int SearchByName(String txt, Device[] devices){
        for(int i = 0; i < devices.length; i++){
            if(devices[i].getName().equals(txt)){
                return i;
            }
        }
        return 0;
    }

    public void Update(Device[] devices){
        this.view.setText("");
        for (Device device : devices) {
            if (device != null) {
                //System.out.println(this.devices[i].toString());
                this.view.setText(this.view.getText() + device.toString());
            }
        }
    }
    public void Add(Device[] devices){
        this.chooseDevice.removeAllItems();
        for(int i = 0; i < devices.length; i++){
            if(devices[i] != null) {
                //System.out.println(this.devices[i].toString());
                this.chooseDevice.addItem(devices[i].getName());
            }
        }
        memory=devices;
    }

    public void AddDevice(Device device,Device[] devices){
        Device[] newDevice = new Device[devices.length+1];
        for(int i = 0; i < devices.length; i++) {
            newDevice[i] = devices[i];
        }
        newDevice[devices.length]=device;
        memory=newDevice;
    }

    public void RemoveDevice(String name,Device[] devices){
        Device[] newDevice = new Device[devices.length-1];
        int k=0;
        for(Device aux : devices){
            if(!Objects.equals(aux.getName(), name)){
                newDevice[k]=aux;
                k++;
            }
        }
        memory=newDevice;
    }

    public DevicePage(Device[] devices){

        memory=devices;

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
                devicesData.setDeviceData(memory);
                new StartUpPage();
            }
        });

        view = new JTextArea();
        view.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        view.setBounds(0, 0, 700, 400);
        Update(devices);
        view.setBackground(new Color(180, 224, 240));
        this.getContentPane().add(view);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(0, 0, 700,400);
        this.getContentPane().add(scrollPane);

        chooseDevice = new JComboBox();
        chooseDevice.setBounds(0, 400, 400, 50);
        chooseDevice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        chooseDevice.setBackground(new Color(225, 225, 250));
        Add(devices);
        this.getContentPane().add(chooseDevice);

        on = new JButton("ON");
        on.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        on.setBounds(400, 400, 100, 50);
        on.setBackground(new Color(50, 205, 50));
        on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int device = SearchByName(chooseDevice.getSelectedItem().toString(), memory);
                memory[device].setStatus("ON");
                memory[device].setDateTime(LocalDateTime.now());
                Update(memory);
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
                int device = SearchByName(chooseDevice.getSelectedItem().toString(), memory);
                memory[device].setStatus("OFF");
                memory[device].setDateTime(LocalDateTime.now());

                Update(memory);
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
                RemoveDevice(name,memory);
                Update(memory);
                Add(memory);
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
                    Device newDevice = new Device();
                    newDevice.setName(name);
                    newDevice.setStatus(status);
                    newDevice.setDateTime(localDateTime);
                    AddDevice(newDevice,memory);
                    Update(memory);
                    Add(memory);
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
