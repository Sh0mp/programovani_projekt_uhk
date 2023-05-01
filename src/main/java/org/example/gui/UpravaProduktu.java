package org.example.gui;

import org.example.Listeners.UpravaProduktuListener;
import org.example.controllers.UpravaProduktuController;
import org.example.model.Produkt;
import org.example.model.Uloziste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UpravaProduktu {
    Uloziste u = new Uloziste();
    public void EditProduct(int id, JTable table) {
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
        UpravaProduktuController controller = new UpravaProduktuController();
        controller.UpravController(frame2, nameField, quantityField, priceField);

        JButton confirmButton = new JButton("Editovat");
        UpravaProduktuListener upl = new UpravaProduktuListener();
        upl.UpravaListener(confirmButton, nameField, priceField, quantityField,idProduktu,frame2);
        frame2.add(confirmButton);

        frame2.setSize(300, 150);
        frame2.setVisible(true);
        frame2.setLocationRelativeTo(null);
    }

    public void refreshTableData(){
        PrehledAdmin pr = new PrehledAdmin();
        pr.refreshFrame();
    }
}
