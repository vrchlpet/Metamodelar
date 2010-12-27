/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.palette;

import org.cvut.MMN.controller.IController;
import org.openide.nodes.AbstractNode;

/**
 *
 * @author Vrchli
 */
public class ShapeCategoryNode extends AbstractNode{
    private String conCat;
    private ShapeNodeContainer snc;

    public ShapeCategoryNode(ShapeNodeContainer conCat, IController controller) {
        super(conCat);
        this.snc = conCat;
        this.conCat = conCat.getConCat();
        this.setDisplayName("Shape " + conCat.getConCat());
    }

    public ShapeNodeContainer getShapeNodeContainer() {
        return this.snc;
    }

    /**
     * @return the conCat
     */
    public String getConCat() {
        return conCat;
    }

    /**
     * @param conCat the conCat to set
     */
    public void setConCat(String conCat) {
        this.conCat = conCat;
    }
}
