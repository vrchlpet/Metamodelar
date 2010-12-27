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
public class ConPaletteManager extends AbstractNode{


    private ConCategoryContainer ch;

    public ConPaletteManager(ConCategoryContainer children) {
        super(children);
        this.ch = children;
    }

    public ConCategoryContainer getConCategoryContainer() {
        return this.ch;
    }




}
