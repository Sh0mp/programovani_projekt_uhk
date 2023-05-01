package org.example.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.example.Listeners.PridatListener;
import org.example.controllers.PridatAdminController;
import org.example.model.Produkt;
import org.example.model.Uloziste;
import java.awt.event.*;

public class PridatAdmin extends JFrame {
    public PridatAdmin() {
        JFrame frame = new JFrame("Přidat");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.setResizable(false);
        frame.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Název produktu:");
        frame.add(nameLabel);
        JTextField nameField = new JTextField(20);
        frame.add(nameField);

        JLabel quantityLabel = new JLabel("Množství produktu:");
        frame.add(quantityLabel);
        JTextField quantityField = new JTextField(10);
        frame.add(quantityField);

        JLabel priceLabel = new JLabel("Cena:");
        frame.add(priceLabel);
        JTextField priceField = new JTextField(10);
        frame.add(priceField);

        PridatAdminController pac = new PridatAdminController();
        pac.PridatController(frame, nameField, quantityField, priceField);

        JButton confirmButton = new JButton("Přidat");
        frame.add(confirmButton);
        PridatListener pl = new PridatListener();
        pl.PridatProduktListener(frame, confirmButton, nameField, quantityField, priceField);

        frame.setSize(300, 150);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }

}
