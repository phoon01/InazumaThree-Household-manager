import org.jsoup.nodes.Element;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class CookingPage extends JFrame {
    private JButton add;
    private JButton generate;
    private JComboBox products;
    private JTextArea display;
    private Cooking[] cookings;
    private ImageIcon image;
    private JLabel labelImage;
    private Map<String, Integer> searchWords = new HashMap<>();
    private databaseConnectionProducts productsData = new databaseConnectionProducts();

    public void Add() {
        this.products.removeAllItems();

        for (Cooking aux : cookings) {
            this.products.addItem(aux);
        }

    }

    private void removeCooking(Cooking remove) {
        Cooking[] newCooking = new Cooking[cookings.length - 1];
        int k = 0;
        for (Cooking aux : cookings) {
            if (!aux.equals(remove)) {
                newCooking[k] = aux;
                k++;
            }
        }
        cookings = newCooking;
    }

    public void updateTextArea(String add) {
        this.display.append(add + "\n");
    }

    public void Refresh() {
        this.display.setText((String) null);
    }

    public Cooking[] addCooking(Cooking add) {
        Cooking[] newCooking = new Cooking[this.cookings.length + 1];
        int k = 0;
        for (Cooking aux : this.cookings) {
            newCooking[k] = aux;
            k++;
        }
        newCooking[this.cookings.length] = add;
        this.cookings = newCooking;
        return newCooking;
    }

    public CookingPage(Cooking[] cooking) {
        this.cookings = cooking;
        this.setTitle("COOKING PAGE");
        this.setBounds(0, 0, 400, 775);
        this.getContentPane().setLayout((LayoutManager) null);
        this.getContentPane().setBackground(new Color(0, 255, 255));
        this.setLocationRelativeTo((Component) null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        this.add = new JButton("Add/Remove");
        this.add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Add", "Remove"};
                int result = JOptionPane.showOptionDialog(
                        new JFrame(),
                        "Add or remove an item!",
                        "Option",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,     //no custom icon
                        options,  //button titles
                        options[0] //default button
                );
                if (result == JOptionPane.YES_OPTION) {
                    insertNewProduct();
                    Add();
                } else if (result == JOptionPane.NO_OPTION) {
                    String ing = products.getSelectedItem().toString().split(" ")[0];
                    Cooking remove = (Cooking) products.getSelectedItem();
                    searchWords.remove(ing);
                    removeCooking(remove);
                    products.removeItem(products.getSelectedItem());
                    Refresh();
                    Add();
                }
            }
        });
        this.add.setBounds(100, 300, 200, 50);
        this.add.setFont(new Font("Times New Roman", 0, 20));
        this.add.setBackground(new Color(255, 255, 255));

        this.generate = new JButton("GENERATE");
        this.generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!searchWords.isEmpty()) {
                    String url = "https://www.google.com/search?q=recipe+with";
                    int k = 0;
                    for (Map.Entry<String, Integer> entry : searchWords.entrySet()) {
                        if (k != 0) {
                            url = url + "+and+" + entry.getKey();
                        } else {
                            url = url + "+" + entry.getKey();
                        }
                        k++;
                    }
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(new URI(url));
                    } catch (IOException | URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                    display.setText("");
                    searchWords.clear();
                } else {
                    JOptionPane.showMessageDialog(new JDialog(), "No items selected!", "ERROR!", JOptionPane.WARNING_MESSAGE);
                }
            }

        });
        this.generate.setBounds(100, 675, 200, 50);
        this.generate.setFont(new Font("Times New Roman", 0, 20));
        this.generate.setBackground(new Color(255, 255, 255));

        this.products = new JComboBox();
        this.products.setBounds(100, 225, 200, 50);
        this.products.setBackground(new Color(255, 255, 255));
        this.products.setFont(new Font("Times New Roman", 0, 20));
        Add();
        this.getContentPane().add(this.products);

        this.display = new JTextArea();
        this.display.setFont(new Font("Times New Roman", 0, 25));
        //this.display.setBounds(100, 450, 200, 200);
        this.display.setBackground(new Color(204, 204, 255));
        JScrollPane scrollPane = new JScrollPane(this.display);
        scrollPane.setBounds(100, 450, 200, 200);


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
        labelImage.setBounds(100, 0, 200, 200);

        JButton addToList = new JButton("Add to list");
        addToList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ing = products.getSelectedItem().toString().split(" ")[0];
                System.out.println(ing);
                if (searchWords.get(ing) == null) {
                    updateTextArea(ing);
                }
                searchWords.put(ing, 1);
            }
        });
        addToList.setBounds(100, 375, 200, 50);
        addToList.setFont(new Font("Times New Roman", 0, 20));
        addToList.setBackground(new Color(255, 255, 255));

        add(labelImage);
        add(products);
        add(add);
        add(addToList);
        add(scrollPane);
        add(generate);


        this.setVisible(true);
    }

    public void insertNewProduct() {
        JLabel nameLabel = new JLabel("Product name:");
        JTextField name = new JTextField();

        JLabel expireYearLabel = new JLabel("Expiration year:");
        JTextField expireYear = new JTextField();

        JLabel expireMonthLabel = new JLabel("Expiration month:");
        JTextField expireMonth = new JTextField();

        JLabel expireDayLabel = new JLabel("Expiration day:");
        JTextField expireDay = new JTextField();

        JPanel p1 = new JPanel(new GridLayout(2, 1));
        JPanel p2 = new JPanel(new GridLayout(3, 2));
        JPanel p3 = new JPanel(new GridLayout(2, 1));
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

        JOptionPane.showConfirmDialog(null, p3,
                "Please enter values", JOptionPane.OK_CANCEL_OPTION);

        String nameVal = name.getText();

        if (nameVal.equals("") || expireYear.getText().equals("") ||
                expireDay.getText().equals("") || expireMonth.getText().equals("")) {
            JOptionPane.showMessageDialog(new JDialog(), "EMPTY FIELD!", "ERROR!", JOptionPane.WARNING_MESSAGE);
            return;
        }


        int eY = Integer.parseInt(expireYear.getText());
        int eM = Integer.parseInt(expireMonth.getText());
        int eD = Integer.parseInt(expireDay.getText());

        Cooking newC = new Cooking(nameVal, eD, eY, eM);
        //System.out.println(nameVal +" "+ eY +" "+ eM +" "+ eD);

        cookings = addCooking(newC);

    }

}
