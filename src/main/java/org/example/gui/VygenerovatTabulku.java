package org.example.gui;

import org.example.Listeners.TableListener;
import org.example.Listeners.VygenerovatTabulkuListener;
import org.example.components.ButtonsRenderToTable;
import org.example.controllers.Sorter;
import org.example.model.Produkt;
import org.example.model.Uloziste;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class VygenerovatTabulku {
    public static final String[] COLUMN_NAMES = {"PRODUKT", "POČET", "CENA", "UPRAVIT", "SMAZAT"};
    public static final String[] COLUMN_NAMES2 = {"PRODUKT", "POČET SKLADEM", "CENA", "VLOŽIT DO KOŠÍKU"};

    public void ViewTable(int verzeTabulka) {
        Uloziste u = new Uloziste();
        List<Produkt> data = u.getAllItems();
        if (data != null) {
            JFrame frame = new JFrame("Přehled");
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
            frame.setResizable(false);

            String[] column;
            if(verzeTabulka == 1){
                column = COLUMN_NAMES;
            } else {
                column = COLUMN_NAMES2;
            }
                DefaultTableModel model = new DefaultTableModel(column, data.size()) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

            JTable table = new JTable(model);

            for (int i = 0; i < data.size(); i++) {
                Produkt p = data.get(i);
                if(verzeTabulka == 1 ){
                    model.setValueAt(p.getName(), i, 0);
                    model.setValueAt(p.getQuantity() + "Ks", i, 1);
                    model.setValueAt(p.getPrice() + "Kč", i, 2);

                    JButton editButton = new JButton("Upravit");
                    model.setValueAt(editButton, i, 3);


                    JButton deleteButton = new JButton("Smazat");
                    model.setValueAt(deleteButton, i, 4);
                } else {
                    model.setValueAt(p.getName(), i, 0);
                    model.setValueAt(p.getQuantity() + "Ks", i, 1);
                    model.setValueAt(p.getPrice() + "Kč", i, 2);

                    JButton pridatButton = new JButton("Přidat");
                    model.setValueAt(pridatButton, i, 3);
                }
            }

            TableListener tl = new TableListener();
            tl.Listener(verzeTabulka,table,frame,model,data);
            Sorter sorter = new Sorter();
            sorter.TableSorter(table, verzeTabulka, model);


            JScrollPane sp = new JScrollPane(table);
            frame.add(sp);

            if (verzeTabulka != 1) {
                JButton kosikButton = new JButton("Přejít do košíku");
                VygenerovatTabulkuListener vtl = new VygenerovatTabulkuListener();
                vtl.KosikButtonListener(kosikButton, frame);
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(kosikButton,kosikButton);
                frame.add(buttonPanel, BorderLayout.SOUTH);
            }

            frame.setSize(760, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            ButtonsRenderToTable brtt = new ButtonsRenderToTable();
            brtt.buildButtons(verzeTabulka, table, brtt);

        }
    }

}
