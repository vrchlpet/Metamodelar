package org.cvut.MMN.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Vrchli
 */
public class ShapeCategory {

    private ArrayList<Shape> shapes;
    private String categoryId;

    public ShapeCategory() {
        this("unknown");
    }

    public ShapeCategory(String id) {
        this.categoryId = id;
        shapes = new ArrayList<Shape>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @XmlElement(name = "shapes")
    public ArrayList<Shape> getShapes() {
        if (shapes == null) {
            shapes = new ArrayList<Shape>();
        }
        return this.shapes;
    }

    @XmlAttribute()
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
