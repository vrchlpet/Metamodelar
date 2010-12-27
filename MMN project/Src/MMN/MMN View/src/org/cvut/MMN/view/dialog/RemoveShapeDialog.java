
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
import javax.swing.JOptionPane;
import org.cvut.MMN.view.core.ShapeViewTopComponent;

/**
 *
 * @author Vrchli
 */
public class RemoveShapeDialog extends Dialog{


    private JLabel lb;
    private ShapeList shapeList;
    private JButton btRemove;
    private final ShapeViewTopComponent svtc;


    public RemoveShapeDialog(Frame frame, final ShapeViewTopComponent svtc) {
        super(frame,"remove shape");

        this.svtc = svtc;

        lb = new JLabel("Shapes");
        shapeList = new ShapeList();
        btRemove = new JButton("remove selected shape");

        fillList();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                RemoveShapeDialog.this.dispose();
            }
        });

        btRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShapeItem item = shapeList.getSelectedShape();

                if ( item != null) {
                    String [] cons = svtc.getController().getConsWithRuleAssociatedWithShape(item.getShapeName());
                    if ( cons.length  > 0) {
                        String msg = "";
                        for ( int i = 0; i < cons.length; i++) {
                            if ( i > 0)
                                msg += ", ";

                            msg += cons[i];
                        }
                        JOptionPane.showMessageDialog(null, "There are connections with rules associated with this shape: \n" + msg + ".");
                    } else {
                        svtc.getShapePaletteManager().getShapeCategoryContainer().
                                removeShape(item.getShapeName(), item.getShapeCategory());
                        svtc.getController().removeShape(item.getShapeName());
                        RemoveShapeDialog.this.dispose();
                    }
                }
            }
        });


        setLayout(new BorderLayout());
        add(lb, BorderLayout.NORTH);
        add(shapeList, BorderLayout.CENTER);
        add(btRemove, BorderLayout.SOUTH);
        setLocation(300, 300);
        pack();
        setVisible(true);


    }

    private void fillList() {
        String [][] shapes = svtc.getController().getShapeGroups();
        for ( int i = 0; i < shapes.length; i++) {
            shapeList.addItem(shapes[i][0], shapes[i][1]);
        }
    }

}