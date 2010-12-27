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
public class ConCategoryNode extends AbstractNode{
    private String conCat;
    private ConnectionNodeContainer cnc;

    public ConCategoryNode(ConnectionNodeContainer cnc, IController controller) {
        super(cnc);
        this.cnc = cnc;
        this.conCat = cnc.getConCat();
        this.setDisplayName("Connection " + conCat);
    }

    public ConnectionNodeContainer getConNodeContainer() {
        return this.cnc;
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
