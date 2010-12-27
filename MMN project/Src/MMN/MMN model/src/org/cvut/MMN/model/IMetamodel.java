

package org.cvut.MMN.model;

import java.util.ArrayList;

/**
 *
 * @author Vrchli
 */
public interface IMetamodel {
    public IShapeManager getShapeManager();
    public IConnectionManager getConnectionManager();
    public void addCategory(ShapeCategory cat);

    public void addCategory(ConnectionCategory cat);

    public ArrayList<ShapeCategory> getShapeCategories();
    public ArrayList<ConnectionCategory> getConCategories();

    public String getName();

    public void setName(String name);
}
