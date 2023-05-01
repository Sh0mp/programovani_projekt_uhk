package org.example.gui;
import org.example.Listeners.KosikListener;
import org.example.components.ButtonsRenderToTable;
import org.example.model.Kosik;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TabulkaKosik extends JFrame {
    public static final String[] COLUMN_NAMES = {"PRODUKT", "POČET", "CENA ZA KS", "ODSTRANIT"};

    public void refreshFrame() {
        repaint();
    }
    public void GenerateTableForKosik() {
        ArrayList<Kosik> data = Kosik.kosik;
        if (data != null && data.size() > 0) {
            JFrame frame = new JFrame("KOŠÍK");
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
            frame.setResizable(false);

            DefaultTableModel model = new DefaultTableModel(COLUMN_NAMES, data.size()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JTable table = new JTable(model);

            for (int i = 0; i < data.size(); i++) {
                Kosik kosik = data.get(i);
                model.setValueAt(kosik.getProduct().getName(), i, 0);
                model.setValueAt(kosik.getProductCount(), i, 1);
                model.setValueAt(kosik.getProduct().getPrice(), i, 2);

                JButton deleteButton = new JButton("Smazat");
                model.setValueAt(deleteButton, i, 3);
            }
            double totalPrice = Kosik.getCartPrice();
            KosikListener kl = new KosikListener();
            kl.ListenerDeleteProducts(table,model,data,frame);

            JScrollPane sp = new JScrollPane(table);
            frame.add(sp);

            JButton kosikButton = new JButton("Zpět");
            kosikButton.addActionListener(e -> {
                ObchodGUI ogui = new ObchodGUI();
                frame.dispose();
            });

            JTextField cenaField = new JTextField("Celková cena: " + totalPrice + "Kč");
            cenaField.setEditable(false);
            JButton dokoncit = new JButton("Dokončit objednávku");
            dokoncit.addActionListener(e -> {
                System.out.println("yes click");
               Kosik kos = new Kosik();
               kos.completeOrder(frame);
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(kosikButton);
            buttonPanel.add(cenaField);
            buttonPanel.add(dokoncit);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.setLocationRelativeTo(null);
            frame.setSize(760, 400);
            frame.setVisible(true);

            ButtonsRenderToTable brtt = new ButtonsRenderToTable();
            table.getColumnModel().getColumn(3).setCellRenderer(brtt.buttonRenderer());
        } else {
            JOptionPane.showMessageDialog(null, "Prázdný košík", "Error", JOptionPane.ERROR_MESSAGE);
            ObchodGUI og  = new ObchodGUI();
        }
    }

}
