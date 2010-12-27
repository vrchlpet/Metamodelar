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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.cvut.MMN.controller.IController;
import org.cvut.MMN.model.attributes.ConnectionAttribute;
import org.cvut.MMN.model.MMNException;
import org.cvut.MMN.model.attributes.ConnectionSourceShape;
import org.cvut.MMN.model.attributes.ConnectionTargetShape;
import org.cvut.MMN.view.core.ConViewTopComponent;
import org.cvut.MMN.view.palette.ConnectionNode;
import org.cvut.MMN.view.palette.PaletteConnection;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author matej
 */
public class CreateConnectionDialog extends JDialog {

    private class ChooseSourceFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int result = CreateConnectionDialog.this.sourceImgFileChooser.showOpenDialog(CreateConnectionDialog.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                CreateConnectionDialog.this.sourceImgFile = CreateConnectionDialog.this.sourceImgFileChooser.getSelectedFile();
                CreateConnectionDialog.this.selectedSourceImgLabel.setText(CreateConnectionDialog.this.sourceImgFile.getName());
            }
        }
    }

    private class ChooseTargetFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int result = CreateConnectionDialog.this.targetImgFileChooser.showOpenDialog(CreateConnectionDialog.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                CreateConnectionDialog.this.targetImgFile = CreateConnectionDialog.this.targetImgFileChooser.getSelectedFile();
                CreateConnectionDialog.this.selectedTargetImgLabel.setText(CreateConnectionDialog.this.targetImgFile.getName());
            }
        }
    }

    private class SubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CreateConnectionDialog.this.checkForm();
        }
    }

    // GUI components
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JFileChooser sourceImgFileChooser;
    private JLabel selectedSourceImgLabel;
    private JFileChooser targetImgFileChooser;
    private JLabel selectedTargetImgLabel;
    // parent window
    Frame parent;
    // selected img files
    private File sourceImgFile;
    private File targetImgFile;
    // controller
    private IController controller;

    private ConViewTopComponent cvtc;

    public CreateConnectionDialog(Frame parent, ConViewTopComponent cvtc) {
        super(parent, "New Connection", true);
        this.controller =cvtc.getController();
        this.parent = parent;
        this.cvtc = cvtc;
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

        shapePanel.setBorder(BorderFactory.createTitledBorder("Connection"));
        attrPanel.setBorder(BorderFactory.createTitledBorder("Attributes"));

        nameLabel = new JLabel("Name             ");
        JLabel connSourceShapeLabel = new JLabel("Source shape");
        JLabel connTargetShapeLabel = new JLabel("Target shape");
        nameTextField = new JTextField();
        sourceImgFileChooser = new JFileChooser();
        selectedSourceImgLabel = new JLabel("(None Selected)");
        JButton chooseSourceFile = new JButton("Source image");
        chooseSourceFile.addActionListener(new ChooseSourceFileListener());
        targetImgFileChooser = new JFileChooser();
        selectedTargetImgLabel = new JLabel("(None selected)");
        JButton chooseTargetFile = new JButton("Target image");
        chooseTargetFile.addActionListener(new ChooseTargetFileListener());
        JButton submit = new JButton("Create Connection");
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
                    .addComponent(connSourceShapeLabel)
                    .addComponent(connTargetShapeLabel))
                .addGroup(attrLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(selectedSourceImgLabel)
                    .addComponent(selectedTargetImgLabel))
                .addGroup(attrLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(chooseSourceFile)
                    .addComponent(chooseTargetFile))
        );
        attrLayout.setVerticalGroup(
            attrLayout.createSequentialGroup()
                .addGroup(attrLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(connSourceShapeLabel)
                    .addComponent(selectedSourceImgLabel)
                    .addComponent(chooseSourceFile))
                .addGroup(attrLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(connTargetShapeLabel)
                    .addComponent(selectedTargetImgLabel)
                    .addComponent(chooseTargetFile))
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
        this.setSize(400, 265);
        this.setVisible(true);

        sourceImgFile = null;
        targetImgFile = null;
    }

    private void checkForm() {
        List<ConnectionAttribute> attributes = new ArrayList<ConnectionAttribute>(2);
        nameLabel.setForeground(Color.black);

        if (Validator.isEmpty(nameTextField.getText())) {
            nameLabel.setForeground(Color.red);
            return;
        }

        if (sourceImgFile != null) {
            attributes.add(new ConnectionSourceShape(sourceImgFile.getAbsolutePath()));
        }
        if (targetImgFile != null) {
            attributes.add(new ConnectionTargetShape(targetImgFile.getAbsolutePath()));
        }

        createConnection(nameTextField.getText(), "Custom category", attributes);
    }

    private void createConnection(String name, String category, List<ConnectionAttribute> attributes) {
        try {
            if (!controller.createNewConnection(name, category, attributes)) {
                JOptionPane.showMessageDialog(this, name + " already exists", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else {

                cvtc.getConPaletteManager().getConCategoryContainer().addConnection(name, category);


                this.dispose();
            }
        } catch (MMNException ex) {
            this.dispose();
            Exceptions.printStackTrace(ex);
        }
    }
}
