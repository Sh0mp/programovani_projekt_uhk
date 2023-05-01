package org.example.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ButtonsRenderToTable {
    public DefaultTableCellRenderer buttonRenderer(){
        DefaultTableCellRenderer buttonRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Component) {
                    return (Component)value;
                } else {
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            }
        };
        return buttonRenderer;
    }

    public void buildButtons(int verzeTabulka, JTable table, ButtonsRenderToTable brtt){
        if(verzeTabulka == 1){
            table.getColumnModel().getColumn(3).setCellRenderer(brtt.buttonRenderer());
            table.getColumnModel().getColumn(4).setCellRenderer(brtt.buttonRenderer());
        } else {
            table.getColumnModel().getColumn(3).setCellRenderer(brtt.buttonRenderer());
        }
    }

}
