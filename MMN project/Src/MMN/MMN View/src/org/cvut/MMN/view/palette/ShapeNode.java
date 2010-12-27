/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.palette;

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author Vrchli
 */
public class ShapeNode extends AbstractNode{
    private PaletteShape shape;

    public ShapeNode(PaletteShape shape) {
        super(Children.LEAF);
        this.shape = shape;
        this.setDisplayName(shape.getId());
    }

    public PaletteShape getPaletteShape() {
        return this.shape;
    }

    @Override
    public Transferable drag() throws IOException {
        return shape;
    }
}
