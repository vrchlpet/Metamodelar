
package org.cvut.MMN.model;

import org.cvut.MMN.model.attributes.ShapeAttribute;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Vrchli
 */
public class ShapeManager implements IShapeManager{
    private IMetamodel mm;

    public ShapeManager(IMetamodel mm) {
        this.mm = mm;
    }

    @Override
    public Shape createShape(String shapeId, String category) throws MMNException {

        ShapeCategory sc;
        Shape shape;
        if ( (sc = getShapeCategoryByCatName(category)) != null) {
            if ( (shape = getShape(shapeId)) == null) {
                shape = new Shape(shapeId);
                sc.addShape(shape);
                return shape;
            }
        }

        throw new MMNException("error");
    }

    @Override
    public void removeShape(String shapeId) {
        Shape shape = getShape(shapeId);
        ShapeCategory cat = getShapeCategoryByShapeId(shapeId);

        if (shape != null) {
            if (cat != null) {
                cat.getShapes().remove(shape);
            }
        }
    }

    @Override
    public Widget getWidget(Scene scene, String shapeId) {
        Widget widget = new Widget(scene);

        for ( ShapeAttribute at: getShape(shapeId).getAttributes()) {
            at.apply(widget);
        }
        
        return widget;
    }

    @Override
    public ShapeCategory createShapeCategory(String name) throws MMNException{
        if ( getShapeCategoryByCatName(name) == null) {
            ShapeCategory sc = new ShapeCategory(name);
            mm.getShapeCategories().add(sc);
            return sc;
        }

        throw new MMNException("error");
    }

    @Override
    public Shape getShape(String shapeId){

        for (ShapeCategory scat : mm.getShapeCategories()) {
            for ( Shape s : scat.getShapes()) {
                if ( s.getShapeId().equals(shapeId)) {
                    return s;
                }
            }
        }
        return null;
    }

    @Override
    public ShapeCategory getShapeCategoryByCatName(String name) {
        for (ShapeCategory scat : mm.getShapeCategories()) {
            if ( scat.getCategoryId().equals(name))
                return scat;
        }
        return null;
    }

    @Override
    public ShapeCategory getShapeCategoryByShapeId(String shapeId) {
        for (ShapeCategory scat : mm.getShapeCategories()) {
            for ( Shape s : scat.getShapes()) {
                if ( s.getShapeId().equals(shapeId))
                    return scat;
            }
        }
        return null;
    }

    
}
