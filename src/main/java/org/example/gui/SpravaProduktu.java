package org.example.gui;

import org.example.model.Uloziste;
import org.example.model.Produkt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.*;

public class SpravaProduktu {
    private Produkt selectedProduct;
    JFrame frame = new JFrame();
    public SpravaProduktu() {
        Uloziste u = new Uloziste();
        List<Produkt> productList = u.getAllItems();

        if (productList != null) {
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
            frame.setLayout(new GridLayout(3, 1));
            frame.setResizable(false);

            JComboBox comboBox = new JComboBox<>();
            for (Produkt product : productList) {
                comboBox.addItem(product);
            }

            comboBox.setBounds(50, 50, 90, 20);
            comboBox.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        selectedProduct = (Produkt) e.getItem();
                    }
                }
            });
            frame.add(comboBox);

            JButton deleteBtn = new JButton("Odstranit");

            deleteBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selectedProduct != null) {
                        int productId = selectedProduct.getId();
                        u.deleteItem(productId);
                        comboBox.removeAllItems();
                        List<Produkt> productList = u.getAllItems();
                        if (productList != null && !productList.isEmpty()) {
                            for (Produkt product : productList) {
                                comboBox.addItem(product);
                            }
                            comboBox.setSelectedIndex(-1);
                        } else {
                            comboBox.addItem("Žádný produkt k dispozici");
                        }

                    } else {
                        u.deleteItem(1);
                        comboBox.removeAllItems();
                        List<Produkt> productList = u.getAllItems();
                        if (productList != null && !productList.isEmpty()) {
                            for (Produkt product : productList) {
                                comboBox.addItem(product);
                            }
                            comboBox.setSelectedIndex(-1);
                        } else {
                            comboBox.addItem("Žádný produkt k dispozici");
                        }
                    }

                }
            });
            frame.add(deleteBtn);

            JButton editBtn = new JButton("Editovat");
            editBtn.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {
                    if (selectedProduct != null) {
                        int productId = selectedProduct.getId();
                            buildGUIEdit(productId);
                    } else {
                        buildGUIEdit(1);
                    }
                }
            });
            frame.add(editBtn);
            frame.setSize(660, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);


        }
    }
    public void buildGUIEdit(int id) {
        JFrame frame2 = new JFrame("Edit");
        frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame2.setResizable(false);
        frame2.setLayout(new GridLayout(4, 2));

        Uloziste u = new Uloziste();
        Produkt produkt = u.getItemById(id);
        String nazev = produkt.getName();
        double cena = produkt.getPrice();
        int mnozstvi = produkt.getQuantity();
        int idProduktu = produkt.getId();
        JLabel nameLabel = new JLabel("Název produktu:");
        frame2.add(nameLabel);
        JTextField nameField = new JTextField(20);
        frame2.add(nameField);
        nameField.setText(nazev);

        JLabel quantityLabel = new JLabel("Množství produktu:");
        frame2.add(quantityLabel);
        JTextField quantityField = new JTextField(10);
        frame2.add(quantityField);
        quantityField.setText(String.valueOf(mnozstvi));

        JLabel priceLabel = new JLabel("Cena:");
        frame2.add(priceLabel);
        JTextField priceField = new JTextField(10);
        frame2.add(priceField);
        priceField.setText(String.valueOf(cena));
        frame2.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(!nameField.getText().isEmpty() && nameField != null || !quantityField.getText().isEmpty() && quantityField != null || !priceField.getText().isEmpty() && priceField != null) {
                    int option = JOptionPane.showConfirmDialog(frame2, "Neuložené změny ! Opustit ?", "", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        frame2.dispose();
                    }
                } else {
                    frame2.dispose();
                }

            }
        });

        JButton confirmButton = new JButton("Editovat");
        frame2.add(confirmButton);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Uloziste u = new Uloziste();
                String name = nameField.getText();
                double cena = Double.parseDouble(priceField.getText());
                int mnozstvi = Integer.parseInt(quantityField.getText());
                if(cena < 0) {
                    JOptionPane.showMessageDialog(null, "Zadejte platnou cenu",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(mnozstvi < 0){
                    JOptionPane.showMessageDialog(null, "Zadejte platné množství",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                u.editItem(idProduktu, name, cena, mnozstvi);
                JOptionPane.showMessageDialog(null, "Upraveno", "", JOptionPane.INFORMATION_MESSAGE);
                frame2.dispose();
                frame.dispose();
            }
        });
        frame2.setSize(300, 150);
        frame2.setVisible(true);
        frame2.setLocationRelativeTo(null);
    }
}


