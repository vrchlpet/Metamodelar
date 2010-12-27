

package org.cvut.MMN.view.core;


import org.cvut.MMN.view.dialog.EditDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.DimensionUIResource;
import org.cvut.MMN.controller.IController;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.border.Border;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.layout.Layout;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.widget.ComponentWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author Vrchli
 */
public class ConWidget extends Widget implements ActionListener {

    private IController controller;

    private MiniScene miniScene;
    private JScrollPane sp;
    private ComponentWidget cp;

    private GraphScene gs;

    private JPanel titlePanel;
    private ComponentWidget titleWidget;
    private JButton btClose;
    private JLabel titleLabel;

    private Layout layout;
    private LabelWidget label;
    private JScrollPane pane;
    private ComponentWidget compW;
    private ConnectionTable conTable;
    private Connection con;
    private Border border;
    private WidgetAction moveAction;

    private JButton btAdd;
    private JButton btRemove;
    private JPanel btPanel;
    private ComponentWidget compW2;

    public ConWidget(GraphScene scene, Connection con, IController controller) {
        super(scene);
        gs = scene;
        this.controller = controller;
        this.miniScene = new MiniScene(this);
        sp = new JScrollPane(miniScene.getScene().createView());
        sp.setPreferredSize(new DimensionUIResource(250, 150));
        cp = new ComponentWidget(getScene(), sp);


        this.con = con;
        layout = LayoutFactory.createVerticalFlowLayout();
        label = new LabelWidget(getScene(), this.getCon().getName());
        conTable = new ConnectionTable(new ConnectionTableModel(con));
        pane = new JScrollPane(conTable);

        pane.setPreferredSize(new Dimension(250, 150));
        compW = new ComponentWidget(getScene(), pane);
        border = BorderFactory.createLineBorder();
        moveAction = ActionFactory.createMoveAction();

        conTable.addObserver(miniScene);

        btAdd = new JButton("add");
        btRemove = new JButton("remove");
        btPanel = new JPanel();
        btPanel.add(btAdd);
        btPanel.add(btRemove);
        compW2 = new ComponentWidget(getScene(), btPanel);
        btAdd.addActionListener(this);
        btRemove.addActionListener(this);

        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titleLabel = new JLabel(con.getName());
        btClose = new JButton("X");
        btClose.addActionListener(this);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(btClose, BorderLayout.EAST);
        titleWidget = new ComponentWidget(getScene(), titlePanel);

        getActions().addAction(moveAction);
        
        setLayout(layout);
        setBorder(border);
        addChild(titleWidget);
        addChild(cp);
        addChild(compW);
        addChild(compW2);
    }

    public IController getController() {
        return this.controller;
    }

    public String getConName() {
        return this.con.getName();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == btAdd) {
            EditDialog editDialog = new EditDialog(this,controller);
        } else if (e.getSource() == btClose) {
            gs.removeNode(getCon().getName());
            gs.validate();
        } else {
            int index = getConTable().getSelectedRow();
            if ( index != -1) {
                ConnectionItem ci = conTable.getCtm().getConnection().getItems().get(index);
                controller.removeConnectionRull(con.getName()
                        , ci.getSource().getName(), ci.getTarget().getName());
                
                conTable.getCtm().getConnection().removeItem(ci);
               conTable.getCtm().fireTableDataChanged();
               miniScene.clear();
            }
        }
        
    }

    public MiniScene getMiniScene() {
        return this.miniScene;
    }

    /**
     * @return the conTable
     */
    public ConnectionTable getConTable() {
        return conTable;
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }
}
