/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.dialog;

/**
 *
 * @author Vrchli
 */
public class ConItem {
    private String ConName;
    private String ConCategory;

    public ConItem(String ConName, String ConCategory) {
        this.ConCategory = ConCategory;
        this.ConName = ConName;
    }

    /**
     * @return the shapeName
     */
    public String getConName() {
        return ConName;
    }

    /**
     * @return the shapeCategory
     */
    public String getConCategory() {
        return ConCategory;
    }
}
