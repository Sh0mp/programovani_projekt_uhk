package org.example.Listeners;

import org.example.gui.TabulkaKosik;

import javax.swing.*;
import java.awt.*;

public class VygenerovatTabulkuListener {
    public void KosikButtonListener(JButton kosikButton, JFrame frame){
        kosikButton.addActionListener(e -> {
            TabulkaKosik tk = new TabulkaKosik();
            tk.GenerateTableForKosik();
            frame.dispose();
        });

    }
}
