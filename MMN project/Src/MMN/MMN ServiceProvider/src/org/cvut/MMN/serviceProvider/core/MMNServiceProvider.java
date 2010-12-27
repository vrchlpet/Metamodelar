/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.serviceProvider.core;

import org.cvut.MMN.model.IMetamodel;
import org.cvut.MMN.model.ISerializer;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Vrchli
 */
@ServiceProvider(service = IMMNServiceProvider.class)
public class MMNServiceProvider implements IMMNServiceProvider {

    @Override
    public Widget getShape(Scene scene, String pathToNotation, String shapeId) {
        ISerializer serializer = Lookup.getDefault().lookup(ISerializer.class);
        IMetamodel mm = serializer.loadMetamodel(pathToNotation);
        return mm.getShapeManager().getWidget(scene, shapeId);
    }

    @Override
    public ConnectionWidget getConnection(Scene scene, String pathToNotation, String connectionId) {
        ISerializer serializer = Lookup.getDefault().lookup(ISerializer.class);
        IMetamodel mm = serializer.loadMetamodel(pathToNotation);
        return mm.getConnectionManager().getConnectionWidget(scene, connectionId);
    }

}
