package org.example.controllers;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PridatAdminController {
    public void PridatController(JFrame frame, JTextField nameField,JTextField quantityField,JTextField priceField){
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(!nameField.getText().isEmpty() && nameField != null || !quantityField.getText().isEmpty() && quantityField != null || !priceField.getText().isEmpty() && priceField != null) {
                    int option = JOptionPane.showConfirmDialog(frame, "Jste si jistý ?", "", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        frame.dispose();
                    }
                } else {
                    frame.dispose();
                }

            }
        });
    }
}
