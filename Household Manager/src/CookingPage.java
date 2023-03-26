import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class CookingPage extends JFrame {
    private JButton add;
    private JButton generate;
    private JComboBox products = new JComboBox<>();
    private JTextArea display;
    private Cooking[] cookings;
    private ImageIcon image;
    private JLabel labelImage;
    private databaseConnectionProducts productsData = new databaseConnectionProducts();

    public void Add() {
        this.products.removeAllItems();

        for(Cooking aux : cookings){
            this.products.addItem(aux);
        }

    }

    public void Refresh() {
        this.display.setText((String) null);
    }
    public Cooking[] addCooking(Cooking add){
        Cooking[] newCooking = new Cooking[this.cookings.length+1];
        int k=0;
        for(Cooking aux : this.cookings){
            newCooking[k]=aux;
            k++;
        }
        newCooking[this.cookings.length] = add;
        this.cookings = newCooking;
        return newCooking;
    }

    public CookingPage(Cooking[] cooking) {
        this.cookings = cooking;
        this.setTitle("COOKING PAGE");
        this.setBounds(0, 0, 813, 437);
        this.getContentPane().setLayout((LayoutManager) null);
        this.getContentPane().setBackground(new Color(0, 255, 255));
        this.setLocationRelativeTo((Component) null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        this.add = new JButton("ADD");
        this.add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertNewProduct();
                Add();
            }
        });
        this.add.setBounds(600, 50, 200, 50);
        this.add.setFont(new Font("Times New Roman", 0, 20));
        this.add.setBackground(new Color(255, 255, 255));
        this.getContentPane().add(this.add);

        this.generate = new JButton("GENERATE");
        this.generate.setBounds(600, 350, 200, 50);
        this.generate.setFont(new Font("Times New Roman", 0, 20));
        this.generate.setBackground(new Color(255, 255, 255));
        this.getContentPane().add(this.generate);

        this.products = new JComboBox();
        this.products.setBounds(600, 0, 200, 50);
        this.products.setBackground(new Color(255, 255, 255));
        this.products.setFont(new Font("Times New Roman", 0, 20));
        Add();
        this.getContentPane().add(this.products);

        this.display = new JTextArea();
        this.display.setFont(new Font("Times New Roman", 0, 25));
        this.display.setBounds(0, 0, 600, 400);
        this.display.setBackground(new Color(14, 126, 137));
        this.getContentPane().add(this.display);
        this.Refresh();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                productsData.setCookingData(cookings);
                new StartUpPage();
                dispose();
            }
        });

        image = new ImageIcon(getClass().getResource("chef.png"));
        labelImage = new JLabel(image);
        labelImage.setBounds(600,130,200,200);
        getContentPane().add(labelImage);

        this.setVisible(true);
    }

    public void insertNewProduct(){
        JLabel nameLabel = new JLabel("Product name:");
        JTextField name = new JTextField();

        JLabel expireYearLabel = new JLabel("Expiration year:");
        JTextField expireYear = new JTextField();

        JLabel expireMonthLabel = new JLabel("Expiration month:");
        JTextField expireMonth = new JTextField();

        JLabel expireDayLabel = new JLabel("Expiration day:");
        JTextField expireDay = new JTextField();

        JPanel p1 = new JPanel(new GridLayout(2,1));
        JPanel p2 = new JPanel(new GridLayout(3,2));
        JPanel p3 = new JPanel(new GridLayout(2,1));
        p1.add(nameLabel);
        p1.add(name);

        p2.add(expireYearLabel);
        p2.add(expireYear);
        p2.add(expireMonthLabel);
        p2.add(expireMonth);
        p2.add(expireDayLabel);
        p2.add(expireDay);

        p3.add(p1);
        p3.add(p2);

        JOptionPane.showConfirmDialog(null,p3,
                "Please enter values",JOptionPane.OK_CANCEL_OPTION);

        String nameVal = name.getText();

        if(nameVal.equals("") || expireYear.getText().equals("") ||
                expireDay.getText().equals("") || expireMonth.getText().equals("")){
            JOptionPane.showMessageDialog(new JDialog(), "EMPTY FIELD!", "ERROR!", JOptionPane.WARNING_MESSAGE);
        }


        int eY = Integer.parseInt(expireYear.getText());
        int eM = Integer.parseInt(expireMonth.getText());
        int eD = Integer.parseInt(expireDay.getText());

        Cooking newC = new Cooking(nameVal,eD,eY,eM);
        //System.out.println(nameVal +" "+ eY +" "+ eM +" "+ eD);

        cookings = addCooking(newC);

    }

}
