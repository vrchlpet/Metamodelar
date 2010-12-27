

package org.cvut.MMN.view.core;

import org.cvut.MMN.controller.IController;
import org.cvut.MMN.model.MMNException;
import org.netbeans.api.visual.action.AcceptProvider;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

/**
 *
 * @author Vrchli
 */
public class EditorSceneImpl extends Scene {

    private LayerWidget mainLayer;
    private LayerWidget conLayer;
    private IController controller;

    public EditorSceneImpl(IController controller) {
        this.controller = controller;
        mainLayer = new LayerWidget (this);
        addChild (mainLayer);
        conLayer = new LayerWidget (this);
        addChild (conLayer);
        getActions().addAction (ActionFactory.createZoomAction ());
    }


    public void addAcceptAction(AcceptProvider ap) {
        getActions().addAction(ActionFactory.createAcceptAction(ap));
    }


    protected Widget attachNodeWidget(String n) {
        Widget w = null;
        try {
            w = controller.getShapeWidget(this, n);
        } catch (MMNException ex) {
            Exceptions.printStackTrace(ex);
        }
        mainLayer.addChild(w);
        validate();
        return w;
    }


}
