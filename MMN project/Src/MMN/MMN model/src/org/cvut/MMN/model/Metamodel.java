package org.cvut.MMN.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Vrchli
 */
@ServiceProvider(service = IMetamodel.class)
@XmlRootElement(name = "metamodel")
public class Metamodel implements IMetamodel {

    private ArrayList<ShapeCategory> shapeCategories;
    private ArrayList<ConnectionCategory> conCategories;
    private String name;

    public Metamodel() {
        this("mm");
    }

    public Metamodel(String name) {
        this.name = name;
        shapeCategories = new ArrayList<ShapeCategory>();
        conCategories = new ArrayList<ConnectionCategory>();
    }

    @Override
    public void addCategory(ShapeCategory cat) {
        shapeCategories.add(cat);
    }

    @Override
    public void addCategory(ConnectionCategory cat) {
        conCategories.add(cat);
    }

    @Override
    @XmlElement(name = "ShapeCategory")
    public ArrayList<ShapeCategory> getShapeCategories() {
        if (shapeCategories == null) {
            shapeCategories = new ArrayList<ShapeCategory>();
        }
        return shapeCategories;
    }

    @Override
    @XmlElement(name = "ConnectionCategory")
    public ArrayList<ConnectionCategory> getConCategories() {
        if (conCategories == null) {
            conCategories = new ArrayList<ConnectionCategory>();
        }
        return conCategories;
    }

    @Override
    @XmlAttribute()
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IShapeManager getShapeManager() {
        return new ShapeManager(this);
    }

    @Override
    public IConnectionManager getConnectionManager() {
        return new ConnectionManager(this);
    }
}
