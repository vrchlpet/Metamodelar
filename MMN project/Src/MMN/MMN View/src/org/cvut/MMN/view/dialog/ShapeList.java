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
public class ShapeList extends List {
    private ArrayList<ShapeItem> items = new ArrayList<ShapeItem>();

    public void addItem(String shapeName, String shapeCat) {
        items.add(new ShapeItem(shapeName, shapeCat));
        add(shapeName);
    }

    public ShapeItem getSelectedShape() {
        int selected = getSelectedIndex();
        return selected != -1 ? items.get(selected) : null;
    }
}
