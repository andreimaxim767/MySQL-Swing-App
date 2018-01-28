/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicatie;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Andrei
 */
public class RSTM extends AbstractTableModel {

    private ResultSet r;

    public RSTM(ResultSet r) {
        this.r = r;
    }

    @Override
    public int getRowCount() {
        try {
            r.last();
            return r.getRow();
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        try {
            //        return 4;
            ResultSetMetaData rsmd = r.getMetaData();
            return rsmd.getColumnCount() - 1;
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public Object getValueAt(int linie, int coloana) {
        try {
            r.absolute(linie + 1);
            return r.getString(coloana + 2);
        } catch (SQLException ex) {
            return "ERROR";
        }
    }

    @Override
    public String getColumnName(int column) {
        try {
            ResultSetMetaData rsmd = r.getMetaData();
            return rsmd.getColumnName(column + 2);
        } catch (SQLException ex) {
            return "ERROR";
        }
    }

    @Override
    public boolean isCellEditable(int linie, int coloana) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int linie, int coloana) {
        try {

            r.absolute(linie + 1);
            switch (coloana) {
                case 0:
                    r.updateString(coloana + 2, aValue.toString());
                    break;
                case 1:
                case 3:
                    r.updateInt(coloana + 2, Integer.parseInt(aValue.toString()));
                    break;
                case 2:
                    r.updateDouble(coloana + 2, Double.parseDouble(aValue.toString()));
                    break;
            }
            r.updateRow();
        } catch (Exception e) {
            return;
        }
        
    }

}
