package org.example.model;
import org.example.gui.ObchodGUI;

import javax.swing.*;
import java.util.ArrayList;

public class Kosik {
    public static ArrayList<Kosik> kosik = new ArrayList<>();
    private Produkt produkt;

    private int pocetKusu;

    public Produkt getProduct() {
        return produkt;
    }

    public Kosik setProduct(Produkt produkt) {
        this.produkt = produkt;
        return this;
    }

    public int getProductCount() {
        return pocetKusu;
    }

    public Kosik setProductCount(int pocetKusu) {
        this.pocetKusu = pocetKusu;
        return this;
    }

    public static double getCartPrice() {
        return kosik.stream().mapToDouble(kosik -> kosik.getProduct().getPrice() * kosik.getProductCount()).sum();
    }

    public static void removeItemFromCart(Kosik kosik) {
        Kosik.kosik.remove(kosik);
    }

    public static void removeAllItemsFromCart(){
        Kosik.kosik.clear();
    }

    public static void completeOrder(JFrame frame){
        if (kosik.size() == 0) {
            return;
        }
        Tiskarna tisk = new Tiskarna();
        tisk.printTicket(kosik);
        Uloziste ul = new Uloziste();
        ul.editQuantityOfProduct();
        removeAllItemsFromCart();
        frame.dispose();
        JOptionPane.showMessageDialog(null, "Objednávka dokončena, vaše účtenka byla vytisknuta", "Informace", JOptionPane.INFORMATION_MESSAGE);
        ObchodGUI og = new ObchodGUI();
    }

    public String toString() {
        return  pocetKusu + "ks  " + produkt.getName() + " cena za kus: " + produkt.getPrice();
    }



}
