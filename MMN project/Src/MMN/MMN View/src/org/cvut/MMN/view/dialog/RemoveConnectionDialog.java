/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cvut.MMN.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.cvut.MMN.view.core.ConViewTopComponent;

/**
 *
 * @author Vrchli
 */
public class RemoveConnectionDialog extends Dialog{


    private JLabel lb;
    private ConList conList;
    private JButton btRemove;
    private final ConViewTopComponent cvtc;


    public RemoveConnectionDialog(Frame frame, final ConViewTopComponent cvtc) {
        super(frame,"remove shape");

        this.cvtc = cvtc;

        lb = new JLabel("Shapes");
        conList = new ConList();
        btRemove = new JButton("remove selected shape");

        fillList();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                RemoveConnectionDialog.this.dispose();
            }
        });

        btRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConItem item = conList.getSelectedCon();

                if ( item != null) {
                    cvtc.getConPaletteManager().getConCategoryContainer().
                            removeConnection(item.getConName(), item.getConCategory());

                    cvtc.getController().removeConnection(item.getConName());
                    RemoveConnectionDialog.this.dispose();
                }
            }
        });


        setLayout(new BorderLayout());
        add(lb, BorderLayout.NORTH);
        add(conList, BorderLayout.CENTER);
        add(btRemove, BorderLayout.SOUTH);
        setLocation(300, 300);
        pack();
        setVisible(true);


    }

    private void fillList() {
        String [][] shapes = cvtc.getController().getConnectionGroups();
        for ( int i = 0; i < shapes.length; i++) {
            conList.addItem(shapes[i][0], shapes[i][1]);
        }
    }

}
