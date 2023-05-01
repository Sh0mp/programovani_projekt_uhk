package org.example.controllers;

import org.example.model.Produkt;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportCSV {
    public void exportToCSV(List<Produkt> produkts) throws IOException {
        FileWriter csvWriter = new FileWriter("data.csv");
        csvWriter.append("CSV EXPORT DAT");
        csvWriter.append("\n");
        csvWriter.append("\n");

        for (Produkt produkt : produkts) {
            csvWriter.append("Název: " + produkt.getName());
            csvWriter.append("\n");
            csvWriter.append("Cena: " + produkt.getPrice() + "Kč");
            csvWriter.append("\n");
            csvWriter.append("Množství skladem: " + produkt.getQuantity() + "ks");
            csvWriter.append("\n");
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
        JOptionPane.showMessageDialog(null, "Produkty byly exportovány do formátu CSV", "Informace", JOptionPane.INFORMATION_MESSAGE);
    }

}
