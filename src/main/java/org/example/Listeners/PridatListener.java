package org.example.Listeners;

import org.example.model.Produkt;
import org.example.model.Uloziste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PridatListener {
    public void PridatProduktListener(JFrame frame, JButton confirmButton,JTextField nameField, JTextField quantityField, JTextField priceField){
        confirmButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nazev = nameField.getText();
                if (nazev == null || nazev.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Prosím, zadejte název produktu", "Chyba", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int quantity = 0;

                try {
                    quantity = Integer.parseInt(quantityField.getText());
                } catch (NumberFormatException eX) {

                    JOptionPane.showMessageDialog(null, "Špatný formát u množství",
                            "Hey!", JOptionPane.ERROR_MESSAGE);

                    return;
                }

                double price = 0;

                try {
                    price = Double.parseDouble(priceField.getText());
                } catch (NumberFormatException eX) {

                    JOptionPane.showMessageDialog(null, "Špatný formát u ceny",
                            "Hey!", JOptionPane.ERROR_MESSAGE);

                    return;
                }
                if(price < 0) {
                    JOptionPane.showMessageDialog(null, "Zadejte platnou cenu",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(quantity < 0){
                    JOptionPane.showMessageDialog(null, "Zadejte platné množství",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Produkt pr = new Produkt(nazev, quantity, price);
                Uloziste u = new Uloziste();
                u.addItem(pr);
                JOptionPane.showMessageDialog(null, "Byl přidán produkt: " + nazev, "Informace", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();

            }


        });
    }
}
