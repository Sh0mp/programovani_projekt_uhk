package org.example.gui;

import org.example.controllers.ExportCSV;
import org.example.model.Produkt;
import org.example.model.Uloziste;

import java.awt.GridLayout;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

public class UvodniStrankaAdmin extends JFrame {

    public UvodniStrankaAdmin() {
        JFrame frame = new JFrame("Administrace");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 1, 5, 20));
        frame.setResizable(false);
        JButton prehled = new JButton("Přehled");
        prehled.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PrehledAdmin pa = new PrehledAdmin();
            }
        });
        frame.add(prehled);

        JButton pridat = new JButton("Přidat");
        pridat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PridatAdmin p = new PridatAdmin();
            }
        });
        frame.add(pridat);

        JButton sprava = new JButton("Správa produktů");
        sprava.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SpravaProduktu s = new SpravaProduktu();
            }
        });
        frame.add(sprava);

        JButton export = new JButton("Export CSV");
        export.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ExportCSV export = new ExportCSV();
                    Uloziste ul = new Uloziste();
                    List<Produkt> produkts =  ul.getAllItems();
                    export.exportToCSV(produkts);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(export);

        JButton end = new JButton("Konec");
        end.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.add(end);

        frame.setSize(660, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
