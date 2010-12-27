/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.core;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.cvut.MMN.view.dialog.RemoveConnectionDialog;

/**
 *
 * @author Vrchli
 */
public class RemoveConnectionActionListener implements ActionListener{
    private ConViewTopComponent cvtc;



    public RemoveConnectionActionListener(ConViewTopComponent x) {
        this.cvtc = x;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dialog dialog = new RemoveConnectionDialog(null, cvtc);
    }
}
