package es.uca.gii.csi18.anakin.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import java.util.*;
import javax.swing.JOptionPane;

public class Actividad {
    private int _iCodigo, _iPlazas, _iIdOrientacion;
    private String _sNombre, _sMonitor;
    private boolean _bIsDeleted = false;
    private Orientacion _orientacion;

    /**
     * @param iCodigo
     */
    public Actividad(int iCodigo) throws Exception {
        _iCodigo = iCodigo;
        _orientacion = new Orientacion(_iIdOrientacion);
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Data.Connection();
            rs = con.createStatement().executeQuery("SELECT Nombre, Monitor, Plazas FROM actividad WHERE Codigo =" + iCodigo + ";");
            rs.next();
            _sNombre = rs.getString("Nombre");
            _sMonitor = rs.getString("Monitor");
            _iPlazas = rs.getInt("Plazas");
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }

    /**
     * @return Codigo de la actividad
     */
    public int getCodigo() {
        return _iCodigo;
    }

    /**
     * @return Nombre de la actividad
     */
    public String getNombre() {
        return _sNombre;
    }

    /**
     * @param sNombre Nombre de la actividad
     */
    public void setNombre(String sNombre) {
        _sNombre = sNombre;
    }

    /**
     * @return Nombre del Monitor
     */
    public String getMonitor() {
        return _sMonitor;
    }

    /**
     * @param sMonitor Nombre del Monitor
     */
    public void setMonitor(String sMonitor) {
        _sMonitor = sMonitor;
    }

    /**
     * @return Numero de plazas para la actividad
     */
    public int getPlazas() {
        return _iPlazas;
    }

    /**
     * @param iPlazas Numero de plazas para la actividad
     */
    public void setPlazas(int iPlazas) {
        _iPlazas = iPlazas;
    }

    /**
     * @return Si la actividad ha sido eliminada
     */
    public boolean getIsDeleted() {
        return _bIsDeleted;
    }

    public Orientacion getOrientacion() {
        return _orientacion;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return super.toString() + ":" + _iCodigo + ":" + _orientacion.toString() +
                ":" + _sNombre + ":" + _sMonitor + ":" + _iPlazas;
    }

    /**
     * @param sNombre
     * @param iPlazas
     * @param sMonitor
     * @return actividad
     * @throws Exception
     */
    public static Actividad Create(String sNombre, Integer iPlazas, String sMonitor, Orientacion orientacion)
            throws Exception {
        if (sNombre.isEmpty() || sNombre == null || sMonitor.isEmpty() || sMonitor == null || iPlazas == null)
            throw new Exception("Todos los campos son obligatorios.");
        Connection con = null;
        Statement stmt = null;
        sNombre = Data.String2Sql(sNombre, true, false);
        sMonitor = Data.String2Sql(sMonitor, true, false);
        try {
            con = Data.Connection();
            stmt = (Statement) con.createStatement();
            stmt.executeUpdate("INSERT INTO actividad (Nombre, Plazas, Monitor, _iIdOrientacion) " +
                    "VALUES (" + sNombre + "," + iPlazas + "," + sMonitor + "," + orientacion.getId() + ")");
            return new Actividad(Data.LastId(con));
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (stmt != null)
                stmt.close();
            if (con != null)
                con.close();
        }
    }

    /**
     * @throws Exception
     */
    public void Delete() throws Exception {
        Connection con = null;
        Statement stmt = null;
        if (!_bIsDeleted) {
            try {
                con = Data.Connection();
                stmt = (Statement) con.createStatement();
                stmt.executeUpdate("DELETE FROM actividad where Codigo = " + _iCodigo + ";");
                _bIsDeleted = true;
            } catch (SQLException ee) {
                throw ee;
            } finally {
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.close();
            }
        } else
            throw new Exception("Actividad ya eliminada.");
    }

    /**
     * @throws Exception
     */
    public void Update() throws Exception {
        Connection con = null;
        Statement stmt = null;
        _sNombre = Data.String2Sql(_sNombre, true, false);
        _sMonitor = Data.String2Sql(_sMonitor, true, false);
        if (!_bIsDeleted) {
            try {
                con = Data.Connection();
                stmt = (Statement) con.createStatement();
                stmt.executeUpdate("UPDATE actividad SET Nombre = " + _sNombre + ", Plazas = " + _iPlazas +
                        ", Monitor = " + _sMonitor + ", id_orientacion = " + _orientacion.getId() +
                        " WHERE Codigo = " + _iCodigo);
            } catch (SQLException ee) {
                throw ee;
            } finally {
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.close();
            }
        } else
            throw new Exception("Actividad eliminada.");
    }

    /**
     * @param sNombre
     * @param iPlazas
     * @param sMonitor
     * @return
     * @throws Exception
     */
    public static ArrayList<Actividad> Select(String sNombre, Integer iPlazas, String sMonitor, String sOrientacion)
            throws Exception {
        Connection con = null;
        ResultSet rs = null;
        ArrayList<Actividad> aActividad = new ArrayList<Actividad>();
        try {
            con = Data.Connection();
            rs = con.createStatement().executeQuery
                    ("SELECT Codigo, Nombre, Plazas, Monitor FROM Actividad INNER JOIN orientacion"
                            + Where(sNombre, iPlazas, sMonitor, sOrientacion));
            while (rs.next()) {
                aActividad.add(new Actividad(rs.getInt("Codigo")));
            }
            return aActividad;
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null)
                rs.close();
            if (con != null)
                con.close();
        }
    }

    /**
     * @param sNombre
     * @param iPlazas
     * @param sMonitor
     * @return
     * @throws Exception
     */
    private static String Where(String sNombre, Integer iPlazas, String sMonitor, String sOrientacion) throws Exception {
        String sQuery = "";

        if (sNombre != null) {
            sNombre = Data.String2Sql(sNombre, true, false);
            sQuery += "Actividad.Nombre LIKE " + sNombre + " and ";
        }
        if (iPlazas != null) sQuery += "Actividad.Plazas LIKE " + iPlazas + " and ";
        if (sMonitor != null) {
            sMonitor = Data.String2Sql(sMonitor, true, false);
            sQuery += "Actividad.Monitor LIKE " + sMonitor + " and ";
        }
        if (sOrientacion != null) {
            sOrientacion = Data.String2Sql(sOrientacion, true, false);
            sQuery += "actividad.id_orientacion = orientacion.id AND orientacion.nombre LIKE " + sOrientacion + " and ";
        }
        if (sQuery != "") {
            sQuery = sQuery.substring(0, sQuery.length() - 5);
            sQuery = "WHERE " + sQuery;
        }
        return sQuery;
    }
}