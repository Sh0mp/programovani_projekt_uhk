package org.example.model;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Tiskarna {

    public void printTicket(ArrayList<Kosik> kosik) {
        try {
            FileWriter writer = new FileWriter("Uctenka.txt");
            LocalDate today = LocalDate.now();
            Kosik kosik2 = new Kosik();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("cs", "CZ"));
            String formattedDate = today.format(formatter);
            writer.write("Účtenka ze dne: " + formattedDate);
            writer.write("\n");
            writer.write("\n");
            for (Kosik item : kosik) {
                writer.write(item.toString() + "\n");
            }
            writer.write("\n");
            writer.write("Celková cena: " + kosik2.getCartPrice() + "Kč");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Chyba", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
