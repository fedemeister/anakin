package es.uca.gii.csi18.anakin.gui;

import es.uca.gii.csi18.anakin.data.Orientacion;

import javax.swing.*;
import java.util.List;

public class OrientacionListModel
        extends javax.swing.AbstractListModel<Orientacion> implements ComboBoxModel<Orientacion> {

    private List<Orientacion> _aData;
    Object _oSelectedItem = null;

    public OrientacionListModel (List<Orientacion> aData) {
        _aData = aData;
    }

    @Override
    public void setSelectedItem(Object anItem) {

    }

    @Override
    public Object getSelectedItem() {
        return null;
    }

    @Override
    public int getSize() {
        return _aData.size();
    }

    @Override
    public Orientacion getElementAt(int index) {
        return _aData.get(index);
    }
}
