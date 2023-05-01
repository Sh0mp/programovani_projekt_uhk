package org.example.Listeners;
import org.example.gui.ObchodGUI;
import org.example.gui.TabulkaKosik;
import org.example.model.Kosik;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KosikListener {

    public void ListenerDeleteProducts(JTable table, DefaultTableModel model, ArrayList<Kosik> data, JFrame frame){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int viewRow = table.rowAtPoint(e.getPoint());
                int modelRow = table.convertRowIndexToModel(viewRow);
                int col = table.columnAtPoint(e.getPoint());

                if (col == 3) {
                    Kosik kosik = data.get(modelRow);
                    Kosik kos = new Kosik();
                    kos.removeItemFromCart(kosik);
                    model.removeRow(modelRow);
                    if(data.size() > 0){
                        TabulkaKosik tk = new TabulkaKosik();
                        tk.refreshFrame();
                    } else {
                        frame.dispose();
                        ObchodGUI ob = new ObchodGUI();
                    }
                }
            }
        });
    }

}
