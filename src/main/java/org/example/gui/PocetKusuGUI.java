package org.example.gui;

import org.example.model.Kosik;
import org.example.model.Produkt;
import org.example.model.Uloziste;

import javax.swing.*;

public class PocetKusuGUI{

    public void ViewGUI(int id) {
        Uloziste u = new Uloziste();
        JFrame frame = new JFrame("Zadejte množství");
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel quantityLabel = new JLabel("Množství:");
        JTextField quantityField = new JTextField();


        JButton addToCartButton = new JButton("Přidat");
        addToCartButton.addActionListener(e -> {
            String quantityText = quantityField.getText();
            try {
                int quantity = Integer.parseInt(quantityText);
                Produkt produkt = u.getItemById(id);
                Kosik kosik = new Kosik();
                if (produkt.getQuantity() < quantity || produkt.getQuantity() == 0) {
                    int skladem = produkt.getQuantity();
                    JOptionPane.showMessageDialog(frame, "Nedostatečný počet kusů skladem. Dostupných: " + skladem + "ks", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean itemFound = false;
                    for (Kosik existingKosik : Kosik.kosik) {
                        if (existingKosik.getProduct().getId() == id) {
                            int newQuantity = existingKosik.getProductCount() + quantity;
                            if (newQuantity > produkt.getQuantity()) {
                                int availableQuantity = produkt.getQuantity() - existingKosik.getProductCount();
                                JOptionPane.showMessageDialog(frame, "Nedostatečný počet kusů skladem. Dostupných: " + availableQuantity + "ks", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            existingKosik.setProductCount(newQuantity);
                            itemFound = true;
                            break;
                        }
                    }
                    if (!itemFound) {
                        if (quantity > produkt.getQuantity()) {
                            JOptionPane.showMessageDialog(frame, "Nedostatečný počet kusů skladem. Dostupných: " + produkt.getQuantity() + "ks", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        kosik.setProduct(produkt);
                        kosik.setProductCount(quantity);
                        Kosik.kosik.add(kosik);
                    }
                    JOptionPane.showMessageDialog(frame, "Přidáno do košíku");
                    System.out.println(kosik.getProductCount());
                    frame.dispose();
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Zadejte číslo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(quantityLabel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(quantityField);
        frame.add(Box.createVerticalStrut(10));
        frame.add(addToCartButton);
        frame.setVisible(true);
    }

}
