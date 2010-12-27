

package org.cvut.MMN.view.core;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import org.cvut.MMN.view.palette.PaletteConnection;
import org.cvut.MMN.view.palette.PaletteShape;
import org.netbeans.api.visual.action.AcceptProvider;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Utilities;

/**
 *
 * @author Vrchli
 */
public class ShapeAcceptProvider implements AcceptProvider{

    private EditorSceneImpl scene;

    public ShapeAcceptProvider(EditorSceneImpl scene) {
        this.scene = scene;
    }




    @Override
    public ConnectorState isAcceptable(Widget widget, Point point, Transferable t) {
        Image dragImage = Utilities.loadImage("org/cvut/MMN/view/core/ea/jpg");
        JComponent view = scene.getView();
        Graphics2D g2 = (Graphics2D) view.getGraphics();
        Rectangle visRect = view.getVisibleRect();
        view.paintImmediately(visRect.x, visRect.y, visRect.width, visRect.height);
        g2.drawImage(dragImage,
                AffineTransform.getTranslateInstance(point.getLocation().getX(),
                point.getLocation().getY()),
                null);
        return ConnectorState.ACCEPT;
    }

    @Override
    public void accept(Widget widget, Point point, Transferable transferable) {
        PaletteShape shape = getConnectionFromTransferable(transferable);
        Widget w = null;
        w = scene.attachNodeWidget(shape.getId());
        /*try {
            w = scene.addNode(shape.getId());
        } catch(AssertionError ex) {
            JOptionPane.showMessageDialog(null, "Object \"" + shape.getId() + "\" already lies on the scene");
            return;
        }*/
        w.getActions().addAction(ActionFactory.createMoveAction());
        w.setPreferredLocation(widget.convertLocalToScene(point));
    }

    private PaletteShape getConnectionFromTransferable(Transferable transferable) {
        Object o = null;
        try {
            o = transferable.getTransferData(PaletteConnection.DATA_FLAVOR);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (UnsupportedFlavorException ex) {
            ex.printStackTrace();
        }
        return (PaletteShape)o;
    }

}
