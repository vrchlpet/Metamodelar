

package org.cvut.MMN.view.core;

import java.awt.Point;
import org.cvut.MMN.model.MMNException;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

/**
 *
 * @author Vrchli
 */
public class MiniScene extends Scene implements ConTableObserver{

    private LayerWidget lw;
    private LayerWidget connectionLayer;

    private Widget sourceW = null;
    private Widget targetW = null;
    private ConnectionWidget cw = null;
    private String conName = null;

    private ConWidget conWidget;

    public MiniScene(ConWidget conWidget) {
        this.conWidget = conWidget;
        lw = new LayerWidget(this);
        addChild(lw);
        connectionLayer = new LayerWidget(this);
        addChild(connectionLayer);
        getActions().addAction (ActionFactory.createZoomAction ());
    }

    public void setSourceShape(String s) {
        if ( sourceW != null) {
            lw.removeChild(sourceW);
        }

        try {
            sourceW = conWidget.getController().getShapeWidget(this, s);
        } catch (MMNException ex) {
            ex.printStackTrace();
        }

        sourceW.getActions().addAction(ActionFactory.createMoveAction());
        sourceW.setPreferredLocation(new Point(0,0));
        lw.addChild(sourceW);

        if ( cw != null)
            cw.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceW));

        validate();
    }

    public void setTargetShape(String s) {
        if ( targetW != null) {
            lw.removeChild(targetW);
        }

        try {
            targetW = conWidget.getController().getShapeWidget(this, s);
        } catch (MMNException ex) {
            ex.printStackTrace();
        }

        
        targetW.getActions().addAction(ActionFactory.createMoveAction());
        targetW.setPreferredLocation(new Point(150,0));
        lw.addChild(targetW);
        if ( cw != null)
            cw.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetW));

        validate();
    }

    public void setConnection(String s) {
        if ( cw != null) {
            connectionLayer.removeChild(cw);

        }

        try {
            cw = conWidget.getController().getConnectionWidget(this, s);
            conName = s;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        connectionLayer.addChild(cw);
        if ( sourceW != null)
            cw.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceW));
        if ( targetW != null)
            cw.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetW));

        validate();
    }

    public void clear() {
        lw.removeChild(sourceW);
        lw.removeChild(targetW);
        connectionLayer.removeChild(cw);
        sourceW = null;
        targetW = null;
        cw = null;
        validate();
    }

    @Override
    public void rowSelected(String source, String target) {
        setSourceShape(source);
        setTargetShape(target);
        setConnection(conName);
    }

}
