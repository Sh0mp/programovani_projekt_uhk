package org.example;

import org.example.gui.UvodniStrankaAdmin;
import org.example.model.Uloziste;

public class Administrace {
    public static void main(String[] args) throws Exception {
        UvodniStrankaAdmin uvod = new UvodniStrankaAdmin();
        Uloziste ul = new Uloziste();
        ul.checkQuntityNull();
    }
}
