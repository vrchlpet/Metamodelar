

package org.cvut.MMN.view.core;

import org.cvut.MMN.controller.IController;
import org.cvut.MMN.model.ConnectionRule;
import org.netbeans.api.visual.action.AcceptProvider;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Vrchli
 */
public class GraphSceneImpl extends GraphScene<String, String>{

    private LayerWidget mainLayer;
    private IController controller;

    public GraphSceneImpl(IController controller) {

        this.controller = controller;
        mainLayer = new LayerWidget (this);
        addChild (mainLayer);
    }

    public void addAcceptAction(AcceptProvider ap) {
        getActions().addAction(ActionFactory.createAcceptAction(ap));
    }

    @Override
    protected Widget attachNodeWidget(String n) {
        ConWidget w = new ConWidget(this, new Connection(n), controller);
        w.getMiniScene().setConnection(n);

        for ( ConnectionRule cr : controller.getConnection(n).getRules()) {
            w.getCon().addItem(new ConnectionItem(new Shape(cr.getSource().getShapeId()), new Shape(cr.getTarget().getShapeId())
                    , cr.getTargetM(), cr.getSourcM()));
        }
        mainLayer.addChild(w);
        return w;
    }

    @Override
    protected Widget attachEdgeWidget(String e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void attachEdgeSourceAnchor(String e, String n, String n1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void attachEdgeTargetAnchor(String e, String n, String n1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    

}
