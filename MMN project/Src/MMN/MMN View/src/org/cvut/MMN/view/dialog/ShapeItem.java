/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.dialog;

/**
 *
 * @author Vrchli
 */
public class ShapeItem {
    private String shapeName;
    private String shapeCategory;

    public ShapeItem(String shapeName, String shapeCategory) {
        this.shapeCategory = shapeCategory;
        this.shapeName = shapeName;
    }

    /**
     * @return the shapeName
     */
    public String getShapeName() {
        return shapeName;
    }

    /**
     * @return the shapeCategory
     */
    public String getShapeCategory() {
        return shapeCategory;
    }
}
