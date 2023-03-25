import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class CookingPage extends JFrame {
    private JButton add;
    private JButton generate;
    private JComboBox products;
    private JTextArea display;
    private Cooking[] cookings;

    public void Add() {
        this.products.removeAllItems();

        for(int i = 0; i < this.cookings.length; ++i) {
            if (this.cookings[i] != null) {
                System.out.println(this.cookings[i].toString());
                this.products.addItem(this.cookings[i].getName());
            }
        }

    }

    public void Update() {
        this.display.setText("");

        for(int i = 0; i < this.cookings.length; ++i) {
            if (this.cookings[i] != null) {
                System.out.println(this.cookings[i].toString());
                JTextArea var10000 = this.display;
                String var10001 = this.display.getText();
                var10000.setText(var10001 + this.cookings[i].toString());
            }
        }

    }

    public void Refresh() {
        this.display.setText((String)null);
    }

    public CookingPage() {
        this.setTitle("COOKING PAGE");
        this.setBounds(0, 0, 813, 437);
        this.getContentPane().setLayout((LayoutManager)null);
        this.getContentPane().setBackground(new Color(0, 255, 255));
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.cookings = new Cooking[101];
        this.cookings[0] = new Cooking();
        this.cookings[0].setName("Milk");
        this.cookings[1] = new Cooking();
        this.cookings[1].setName("Eggs");

        this.add = new JButton("ADD");
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
        this.Add();
        this.getContentPane().add(this.products);

        this.display = new JTextArea();
        this.display.setFont(new Font("Times New Roman", 0, 25));
        this.display.setBounds(0, 0, 600, 400);
        this.Update();
        this.display.setBackground(new Color(180, 224, 240));
        this.getContentPane().add(this.display);
        this.Refresh();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new StartUpPage();
                dispose();
            }
        });

        this.setVisible(true);
    }

}
