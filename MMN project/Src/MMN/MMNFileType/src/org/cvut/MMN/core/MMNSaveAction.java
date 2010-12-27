/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cvut.MMN.core;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import org.cvut.MMN.model.IMetamodel;
import org.cvut.MMN.model.ISerializer;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;

public final class MMNSaveAction implements ActionListener {

    private final DataObject context;

    public MMNSaveAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {

        MMDataObject oo = (MMDataObject)context;

        ISerializer serializer = Lookup.getDefault().lookup(ISerializer.class);
        IMetamodel mm = (oo.getCookie(MetamodelData.class)).getMetamodel();

        serializer.saveMetamodel(mm, context.getPrimaryFile().getPath());
        JOptionPane.showMessageDialog(null, "Metamodel " + mm.getName() + " has been saved.");
        
        
    }
}
