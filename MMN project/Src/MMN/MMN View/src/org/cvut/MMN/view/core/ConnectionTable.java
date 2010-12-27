

package org.cvut.MMN.view.core;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Vrchli
 */
public class ConnectionTable extends JTable {

    private ConnectionTableModel ctm = null;
    private ArrayList<ConTableObserver> observers;

    public ConnectionTable(ConnectionTableModel ctm) {
        this.ctm = ctm;
        setModel(this.ctm);

        observers = new ArrayList<ConTableObserver>();

        getSelectionModel().addListSelectionListener(new ListSelectionListener() {


            @Override
            public void valueChanged(ListSelectionEvent e) {
                int rowIndex = getSelectedRow();
                if ( rowIndex != -1) {
                    String source = (String)(ConnectionTable.this.ctm.getValueAt(getSelectedRow(), 0));
                    String target = (String)(ConnectionTable.this.ctm.getValueAt(getSelectedRow(), 2));
                    for ( ConTableObserver cto : observers) {
                        cto.rowSelected(source, target);
                    }
                }
            }
        });
    }


    public void addObserver(ConTableObserver cto) {
        observers.add(cto);
    }

    public void removeObserver(ConTableObserver cto) {
        observers.remove(cto);
    }



    /**
     * @return the ctm
     */
    public ConnectionTableModel getCtm() {
        return ctm;
    }

}
