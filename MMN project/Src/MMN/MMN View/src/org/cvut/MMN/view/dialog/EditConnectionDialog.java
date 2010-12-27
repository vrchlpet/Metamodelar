/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.cvut.MMN.controller.IController;
import org.cvut.MMN.model.Connection;
import org.cvut.MMN.model.attributes.ConnectionAttribute;
import org.cvut.MMN.model.attributes.ConnectionSourceShape;
import org.cvut.MMN.model.attributes.ConnectionTargetShape;

/**
 *
 * @author Vrchli
 */
public class EditConnectionDialog extends Dialog implements ItemListener, ActionListener {

    private IController controller;

    private ConList conList;
    private JLabel lbConListTitle;
    private JPanel jpConList;

    private JLabel lbConTarget;
    private JLabel lbConSource;
    private JLabel lbConTargetText;
    private JLabel lbConSourceText;

    private JPanel jpAttributes;
    private JPanel jpSource;
    private JPanel jpTarget;
    private JPanel jpSourceBt;
    private JPanel jpTargetBt;

    private JPanel jpBt;

    private JButton btRemoveSource;
    private JButton btSetSource;
    private JButton btRemoveTarget;
    private JButton btSetTarget;

    private JButton btOk;

    private final String NONE = "(not selected)";

    private Connection connection;

    private JFileChooser imgFileChooser;
    private File imgFile;

    public EditConnectionDialog(Frame frame, IController controller) {
        super(frame,"Edit connection dialog");
        this.controller = controller;

        initComponent();

        String [][] cons = controller.getConnectionGroups();
        for ( int i = 0; i < cons.length; i++) {
            conList.addItem(cons[i][0], cons[i][1]);
        }
    }

    private void initComponent() {

        conList = new ConList();
        lbConListTitle = new JLabel("Connections:");
        jpConList = new JPanel();
        lbConTarget = new JLabel("Target: ");
        lbConTargetText = new JLabel(NONE);
        lbConSource = new JLabel("Source: ");
        lbConSourceText = new JLabel(NONE);
        jpAttributes = new JPanel();
        jpSource = new JPanel();
        jpTarget = new JPanel();
        jpSourceBt = new JPanel();
        jpTargetBt = new JPanel();
        jpBt = new JPanel();
        btRemoveSource = new JButton("Remove");
        btSetSource = new JButton("Set");
        btRemoveTarget = new JButton("Remove");
        btSetTarget = new JButton("Set");
        btOk = new JButton("Ok");
        
        this.setLayout(new BorderLayout());

        jpConList.setLayout(new BorderLayout());
        jpConList.add(lbConListTitle, BorderLayout.NORTH);
        jpConList.add(conList);
        this.add(jpConList, BorderLayout.WEST);

        jpAttributes.setLayout( new GridLayout(2,1));

        jpSource.setLayout( new BorderLayout());
        jpSource.add(lbConSource, BorderLayout.WEST);
        jpSource.add(lbConSourceText, BorderLayout.CENTER);
        jpSource.add(jpSourceBt, BorderLayout.EAST);
        jpSourceBt.add(btRemoveSource);
        jpSourceBt.add(btSetSource);
        jpAttributes.add(jpSource);

        jpTarget.setLayout( new BorderLayout());
        jpTarget.add(lbConTarget, BorderLayout.WEST);
        jpTarget.add(lbConTargetText, BorderLayout.CENTER);
        jpTarget.add(jpTargetBt, BorderLayout.EAST);
        jpTargetBt.add(btRemoveTarget);
        jpTargetBt.add(btSetTarget);
        jpAttributes.add(jpTarget);

        this.add(jpAttributes, BorderLayout.CENTER);

        jpBt.setLayout( new BorderLayout());
        jpBt.add(btOk, BorderLayout.EAST);
        this.add(jpBt, BorderLayout.SOUTH);

        imgFileChooser = new JFileChooser();

        conList.addItemListener(this);
        btOk.addActionListener(this);
        btRemoveSource.addActionListener(this);
        btRemoveTarget.addActionListener(this);
        btSetSource.addActionListener( new ChooseSourceFileListener());
        btSetTarget.addActionListener( new ChooseTargetFileListener());


        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EditConnectionDialog.this.dispose();
            }
        });

        this.pack();
        this.setLocation(300,300);
        this.setVisible(true);

        connection = null;
        imgFile = null;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        ConItem conx = conList.getSelectedCon();
        this.connection = controller.getMetamodel().getConnectionManager().getConnection(conx.getConName());
        lbConSourceText.setText(NONE);
        lbConTargetText.setText(NONE);

        for ( ConnectionAttribute ca : this.connection.getAttributes()) {
            if ( ca instanceof ConnectionSourceShape) {
                lbConSourceText.setText( ((ConnectionSourceShape)ca).getPath() );
            } else if (ca instanceof ConnectionTargetShape) {
                lbConTargetText.setText( ((ConnectionTargetShape)ca).getPath() );
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == btRemoveSource) {
            if ( connection != null) {
                for ( ConnectionAttribute ca : connection.getAttributes()) {
                    if ( ca instanceof ConnectionSourceShape) {
                        connection.getAttributes().remove(ca);
                        lbConSourceText.setText(NONE);
                        JOptionPane.showMessageDialog(this, "Image has been removed.");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "There is no image!");
            } else {
                JOptionPane.showMessageDialog(this, "Select connection first!");
            }
        } else if ( e.getSource() == btRemoveTarget) {
            if ( connection != null) {
                for ( ConnectionAttribute ca : connection.getAttributes()) {
                    if ( ca instanceof ConnectionTargetShape) {
                        connection.getAttributes().remove(ca);
                        lbConTargetText.setText(NONE);
                        JOptionPane.showMessageDialog(this, "Image has been removed.");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "There is no image!");
            } else {
                JOptionPane.showMessageDialog(this, "Select connection first!");
            }
        } else if (e.getSource() == btOk) {
            this.dispose();
        }
    }

    private class ChooseSourceFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ( connection != null) {
                int result = EditConnectionDialog.this.imgFileChooser.showOpenDialog(EditConnectionDialog.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    EditConnectionDialog.this.imgFile = EditConnectionDialog.this.imgFileChooser.getSelectedFile();
                    if ( imgFile.isFile()) {
                        EditConnectionDialog.this.lbConSourceText.setText(EditConnectionDialog.this.imgFile.getAbsolutePath());
                        x:for ( ConnectionAttribute ca : connection.getAttributes()) {
                            if ( ca instanceof ConnectionSourceShape) {
                                connection.getAttributes().remove(ca);
                                break x;
                            }
                        }
                        connection.getAttributes().add(new ConnectionSourceShape(lbConSourceText.getText()));
                        JOptionPane.showMessageDialog(EditConnectionDialog.this, "Image has been set.");
                        return;
                    } else {
                        JOptionPane.showMessageDialog(EditConnectionDialog.this, "Wrong fiel!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(EditConnectionDialog.this, "Select connection first!");
            }
        }
    }

    private class ChooseTargetFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ( connection != null) {
                int result = EditConnectionDialog.this.imgFileChooser.showOpenDialog(EditConnectionDialog.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    EditConnectionDialog.this.imgFile = EditConnectionDialog.this.imgFileChooser.getSelectedFile();
                    if ( imgFile.isFile()) {
                        EditConnectionDialog.this.lbConTargetText.setText(EditConnectionDialog.this.imgFile.getAbsolutePath());
                        x:for ( ConnectionAttribute ca : connection.getAttributes()) {
                            if ( ca instanceof ConnectionTargetShape) {
                                connection.getAttributes().remove(ca);
                                break x;
                            }
                        }
                        connection.getAttributes().add(new ConnectionTargetShape(lbConTargetText.getText()));
                        JOptionPane.showMessageDialog(EditConnectionDialog.this, "Image has been set.");
                        return;
                    } else {
                        JOptionPane.showMessageDialog(EditConnectionDialog.this, "Wrong fiel!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(EditConnectionDialog.this, "Select connection first!");
            }
        }

    }

}