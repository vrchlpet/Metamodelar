package org.cvut.MMN.view.core;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.cvut.MMN.controller.IController;
import org.cvut.MMN.view.dialog.CreateConnectionDialog;

/**
 *
 * @author Vrchli
 */
public class AddConActionListener implements ActionListener {

    private ConViewTopComponent cvtc;

    public AddConActionListener(ConViewTopComponent cvtc) {
        this.cvtc = cvtc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dialog dialog = new CreateConnectionDialog(null, cvtc);
    }
}
