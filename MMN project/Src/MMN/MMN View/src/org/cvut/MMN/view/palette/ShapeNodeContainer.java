/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.palette;

import java.util.ArrayList;
import java.util.HashMap;
import org.cvut.MMN.controller.IController;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Vrchli
 */
public class ShapeNodeContainer extends Children.Keys<String>{

    private String conCat;
    private IController controller;
    private java.util.Map<String, ShapeNode> map;


    public ShapeNodeContainer(String conCat, IController controller) {
        this.conCat = conCat;
        this.controller = controller;
        map = new HashMap<String,ShapeNode>();
    }

    public String getConCat() {
        return this.conCat;
    }

    @Override
    protected void addNotify() {
        setKeys(new String[] {conCat});
    }

    public void addShape(String shape) {
        ShapeNode sn = new ShapeNode(new PaletteShape(shape, conCat));
        map.put(shape, sn);
        ShapeNode [] n = new ShapeNode [] {sn};
        
        //setKeys(new String [] {shape});
        add(n);
    }

    public void removeShape(String shape) {
        ShapeNode sn = map.get(shape);

        remove(new ShapeNode[]{sn});
    }

    @Override
    protected Node[] createNodes(String t) {
        String [][] items = controller.getShapeGroups();

        ArrayList<ShapeNode> itemsNode = new ArrayList<ShapeNode>();

        for ( int i = 0; i < items.length; i++) {
            if ( items[i][1].equals(conCat)) {
                itemsNode.add(new ShapeNode(
                    new PaletteShape(items[i][0], items[i][1])));
            }
        }

        Node [] nn = new Node[itemsNode.size()];
        for ( int i = 0; i < nn.length; i++) {
            nn[i] = itemsNode.get(i);
            map.put(itemsNode.get(i).getPaletteShape().getId(),itemsNode.get(i));
        }

        return nn;
    }

}
