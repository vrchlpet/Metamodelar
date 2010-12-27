/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.core;

import java.util.ArrayList;

/**
 *
 * @author Vrchli
 */
public class Connection {

    private String name;
    private ArrayList<ConnectionItem> items;


    public Connection(String name) {
        this.name = name;
        items = new ArrayList<ConnectionItem>();
    }


    public void addItem(ConnectionItem conI) {
        items.add(conI);
    }

    public void removeItem(ConnectionItem conI) {
        items.remove(conI);
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the items
     */
    public ArrayList<ConnectionItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<ConnectionItem> items) {
        this.items = items;
    }
}
