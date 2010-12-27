

package org.cvut.MMN.view.palette;



import java.util.HashMap;
import org.cvut.MMN.controller.IController;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Vrchli
 */
public class ConCategoryContainer extends Children.Keys<String>{

    private IController controller;
    private java.util.Map<String,ConCategoryNode> map;

    public ConCategoryContainer(IController controller) {
        this.controller = controller;
        map = new HashMap<String, ConCategoryNode>();
    }

    @Override
    protected void addNotify() {
        setKeys(new String[] {"root"});
    }

    @Override
    protected Node[] createNodes(String t) {
        String [] conCat = controller.getPaletteCategories();

        ConCategoryNode [] conCatNodes = new ConCategoryNode[conCat.length];

        for ( int i = 0; i < conCat.length; i++) {
            conCatNodes[i] = new ConCategoryNode(new ConnectionNodeContainer(conCat[i], controller), controller);
            map.put(conCat[i], conCatNodes[i]);
        }

        return conCatNodes;
    }

    public void addConnection(String con, String category) {
        map.get(category).getConNodeContainer().addConnection(con);
    }

    public void removeConnection(String con, String category) {
        map.get(category).getConNodeContainer().removeConnection(con);
    }

}
