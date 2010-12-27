package org.cvut.MMN.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cvut.MMN.model.Connection;
import org.cvut.MMN.model.attributes.ConnectionAttribute;
import org.cvut.MMN.model.ConnectionCategory;
import org.cvut.MMN.model.ConnectionRule;
import org.cvut.MMN.model.IMetamodel;
import org.cvut.MMN.model.MMNException;
import org.cvut.MMN.model.Shape;
import org.cvut.MMN.model.attributes.ShapeAttribute;
import org.cvut.MMN.model.ShapeCategory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Vrchli
 */
public class Controller implements IController {

    private IMetamodel mm;

    public Controller(IMetamodel mm) {

        this.mm = mm;
    }

    @Override
    public String[] getPaletteCategories() {
        String[] cat = new String[mm.getConCategories().size()];
        int i = 0;
        for (ConnectionCategory cc : mm.getConCategories()) {
            cat[i++] = cc.getCategoryId();
        }
        return cat;
    }

    @Override
    public String[][] getConnectionGroups() {
        int size = 0;
        for (ConnectionCategory cc : mm.getConCategories()) {
            size += cc.getConnection().size();
        }

        String[][] s = new String[size][2];

        for (ConnectionCategory cc : mm.getConCategories()) {
            for (Connection c : cc.getConnection()) {
                --size;
                s[size][0] = c.getConId();
                s[size][1] = cc.getCategoryId();
            }
        }

        return s;
    }

    @Override
    public Widget getShapeWidget(Scene scene, String id) throws MMNException {
        return mm.getShapeManager().getWidget(scene, id);
    }

    @Override
    public ConnectionWidget getConnectionWidget(Scene scene, String id) throws MMNException {
        return mm.getConnectionManager().getConnectionWidget(scene, id);
    }

    @Override
    public void createConnection(String conId, String source, int sourcM,
                                    String target, int targetM) throws MMNException {
        mm.getConnectionManager().createRule(conId, source, sourcM, target, targetM);
    }

    @Override
    public ArrayList<String> getShapes() {
        ArrayList<String> shapes = new ArrayList<String>();

        for (ShapeCategory sc : mm.getShapeCategories()) {
            for (Shape s : sc.getShapes()) {
                shapes.add(s.getShapeId());
            }
        }
        return shapes;
    }

    @Override
    public Connection getConnection(String conId) {
        return mm.getConnectionManager().getConnection(conId);
    }

    @Override
    public void removeConnectionRull(String conId, String source, String target) {
        mm.getConnectionManager().removeRule(conId, source, target);
    }

    @Override
    public boolean isMetamodelLoaded() {
        return !(mm == null);
    }

    @Override
    public String[] getShapePaletteCategories() {
        String[] cat = new String[mm.getShapeCategories().size()];
        int i = 0;
        for (ShapeCategory sc : mm.getShapeCategories()) {
            cat[i++] = sc.getCategoryId();
        }
        return cat;
    }



    /*
     *  The second dimension of returning filed has 2 string.
     *  The first string is a shape id.
     *  The second string is a category of the shape.
     *
     */
    @Override
    public String[][] getShapeGroups() {
        int size = 0;
        for (ShapeCategory sc : mm.getShapeCategories()) {
            size += sc.getShapes().size();
        }

        String[][] s = new String[size][2];

        for (ShapeCategory sc : mm.getShapeCategories()) {
            for (Shape ss : sc.getShapes()) {
                --size;
                s[size][0] = ss.getShapeId();
                s[size][1] = sc.getCategoryId();
            }
        }

        return s;
    }

    @Override
    public boolean createNewShape(String name, String category, List<ShapeAttribute> attributes) throws MMNException {
        if (mm.getShapeManager().getShape(name) != null) {
            return false;
        }

        Shape shape = mm.getShapeManager().createShape(name, category);
        for (ShapeAttribute attr : attributes) {
            shape.addAttribute(attr);
        }

        return true;
    }

    @Override
    public boolean createNewConnection(String name, String category, List<ConnectionAttribute> attributes) throws MMNException {
        if (mm.getConnectionManager().getConnection(name) != null) {
            return false;
        }

        Connection connection = mm.getConnectionManager().createConnection(name, category);
        for (ConnectionAttribute attr : attributes) {
            connection.addAttribute(attr);
        }

        return true;
    }


    // pokud existuje nejake pravidlo/a spojene s odebiranym tvarem,
    // pak se odebere i dane pravidlo/a
    // krkolomne reseni s MAP tridami je kvuli vyhnuti se ConcurrentModificationException
    @Override
    public void removeShape(String id) {
        mm.getShapeManager().removeShape(id);

        Map<Connection, Map<Integer, ConnectionRule>> map = new HashMap<Connection, Map<Integer, ConnectionRule>>();
        Map<Integer, ConnectionRule> del = new HashMap<Integer, ConnectionRule>();

        for ( ConnectionCategory ca : mm.getConCategories()) {
            for ( Connection con : ca.getConnection()) {
                for ( ConnectionRule cr : con.getRules()) {
                    if ( cr.getSource().getShapeId().equals(id) ||
                            cr.getTarget().getShapeId().equals(id)) {
                        del.put(new Integer(con.getRules().indexOf(cr)), cr);
                        map.put(con, del);
                    }
                }
            }
        }

        for ( Connection con : map.keySet()) {
            for ( Integer i : map.get(con).keySet()) {
                con.getRules().remove(del.get(i.intValue()));
            }
        }
    }

    @Override
    public IMetamodel getMetamodel() {
        return this.mm;
    }

    @Override
    public void removeConnection(String id) {
        mm.getConnectionManager().removeConnection(id);
    }

    @Override
    public Shape getShape(String id) {
        return mm.getShapeManager().getShape(id);
    }

    @Override
    public String [] getConsWithRuleAssociatedWithShape(String shapeId) {
        ArrayList<String> cons = new ArrayList<String>();

        for (ConnectionCategory cc : mm.getConCategories()) {
            for ( Connection con : cc.getConnection()) {
                x:for ( ConnectionRule cr : mm.getConnectionManager().getRules(con.getConId())) {
                    if ( cr.getSource().getShapeId().equals(shapeId) ||
                            cr.getTarget().getShapeId().equals(shapeId)) {
                        cons.add(con.getConId());
                        break x;
                    }
                }
            }
        }

        String [] cons2 = new String[cons.size()];
        int i = 0;
        for ( String s : cons) {
            cons2[i++] = s;
        }

        return cons2;
    }
}
