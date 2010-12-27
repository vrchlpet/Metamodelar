

package org.cvut.MMN.view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.cvut.MMN.controller.IController;
import org.cvut.MMN.model.MMNException;
import org.cvut.MMN.view.core.ConWidget;
import org.cvut.MMN.view.core.ConnectionItem;
import org.cvut.MMN.view.core.Shape;

/**
 *
 * @author Vrchli
 */
public class EditDialog extends JDialog implements ActionListener{

    private ConWidget con;

    private JPanel ioPanel;
    private JLabel tl;
    private JLabel sl;
    private JLabel tml;
    private JLabel sml;
    private JTextField tmf;
    private JTextField smf;
    private JPanel btPanel;
    private JButton btOk;

    private JComboBox sourceList;
    private JComboBox targetList;

    private IController controller;

    public EditDialog(ConWidget con, IController controller) {
        this.con = con;
        this.controller = controller;
        initDialog();
    }

    private void initDialog() {
        ioPanel = new JPanel();
        tl = new JLabel("Target shape: ");
        sl = new JLabel("Source shape: ");
        tml = new JLabel("Target multiplicity: ");
        sml = new JLabel("Source multiplicity: ");
        tmf = new JTextField("1", 10);
        smf = new JTextField("2", 10);
        btPanel = new JPanel();
        btOk = new JButton("OK");


        sourceList = new JComboBox(controller.getShapes().toArray());
        

        targetList = new JComboBox(controller.getShapes().toArray());
        

        ioPanel.setLayout(new GridLayout(4,2));
        setLayout(new BorderLayout());
        ioPanel.add(sl);
        ioPanel.add(sourceList);
        ioPanel.add(sml);
        ioPanel.add(smf);
        ioPanel.add(tl);
        ioPanel.add(targetList);
        ioPanel.add(tml);
        ioPanel.add(tmf);
        add(ioPanel, BorderLayout.CENTER);
        btPanel.setLayout(new FlowLayout());
        btPanel.add(btOk);
        add(btPanel, BorderLayout.SOUTH);
        btOk.addActionListener(this);

        pack();
        setTitle("Add Item");
        setLocation(200,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Shape source = new Shape((String)(sourceList.getSelectedItem()));
        Shape target = new Shape((String)(targetList.getSelectedItem()));
        int sourceM = Integer.parseInt(smf.getText());
        int targetM = Integer.parseInt(tmf.getText());

        

        
        try {
            controller.createConnection(con.getCon().getName(), source.getName(), sourceM, target.getName(), targetM);
            con.getConTable().getCtm().getConnection()
                .addItem(new ConnectionItem(source, target, targetM, sourceM));
            con.getConTable().getCtm().fireTableDataChanged();
        } catch (MMNException ex) {
            new JOptionPane().showMessageDialog(null, "This combination already exists!");
            return;
        }
        
        dispose();
    }

    
}
