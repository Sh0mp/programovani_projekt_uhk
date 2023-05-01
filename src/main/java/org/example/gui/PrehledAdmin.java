package org.example.gui;

import javax.swing.JFrame;

public class PrehledAdmin extends JFrame {
    public void refreshFrame() {
        repaint();
    }

    public PrehledAdmin() {
        VygenerovatTabulku vt = new VygenerovatTabulku();
        vt.ViewTable(1);
    }


}

