package org.cvut.MMN.view.core;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.cvut.MMN.controller.IController;
import org.cvut.MMN.view.dialog.CreateShapeDialog;

/**
 *
 * @author Vrchli
 */
public class AddShapeActionListener implements ActionListener {

    private ShapeViewTopComponent svtc;

    public AddShapeActionListener(ShapeViewTopComponent svtc) {
        this.svtc = svtc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dialog d = new CreateShapeDialog(null, svtc);
    }
}
