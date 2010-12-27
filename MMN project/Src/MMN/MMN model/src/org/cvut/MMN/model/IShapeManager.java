
package org.cvut.MMN.model;

import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Vrchli
 */
public interface IShapeManager {
    public Shape createShape(String shapeId, String category) throws MMNException;
    public void removeShape(String shapeId);
    public Shape getShape(String shapeId);
    public Widget getWidget(Scene scene, String shapeId);

    public ShapeCategory createShapeCategory(String name) throws MMNException;
    public ShapeCategory getShapeCategoryByCatName(String name);
    public ShapeCategory getShapeCategoryByShapeId(String shapeId);
}
