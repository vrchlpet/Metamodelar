

package org.cvut.MMN.view.core;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.cvut.MMN.controller.IController;
import org.cvut.MMN.view.dialog.EditShapeDialog;

/**
 *
 * @author Vrchli
 */
public class EditShapeActionListener implements ActionListener {

    private IController controller;

    public EditShapeActionListener(IController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dialog dialog = new EditShapeDialog(null, controller);
    }

}
