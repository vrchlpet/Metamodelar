

package org.cvut.MMN.model;

/**
 *
 * @author Vrchli
 */
public interface ISerializer {
    public IMetamodel loadMetamodel(String path);
    public void saveMetamodel(IMetamodel mm, String path);
}
