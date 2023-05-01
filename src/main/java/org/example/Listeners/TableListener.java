package org.example.Listeners;

import org.example.controllers.smazatController;
import org.example.gui.PocetKusuGUI;
import org.example.gui.UpravaProduktu;
import org.example.model.Produkt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TableListener {
    public void Listener(int verze, JTable table, JFrame frame, DefaultTableModel model, List<Produkt> data){
        switch(verze) {
            case 1:
                versionOneListener(table, frame, model, data);
                break;
            case 2:
                versionTwoListener(table, frame, model, data);
                break;
            default:
        }

    }

    public void versionOneListener(JTable table, JFrame frame, DefaultTableModel model, List<Produkt> data){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int viewRow = table.rowAtPoint(e.getPoint());
                int modelRow = table.convertRowIndexToModel(viewRow);
                int col = table.columnAtPoint(e.getPoint());

                if (col == 3) {
                    Produkt produkt = data.get(modelRow);
                    int productId = produkt.getId();
                    UpravaProduktu up = new UpravaProduktu();
                    up.EditProduct(productId, table);
                    frame.dispose();
                }

                if (col == 4) {
                    Produkt produkt = data.get(modelRow);
                    int productId = produkt.getId();
                    smazatController cont = new smazatController();
                    cont.viewPane(productId);
                    frame.dispose();
                }

            }
        });
    }
    public void versionTwoListener(JTable table, JFrame frame, DefaultTableModel model, List<Produkt> data){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int viewRow = table.rowAtPoint(e.getPoint());
                int modelRow = table.convertRowIndexToModel(viewRow);
                int col = table.columnAtPoint(e.getPoint());

                if (col == 3) {
                    Produkt produkt = data.get(modelRow);
                    if(produkt.getQuantity() == 0){
                        JOptionPane.showMessageDialog(null, "Produkt " + produkt.getName() + " není skladem", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int productId = produkt.getId();
                        PocetKusuGUI pkg = new PocetKusuGUI();
                        pkg.ViewGUI(productId);
                    }
                }
            }
        });
    }
}
