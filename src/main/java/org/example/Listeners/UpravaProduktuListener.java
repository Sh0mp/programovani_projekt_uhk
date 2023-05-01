package org.example.Listeners;

import org.example.gui.UpravaProduktu;
import org.example.model.Uloziste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpravaProduktuListener {
    public void UpravaListener(JButton confirmButton, JTextField nameField,JTextField priceField,JTextField quantityField,int idProduktu, JFrame frame2){
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Uloziste u = new Uloziste();
                String name = nameField.getText();
                double cena = Double.parseDouble(priceField.getText());
                int mnozstvi = Integer.parseInt(quantityField.getText());

                int pom = 0;
                if(cena <= 0) {
                    JOptionPane.showMessageDialog(null, "Cena musí být větší než 0", "Error", JOptionPane.ERROR_MESSAGE);
                    pom = 100;
                }

                if(mnozstvi <= 0) {
                    JOptionPane.showMessageDialog(null, "Množství musí být větší než 0", "Error", JOptionPane.ERROR_MESSAGE);
                    pom = 100;
                }
                if(pom != 100){
                    u.editItem(idProduktu, name, cena, mnozstvi);
                    UpravaProduktu up = new UpravaProduktu();
                    up.refreshTableData();
                    JOptionPane.showMessageDialog(null, "Upraveno", "", JOptionPane.INFORMATION_MESSAGE);
                    frame2.dispose();
                    pom = 0;
                }
            }
        });
    }
}
