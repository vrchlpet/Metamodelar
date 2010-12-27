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
public class ConnectionNode extends AbstractNode{
    private PaletteConnection con;

    public ConnectionNode(PaletteConnection con) {
        super(Children.LEAF);
        this.con = con;
        this.setDisplayName(con.getId());
    }

    public PaletteConnection getPaletteConnection() {
        return this.con;
    }

    @Override
    public Transferable drag() throws IOException {
        return con;
    }
}
