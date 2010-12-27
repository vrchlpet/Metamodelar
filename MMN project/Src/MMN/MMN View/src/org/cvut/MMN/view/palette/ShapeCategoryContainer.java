

package org.cvut.MMN.view.palette;



import java.util.HashMap;
import org.cvut.MMN.controller.IController;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Vrchli
 */
public class ShapeCategoryContainer extends Children.Keys<String>{

    private IController controller;
    private java.util.Map<String,ShapeCategoryNode> map;

    public ShapeCategoryContainer(IController controller) {
        this.controller = controller;
        map = new HashMap<String, ShapeCategoryNode>();
    }

    @Override
    protected void addNotify() {
        setKeys(new String[] {"root"});
    }

    @Override
    protected Node[] createNodes(String t) {
        String [] conCat = controller.getShapePaletteCategories();

        ShapeCategoryNode [] conCatNodes = new ShapeCategoryNode[conCat.length];

        for ( int i = 0; i < conCat.length; i++) {
            conCatNodes[i] = new ShapeCategoryNode(new ShapeNodeContainer(conCat[i], controller), controller);
            map.put(conCat[i], conCatNodes[i]);
        }

        return conCatNodes;
    }

    public void addShape(String shape, String category) {
        map.get(category).getShapeNodeContainer().addShape(shape);
    }

    public void removeShape(String shape, String category) {
        map.get(category).getShapeNodeContainer().removeShape(shape);
    }

}
