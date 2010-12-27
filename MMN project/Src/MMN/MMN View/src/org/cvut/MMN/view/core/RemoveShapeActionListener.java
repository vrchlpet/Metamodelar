/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.core;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.cvut.MMN.view.dialog.RemoveShapeDialog;

/**
 *
 * @author Vrchli
 */
public class RemoveShapeActionListener implements ActionListener{

    private ShapeViewTopComponent svtc;



    public RemoveShapeActionListener(ShapeViewTopComponent x) {
        this.svtc = x;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dialog dialog = new RemoveShapeDialog(null, svtc);
    }

}
