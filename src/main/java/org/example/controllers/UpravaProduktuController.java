package org.example.controllers;

import org.example.gui.PrehledAdmin;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpravaProduktuController {
    public void UpravController(JFrame frame2, JTextField nameField, JTextField quantityField, JTextField priceField){
        frame2.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(!nameField.getText().isEmpty() && nameField != null || !quantityField.getText().isEmpty() && quantityField != null || !priceField.getText().isEmpty() && priceField != null) {
                    int option = JOptionPane.showConfirmDialog(frame2, "Neuložené změny ! Opustit ?", "", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        frame2.dispose();
                        PrehledAdmin pr = new PrehledAdmin();
                    }
                } else {
                    frame2.dispose();
                }

            }
        });
    }
}
