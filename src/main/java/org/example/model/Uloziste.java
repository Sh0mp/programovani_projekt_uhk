package org.example.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.gui.PrehledAdmin;

import javax.swing.*;

public class Uloziste {

    private static final String JSON_FILE = "data.json";
    private static List<Produkt> produkts;

    static {
        produkts = new ArrayList<Produkt>();
    }


    public void addItem(Produkt produkt) {

        if (produkts == null) {
            produkts = new ArrayList<>();
        } else {
            produkts.clear();
        }
        produkts.add(produkt);
        saveItemsToJson();
    }


    public static void editItem(int id, String newName, double cena, int mnozstvi) {
        Produkt produkt = getItemById(id);
        if (produkt != null) {
            produkt.setName(newName);
            produkt.setQuantity(mnozstvi);
            produkt.setPrice(cena);
            clearJson();
            saveItemsToJson();
        }
    }

    public static void editQuantityOfProduct(){
        for(Kosik item : Kosik.kosik) {
            Produkt product = item.getProduct();
            int quantity = product.getQuantity() - item.getProductCount();
            product.setQuantity(quantity);
        }
        clearJson();
        saveItemsToJson();
    }


    public static Produkt getItemById(int id) {
        for (Produkt produkt : produkts) {
            if (produkt.getId() == id) {
                return produkt;
            }
        }
        return null;
    }

    public static void checkQuntityNull(){
        produkts = readJson();
        for(Produkt produkt : produkts){
            if(produkt.getQuantity() == 0){
                JOptionPane.showMessageDialog(null, "Doplnit množství u produktu: " + produkt.getName(), "Informace", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static List<Produkt> getAllItems() {
        produkts = readJson();
        return produkts;
    }

    public static void deleteItem(int id) {
        Produkt itemToRemove = getItemById(id);
        if (itemToRemove != null) {
            produkts.remove(itemToRemove);
            clearJson();
            if (!produkts.isEmpty()) {
                saveItemsToJson();
                produkts = readJson();
            }
        }
    }

    private static void clearJson() {
        try {
            File file = new File(JSON_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveItemsToJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            File file = new File(JSON_FILE);
            List<Produkt> existingProdukts;
            if (file.exists() && file.length() > 0) {
                existingProdukts = mapper.readValue(file, new TypeReference<List<Produkt>>() {
                });
            } else {
                existingProdukts = new ArrayList<>();
            }
            for (Produkt produkt : produkts) {
                if (produkt.getId() == 0) {
                    Produkt idProdukt = existingProdukts.get(existingProdukts.size() - 1);
                    int newID = idProdukt.getId() + 1;
                    produkt.setId(newID);
                }
            }
            existingProdukts.addAll(produkts);
            file.createNewFile();
            mapper.writeValue(file, existingProdukts);
            produkts.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Produkt> readJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(JSON_FILE),
                    mapper.getTypeFactory().constructCollectionType(List.class, Produkt.class));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Žádná data k zobrazení",
                    "Hey!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }


}