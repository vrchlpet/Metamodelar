

package org.cvut.MMN.controller;

import java.util.ArrayList;
import java.util.List;
import org.cvut.MMN.model.Connection;
import org.cvut.MMN.model.ConnectionRule;
import org.cvut.MMN.model.IMetamodel;
import org.cvut.MMN.model.attributes.ConnectionAttribute;
import org.cvut.MMN.model.MMNException;
import org.cvut.MMN.model.Shape;
import org.cvut.MMN.model.attributes.ShapeAttribute;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Vrchli
 */
public interface IController {

    public boolean isMetamodelLoaded();
    public IMetamodel getMetamodel();
    public String[] getPaletteCategories();
    public String[] getShapePaletteCategories();
    public String [][] getConnectionGroups();
    public String [][] getShapeGroups();
    public Widget getShapeWidget(Scene scene, String id) throws MMNException;
    public ConnectionWidget getConnectionWidget(Scene scene, String id) throws MMNException;
    public void createConnection(String conId, String source,
            int sourcM, String target, int targetM) throws MMNException;
    public void removeShape(String id);
    public void removeConnection(String id);

    public String [] getConsWithRuleAssociatedWithShape(String shapeId);

    public ArrayList<String> getShapes();
    public Connection getConnection(String conId);
    public Shape getShape(String id);
    public void removeConnectionRull(String conId, String source, String target);

    /**
     * Creates new Shape in the metamodel according to input parameters.
     *
     * @param name Shape name
     * @param category Shape category
     * @param attributes Shape attributes
     * @return True if the new shape is created, false if the shape with
     *         the same name already exists
     * @throws MMNException If metamodel is unable to create new shape
     */
    public boolean createNewShape(String name, String category, List<ShapeAttribute> attributes) throws MMNException;

    /**
     * Creates new Connection in the metamodel according to input parameters.
     *
     * @param name Connection name
     * @param category Connection category
     * @param attributes Connection attributes
     * @return True if the new connection is created, false if the connection
     *         with the same name already exists
     * @throws MMNException If metamodel is unable to create new shape
     */
    public boolean createNewConnection(String name, String category, List<ConnectionAttribute> attributes) throws MMNException;
}
