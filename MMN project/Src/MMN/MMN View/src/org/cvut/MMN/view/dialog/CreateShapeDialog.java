package org.cvut.MMN.view.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.cvut.MMN.controller.IController;
import org.cvut.MMN.model.attributes.ImageAttribute;
import org.cvut.MMN.model.attributes.LabelAttribute;
import org.cvut.MMN.model.MMNException;
import org.cvut.MMN.model.attributes.ShapeAttribute;
import org.cvut.MMN.view.core.ShapeViewTopComponent;
import org.openide.util.Exceptions;

/**
 *
 * @author matej
 */
public class CreateShapeDialog extends JDialog {

    private class ChooseFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int result = CreateShapeDialog.this.imgFileChooser.showOpenDialog(CreateShapeDialog.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                CreateShapeDialog.this.imgFile = CreateShapeDialog.this.imgFileChooser.getSelectedFile();
                CreateShapeDialog.this.selectedImgLabel.setText(CreateShapeDialog.this.imgFile.getName());
            }
        }
    }

    private class SubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CreateShapeDialog.this.checkForm();
        }
    }

    // GUI components
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JTextField labelAttrTextField;
    private JFileChooser imgFileChooser;
    private JLabel selectedImgLabel;
    // parent window
    Frame parent;
    // selected img file
    private File imgFile;
    // controller
    private IController controller;

    private ShapeViewTopComponent svtc;

    public CreateShapeDialog(Frame parent, ShapeViewTopComponent svtc) {
        super(parent, "New Shape", true);
        this.controller = svtc.getController();
        this.parent = parent;
        this.svtc = svtc;
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        JPanel shapePanel = new JPanel();
        JPanel attrPanel = new JPanel();
        GroupLayout mainLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainLayout);
        GroupLayout shapeLayout = new GroupLayout(shapePanel);
        shapePanel.setLayout(shapeLayout);
        GroupLayout attrLayout = new GroupLayout(attrPanel);
        attrPanel.setLayout(attrLayout);

        shapePanel.setBorder(BorderFactory.createTitledBorder("Shape"));
        attrPanel.setBorder(BorderFactory.createTitledBorder("Attributes"));

        nameLabel = new JLabel("Name  ");
        JLabel labelAttrLabel = new JLabel("Label");
        JLabel imgAttrLabel = new JLabel("Image");
        nameTextField = new JTextField();
        labelAttrTextField = new JTextField();
        imgFileChooser = new JFileChooser();
        selectedImgLabel = new JLabel("(None selected)");
        JButton chooseFile = new JButton("Choose File");
        chooseFile.addActionListener(new ChooseFileListener());
        JButton submit = new JButton("Create Shape");
        submit.addActionListener(new SubmitListener());

        shapeLayout.setAutoCreateGaps(true);
        shapeLayout.setAutoCreateContainerGaps(true);
        shapeLayout.setHorizontalGroup(
            shapeLayout.createSequentialGroup()
                .addComponent(nameLabel)
                .addComponent(nameTextField)
        );
        shapeLayout.setVerticalGroup(
            shapeLayout.createSequentialGroup()
                .addGroup(shapeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField))
        );

        attrLayout.setAutoCreateGaps(true);
        attrLayout.setAutoCreateContainerGaps(true);
        attrLayout.setHorizontalGroup(
            attrLayout.createSequentialGroup()
                .addGroup(attrLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(labelAttrLabel)
                    .addComponent(imgAttrLabel))
                .addGroup(attrLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(labelAttrTextField)
                    .addComponent(selectedImgLabel))
                .addComponent(chooseFile)
        );
        attrLayout.setVerticalGroup(
            attrLayout.createSequentialGroup()
                .addGroup(attrLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAttrLabel)
                    .addComponent(labelAttrTextField))
                .addGroup(attrLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(imgAttrLabel)
                    .addComponent(selectedImgLabel)
                    .addComponent(chooseFile))
        );

        mainLayout.setAutoCreateGaps(true);
        mainLayout.setAutoCreateContainerGaps(true);
        mainLayout.setHorizontalGroup(
            mainLayout.createSequentialGroup()
                .addGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(shapePanel)
                    .addComponent(attrPanel)
                    .addComponent(submit))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createSequentialGroup()
                .addComponent(shapePanel)
                .addComponent(attrPanel)
                .addComponent(submit)
        );

        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(parent);
        this.setSize(400, 260);
        this.setVisible(true);

        imgFile = null;
    }

    private void checkForm() {
        List<ShapeAttribute> attributes = new ArrayList<ShapeAttribute>(2);
        nameLabel.setForeground(Color.black);

        if (Validator.isEmpty(nameTextField.getText())) {
            nameLabel.setForeground(Color.red);
            return;
        }

        if (!Validator.isEmpty(labelAttrTextField.getText())) {
            attributes.add(new LabelAttribute(labelAttrTextField.getText()));
        }
        if (imgFile != null) {
            attributes.add(new ImageAttribute(imgFile.getAbsolutePath()));
        }

        createShape(nameTextField.getText(), "Custom category", attributes);
    }

    private void createShape(String name, String category, List<ShapeAttribute> attributes) {
        try {
            if (!controller.createNewShape(name, category, attributes)) {
                JOptionPane.showMessageDialog(this, name + " already exists", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else {


                svtc.getShapePaletteManager().getShapeCategoryContainer().addShape(name, category);



                this.dispose();
            }
        } catch (MMNException ex) {
            this.dispose();
            Exceptions.printStackTrace(ex);
        }
    }
}
