/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.dialog;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author Vrchli
 */
public class ConList extends List {
    private ArrayList<ConItem> items = new ArrayList<ConItem>();

    public void addItem(String ConName, String ConCat) {
        items.add(new ConItem(ConName, ConCat));
        add(ConName);
    }

    public ConItem getSelectedCon() {
        int selected = getSelectedIndex();
        return selected != -1 ? items.get(selected) : null;
    }
}
