

package org.cvut.MMN.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.cvut.MMN.controller.IController;
import org.cvut.MMN.model.Shape;
import org.cvut.MMN.model.attributes.ImageAttribute;
import org.cvut.MMN.model.attributes.LabelAttribute;
import org.cvut.MMN.model.attributes.ShapeAttribute;

/**
 *
 * @author Vrchli
 */
public class EditShapeDialog extends Dialog implements ItemListener, ActionListener{

    private IController controller;

    private ShapeList shapeList;
    private JLabel lbShapeListTitle;
    private JPanel jpShapeList;

    private JLabel lbShapeLabel;
    private JLabel lbShapeImage;

    private JTextField txShapeLabel;
    private JLabel lbShapeImageText;
    private JPanel jpShapeAttributes;

    private JButton btOk;
    private JPanel jpSaveButton;

    private JPanel jpLabel;
    private JPanel jpImage;

    private JFileChooser imgFileChooser;
    private File imgFile;
    private JButton chooseFile;
    
    private JButton btEraseImage;
    private JButton btSetLabel;
    private JButton btEraseLabel;
    private JPanel jpBtLabel;

    private final String NONE = "(not selected)";

    private Shape shape;

    public EditShapeDialog(Frame frame, IController controller) {
        super(frame,"Edit shape dialog");
        this.controller = controller;

        initComponent();

        String [][] shapes = controller.getShapeGroups();
        for ( int i = 0; i < shapes.length; i++) {
            shapeList.addItem(shapes[i][0], shapes[i][1]);
        }
    }

    private void initComponent() {
        shapeList = new ShapeList();
        lbShapeListTitle = new JLabel("Shapes:");
        jpShapeList = new JPanel();
        lbShapeLabel = new JLabel("Shape label: ");
        lbShapeImage = new JLabel("Shape image: ");
        txShapeLabel = new JTextField( NONE, 10);
        lbShapeImageText = new JLabel(NONE);
        jpShapeAttributes = new JPanel();
        jpShapeAttributes.setBorder(BorderFactory.createTitledBorder("Attributes"));
        btOk = new JButton("Ok");
        jpSaveButton = new JPanel();
        jpLabel = new JPanel();
        jpImage = new JPanel();
        jpImage.setLayout( new BorderLayout());
        jpLabel.setLayout( new BorderLayout());


        imgFileChooser = new JFileChooser();
        imgFile = null;
        chooseFile = new JButton("Choose File");
        chooseFile.addActionListener(new ChooseFileListener());

        btOk.addActionListener(this);

        jpShapeList.setLayout( new BorderLayout());
        jpShapeAttributes.setLayout( new GridLayout(2,1));
        jpSaveButton.setLayout( new BorderLayout());

        btEraseImage = new JButton("Remove");
        btEraseImage.addActionListener(this);



        jpShapeList.add(lbShapeListTitle, BorderLayout.NORTH);
        jpShapeList.add(shapeList);
        jpShapeAttributes.add(jpLabel);
        jpShapeAttributes.add(jpImage);

        btSetLabel = new JButton("Set");
        btEraseLabel = new JButton("Remove");
        btSetLabel.addActionListener(this);
        btEraseLabel.addActionListener(this);
        jpBtLabel = new JPanel();
        jpBtLabel.add(btEraseLabel);
        jpBtLabel.add(btSetLabel);

        JPanel jpButtons = new JPanel();
        jpButtons.add(btEraseImage);
        jpButtons.add(chooseFile);
        jpLabel.add(lbShapeLabel, BorderLayout.WEST);
        jpLabel.add(txShapeLabel, BorderLayout.CENTER);
        jpLabel.add(jpBtLabel, BorderLayout.EAST);
        jpImage.add(lbShapeImage, BorderLayout.WEST);
        jpImage.add(lbShapeImageText, BorderLayout.CENTER);
        jpImage.add(jpButtons, BorderLayout.EAST);





        jpSaveButton.add(btOk, BorderLayout.EAST);

        shapeList.addItemListener(this);

        this.setLayout( new BorderLayout());
        this.add(jpShapeList, BorderLayout.WEST);
        this.add(jpShapeAttributes, BorderLayout.CENTER);
        this.add(jpSaveButton, BorderLayout.SOUTH);

        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EditShapeDialog.this.dispose();
            }
        });

        this.pack();
        this.setLocation(300,300);
        this.setVisible(true);

        shape = null;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        ShapeItem shapex = shapeList.getSelectedShape();
        this.shape = controller.getMetamodel().getShapeManager().getShape(shapex.getShapeName());
        txShapeLabel.setText(NONE);
        lbShapeImageText.setText(NONE);

        for ( ShapeAttribute sa : this.shape.getAttributes()) {
            if ( sa instanceof ImageAttribute) {
                lbShapeImageText.setText( ((ImageAttribute)sa).getPath() );
            } else if (sa instanceof LabelAttribute) {
                txShapeLabel.setText( ((LabelAttribute)sa).getLabelText() );
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == btEraseImage) {
            if ( shape != null) {
                for ( ShapeAttribute sa : shape.getAttributes()) {
                    if ( sa instanceof ImageAttribute) {
                        shape.getAttributes().remove(sa);
                        lbShapeImageText.setText(NONE);
                        JOptionPane.showMessageDialog(this, "Image has been removed.");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "There is no image!");
            } else {
                JOptionPane.showMessageDialog(this, "Select shape first!");
            }
        } else if (e.getSource() == btOk) {
            this.dispose();
        } else if ( e.getSource() == btEraseLabel) {
            if ( shape != null) {
                for ( ShapeAttribute sa : shape.getAttributes()) {
                    if ( sa instanceof LabelAttribute) {
                        shape.getAttributes().remove(sa);
                        txShapeLabel.setText(NONE);
                        JOptionPane.showMessageDialog(this, "Label has been removed.");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "There is no label!");
            } else {
                JOptionPane.showMessageDialog(this, "Select shape first!");
            }
        } else if ( e.getSource() == btSetLabel) {
            if ( shape != null) {
                x:for ( ShapeAttribute sa : shape.getAttributes()) {
                    if ( sa instanceof LabelAttribute) {
                        shape.getAttributes().remove(sa);
                        break x;
                    }
                }
                shape.getAttributes().add(new LabelAttribute(txShapeLabel.getText()));
                JOptionPane.showMessageDialog(this, "Label has been set.");
            } else {
                JOptionPane.showMessageDialog(this, "Select shape first!");
            }
        }
    }

    private class ChooseFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ( shape != null) {
                int result = EditShapeDialog.this.imgFileChooser.showOpenDialog(EditShapeDialog.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    EditShapeDialog.this.imgFile = EditShapeDialog.this.imgFileChooser.getSelectedFile();
                    if ( imgFile.isFile()) {
                        EditShapeDialog.this.lbShapeImageText.setText(EditShapeDialog.this.imgFile.getAbsolutePath());
                        x:for ( ShapeAttribute sa : shape.getAttributes()) {
                            if ( sa instanceof ImageAttribute) {
                                shape.getAttributes().remove(sa);
                                break x;
                            }
                        }
                        shape.getAttributes().add(new ImageAttribute(lbShapeImageText.getText()));
                        JOptionPane.showMessageDialog(EditShapeDialog.this, "Image has been set.");
                        return;
                    } else {
                        JOptionPane.showMessageDialog(EditShapeDialog.this, "Wrong fiel!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(EditShapeDialog.this, "Select shape first!");
            }
        }
    }

}
