/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.core;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.cvut.MMN.controller.IController;
import org.cvut.MMN.view.dialog.EditConnectionDialog;
import org.openide.windows.IOProvider;


/**
 *
 * @author Vrchli
 */
public class EditConActionListener implements ActionListener {

    private IController controller;

    public EditConActionListener(IController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dialog dialog = new EditConnectionDialog(null, controller);
    }

}
