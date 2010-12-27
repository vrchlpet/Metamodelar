

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
import org.netbeans.api.visual.action.AcceptProvider;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Utilities;

/**
 *
 * @author Vrchli
 */
public class SceneAcceptProvider implements AcceptProvider{

    private GraphScene scene;

    public SceneAcceptProvider(GraphScene scene) {
        this.scene = scene;
    }




    @Override
    public ConnectorState isAcceptable(Widget widget, Point point, Transferable transferable) {
        Image dragImage = Utilities.loadImage("org/cvut/MMN/editor/palette.ea.jpg");
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
        PaletteConnection con = getConnectionFromTransferable(transferable);
        Widget w = null;
        try {
            w = scene.addNode(con.getId());
        } catch(AssertionError ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Object \"" + con.getId() + "\" already lies on the scene");
            return;
        }
        w.setPreferredLocation(widget.convertLocalToScene(point));
        w.getActions().addAction(ActionFactory.createSelectAction(new WidgetToTheFrontAction()));
    }

    private PaletteConnection getConnectionFromTransferable(Transferable transferable) {
        Object o = null;
        try {
            o = transferable.getTransferData(PaletteConnection.DATA_FLAVOR);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (UnsupportedFlavorException ex) {
            ex.printStackTrace();
        }
        return (PaletteConnection)o;
    }


}
