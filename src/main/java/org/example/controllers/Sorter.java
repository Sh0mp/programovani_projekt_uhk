package org.example.controllers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.Collections;

public class Sorter {
    public void TableSorter(JTable table, int verzeTabulka,DefaultTableModel model){
        table.setAutoCreateRowSorter(true);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        if(verzeTabulka == 1){
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(1, SortOrder.DESCENDING)));
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(0, SortOrder.DESCENDING)));
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(2, SortOrder.DESCENDING)));
        } else {
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(1, SortOrder.DESCENDING)));
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(0, SortOrder.DESCENDING)));
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(2, SortOrder.DESCENDING)));
        }

        table.setRowSorter(sorter);
    }
}
