/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.core;


import org.cvut.MMN.controller.Controller;
import org.cvut.MMN.controller.IController;
import org.cvut.MMN.model.IMetamodel;
import org.cvut.MMN.view.core.MultiViewShapeDescription;
import org.cvut.MMN.view.core.MultiViewConDescription;
import org.netbeans.core.spi.multiview.MultiViewDescription;
import org.netbeans.core.spi.multiview.MultiViewFactory;
import org.openide.cookies.CloseCookie;
import org.openide.cookies.OpenCookie;
import org.openide.loaders.OpenSupport;
import org.openide.windows.CloneableTopComponent;

class MMOpenSupport extends OpenSupport implements OpenCookie, CloseCookie {

    public MMOpenSupport(MMDataObject.Entry entry) {
        super(entry);
    }

    @Override
    protected CloneableTopComponent createCloneableTopComponent() {

        
        MMDataObject dobj = (MMDataObject) entry.getDataObject();

        IMetamodel mm = (dobj.getCookie(MetamodelData.class)).getMetamodel();

        IController controller = new Controller(mm);

        MultiViewDescription dsc[] = { new MultiViewConDescription(controller),
                                       new MultiViewShapeDescription(controller)};

        CloneableTopComponent tc = MultiViewFactory.createCloneableMultiView(dsc, dsc[0]);
        tc.setName(dobj.getName());

        return tc;


    }

}
