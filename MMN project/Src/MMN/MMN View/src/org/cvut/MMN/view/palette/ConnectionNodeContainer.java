/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.palette;

import java.util.ArrayList;
import java.util.HashMap;
import org.cvut.MMN.controller.IController;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Vrchli
 */
public class ConnectionNodeContainer extends Children.Keys<String>{

    private String conCat;
    private IController controller;
    private java.util.Map<String, ConnectionNode> map;

    public ConnectionNodeContainer(String conCat, IController controller) {
        this.conCat = conCat;
        this.controller = controller;
        map = new HashMap<String,ConnectionNode>();
    }

    public String getConCat() {
        return this.conCat;
    }

    public void addConnection(String con) {
        ConnectionNode cn = new ConnectionNode(new PaletteConnection(con, conCat));
        map.put(con, cn);
        ConnectionNode [] n = new ConnectionNode [] {cn};

        //setKeys(new String [] {shape});
        add(n);
    }

    public void removeConnection(String con) {
        ConnectionNode cn = map.get(con);

        remove(new ConnectionNode[]{cn});
    }

    @Override
    protected void addNotify() {
        setKeys(new String[] {conCat});
    }

    @Override
    protected Node[] createNodes(String t) {
        String [][] items = controller.getConnectionGroups();

        ArrayList<ConnectionNode> itemsNode = new ArrayList<ConnectionNode>();
        
        for ( int i = 0; i < items.length; i++) {
            if ( items[i][1].equals(conCat)) {
                itemsNode.add(new ConnectionNode(
                    new PaletteConnection(items[i][0], items[i][1])));
            }
        }
        
        Node [] nn = new Node[itemsNode.size()];
        for ( int i = 0; i < nn.length; i++) {
            nn[i] = itemsNode.get(i);
            map.put(itemsNode.get(i).getPaletteConnection().getId(),itemsNode.get(i));
        }

        return nn;
    }

}
