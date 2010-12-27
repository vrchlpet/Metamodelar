/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cvut.MMN.core;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;

public final class GenerateNotationXMLAction implements ActionListener {

    private final DataObject context;

    public GenerateNotationXMLAction(DataObject context) {
        this.context = context;
    }

    public void actionPerformed(ActionEvent ev) {
        FileObject f = context.getPrimaryFile();
        String displayName = FileUtil.getFileDisplayName(f);
        String msg = "I am " + displayName + ". Hear me roar!";
        NotifyDescriptor nd = new NotifyDescriptor.Message(msg);
        DialogDisplayer.getDefault().notify(nd);

    }
}
