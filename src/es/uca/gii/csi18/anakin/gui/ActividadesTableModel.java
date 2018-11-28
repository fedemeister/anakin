package es.uca.gii.csi18.anakin.gui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import es.uca.gii.csi18.anakin.data.Actividad;

public class ActividadesTableModel extends AbstractTableModel {
    private ArrayList<Actividad> _aData;

    public ActividadesTableModel(ArrayList<Actividad> aData) {
        _aData = aData;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return _aData.size();
    }

    @Override
    public Object getValueAt(int iRow, int iCol) {
        Actividad actividad = _aData.get(iRow);
        switch (iCol) {
            case 0:
                return actividad.getNombre();
            case 1:
                return actividad.getMonitor();
            case 2:
                return actividad.getPlazas();
        }
        throw new IllegalStateException("Columna incorrecta");
    }

    public Actividad getData(int iRow) {
        return _aData.get(iRow);
    }
}
