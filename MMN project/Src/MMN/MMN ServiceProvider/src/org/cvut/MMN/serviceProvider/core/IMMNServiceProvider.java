/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.serviceProvider.core;

import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Vrchli
 */
public interface IMMNServiceProvider {


    public Widget getShape(Scene scene, String pathToNotation, String shapeId);
    public ConnectionWidget getConnection(Scene scene, String pathToNotation, String connectionId);


}
