

package org.cvut.MMN.view.core;

import java.awt.Image;
import java.io.Serializable;
import org.cvut.MMN.controller.IController;
import org.netbeans.core.spi.multiview.MultiViewDescription;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.openide.util.HelpCtx;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 *
 * @author Vrchli
 */
public class MultiViewShapeDescription implements MultiViewDescription, Serializable{

    private IController controller;

    public MultiViewShapeDescription(IController controller) {
        this.controller = controller;
    }

    public MultiViewElement createElement() {
        return new ShapeViewTopComponent(controller);
    }
    public String preferredID() {
        return "PANEL_2";
    }
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;
    }
    public String getDisplayName() {
        return "Editor";
    }
    public Image getIcon() {
        return Utilities.loadImage("org/cvut/MMN/view/core/MetamodelIcon.png");
    }
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

}
