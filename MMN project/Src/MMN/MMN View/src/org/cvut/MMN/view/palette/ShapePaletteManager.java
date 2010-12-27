/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.palette;

import org.openide.nodes.AbstractNode;

/**
 *
 * @author Vrchli
 */
public class ShapePaletteManager extends AbstractNode{

    private ShapeCategoryContainer ch;

    public ShapePaletteManager(ShapeCategoryContainer children) {
        super(children);
        this.ch = children;
    }

    public ShapeCategoryContainer getShapeCategoryContainer() {
        return this.ch;
    }



}
