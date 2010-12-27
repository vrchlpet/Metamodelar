
package org.cvut.MMN.view.core;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vrchli
 */
public class ConnectionTableModel extends AbstractTableModel{

    private Connection con;

    public ConnectionTableModel(Connection con) {
        this.con = con;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    public Connection getConnection() {
        return this.con;
    }



    @Override
    public int getRowCount() {
        return con.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return con.getItems().get(rowIndex).getSource().getName();
            case 1:
                return (Integer)(con.getItems().get(rowIndex).getSourceMul());
            case 2:
                return con.getItems().get(rowIndex).getTarget().getName();
            case 3:
                return (Integer)(con.getItems().get(rowIndex).getTargetMul());
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Source";
            case 1:
                return "SM";
            case 2:
                return "Target";
            case 3:
                return "TM";
        }
        return null;
    }

}
