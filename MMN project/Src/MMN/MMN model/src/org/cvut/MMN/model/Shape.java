package org.cvut.MMN.model;

import org.cvut.MMN.model.attributes.ShapeAttribute;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Vrchli
 */
public class Shape {

    private ArrayList<ShapeAttribute> attributes;
    private String shapeId;

    public Shape() {
        this("unknown");
    }

    public Shape(String shapeId) {
        this.shapeId = shapeId;
        attributes = new ArrayList<ShapeAttribute>();
    }

    public void addAttribute(ShapeAttribute at) {
        attributes.add(at);
    }

    public ArrayList<ShapeAttribute> getAttributes() {
        return this.attributes;
    }

    @XmlAttribute()
    public String getShapeId() {
        return shapeId;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }
}
