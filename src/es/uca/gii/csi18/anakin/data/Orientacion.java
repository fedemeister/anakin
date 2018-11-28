package es.uca.gii.csi18.anakin.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Orientacion {
    private int _iId;
    private String _sNombre;

    public Orientacion(int iId) throws Exception {
        _iId = iId;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Data.Connection();
            rs = con.createStatement().executeQuery("SELECT nombre FROM orientacion WHERE id =" + iId + ";");
            rs.next();
            _sNombre = rs.getString("nombre");
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }

    public int getId() {
        return _iId;
    }


    public String getNombre() {
        return _sNombre;
    }

    public void set_nombre(String sNombre) {
        sNombre = _sNombre;
    }


    public static ArrayList<Orientacion> Select() throws Exception {
        Connection con = null;
        ResultSet rs = null;
        ArrayList<Orientacion> aOrientacion = new ArrayList<Orientacion>();
        try {
            con = Data.Connection();
            rs = con.createStatement().executeQuery("SELECT id, nombre FROM orientacion ORDER BY nombre;");
            while (rs.next()) {
                aOrientacion.add(new Orientacion(rs.getInt("id")));
            }
            return aOrientacion;
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null)
                rs.close();
            if (con != null)
                con.close();
        }
    }

    public String toString() {
        return super.toString() + ":" + _iId + ":" + _sNombre;
    }
}
