package org.example.controllers;

import org.example.gui.PrehledAdmin;
import org.example.model.Uloziste;

import javax.swing.*;
import java.awt.*;

public class smazatController extends Component {
    Uloziste u = new Uloziste();

    public void viewPane(int id) {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Opravdu chcete smazat produkt ?", "", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            u.deleteItem(id);
            refreshTableData();
        } else {
            Window window = SwingUtilities.getWindowAncestor(this);
            window.dispose();
        }
    }

    public void refreshTableData(){
        PrehledAdmin pr = new PrehledAdmin();
        pr.refreshFrame();
    }
}
