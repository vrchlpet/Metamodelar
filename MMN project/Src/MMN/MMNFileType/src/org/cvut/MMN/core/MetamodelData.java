

package org.cvut.MMN.core;


import org.cvut.MMN.model.IMetamodel;
import org.cvut.MMN.model.ISerializer;
import org.openide.nodes.Node.Cookie;
import org.openide.util.Lookup;

/**
 *
 * @author Vrchli
 */
public class MetamodelData implements Cookie{
    private IMetamodel mm;

    public MetamodelData(String path) {
        ISerializer serializer = Lookup.getDefault().lookup(ISerializer.class);
        mm = serializer.loadMetamodel(path);
    }

    public IMetamodel getMetamodel() {
        return this.mm;
    }

}
