package es.uca.gii.csi18.anakin.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.uca.gii.csi18.anakin.data.Orientacion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Connection;

import es.uca.gii.csi18.anakin.data.Actividad;
import es.uca.gii.csi18.anakin.data.Data;

class ActividadTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Data.LoadDriver();
    }

    @Test
    void testConstructor() throws Exception {
        Actividad actividad = new Actividad(1);
        assertEquals(1, actividad.getCodigo());
        assertEquals("Petanca", actividad.getNombre());
        assertEquals("Yoda", actividad.getMonitor());
        assertEquals(10, actividad.getPlazas());
    }

    @Test
    void testCreate() throws Exception {
        Actividad actividad = Actividad.Create("Batuka", 5, "Dooku", new Orientacion(1));
        assertEquals("Batuka", actividad.getNombre());
        assertEquals("Dooku", actividad.getMonitor());
        assertEquals(5, actividad.getPlazas());
        assertEquals(1,actividad.getOrientacion().getId());
    }

    @Test
    void testSelect() throws Exception {
        Actividad actividad = new Actividad(1);
        ArrayList<Actividad> aActividad = new ArrayList<Actividad>();

        aActividad = actividad.Select(null, null, null, null);
        actividad = aActividad.get(0);
        assertEquals(1, actividad.getCodigo());
        assertEquals("Petanca", actividad.getNombre());
        assertEquals("Yoda", actividad.getMonitor());
        assertEquals(10, actividad.getPlazas());

        actividad = aActividad.get(1);
        assertEquals(2, actividad.getCodigo());
        assertEquals("Meditacion", actividad.getNombre());
        assertEquals("Windu", actividad.getMonitor());
        assertEquals(10, actividad.getPlazas());

        actividad = aActividad.get(2);
        assertEquals(3, actividad.getCodigo());
        assertEquals("Tiro con arco", actividad.getNombre());
        assertEquals("Yoda", actividad.getMonitor());
        assertEquals(22, actividad.getPlazas());

        aActividad = actividad.Select(null, 22, null, null);
        actividad = aActividad.get(0);
        assertEquals(3, actividad.getCodigo());
        assertEquals("Tiro con arco", actividad.getNombre());
        assertEquals("Yoda", actividad.getMonitor());
        assertEquals(22, actividad.getPlazas());

        aActividad = actividad.Select(null, null, "Windu", null);
        actividad = aActividad.get(0);
        assertEquals(2, actividad.getCodigo());
        assertEquals("Meditacion", actividad.getNombre());
        assertEquals("Windu", actividad.getMonitor());
        assertEquals(10, actividad.getPlazas());

    }

    @Test
    void testUpdate() throws Exception {

        Actividad actividad = new Actividad(2);
        Connection con = null;
        ResultSet rs = null;
        actividad.setNombre("Meditacion");
        actividad.setMonitor("Windu");
        actividad.setPlazas(10);
        try {
            actividad.Update();
            con = (Connection) Data.Connection();
            rs = con.createStatement().executeQuery("SELECT Nombre, Plazas, Monitor FROM Actividad where Codigo ="
                    + actividad.getCodigo());
            rs.next();

            assertEquals("Meditacion", rs.getString("Nombre"));
            assertEquals("Windu", rs.getString("Monitor"));
            assertEquals(10, rs.getInt("Plazas"));

        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null)
                rs.close();
            if (con != null)
                con.close();
        }

    }

    @Test
    void testDelete() throws Exception {

        Connection con = null;
        ResultSet rs = null;
        try {
            con = (Connection) Data.Connection();
            int iIdActividadEliminar = Data.LastId(con);
            Actividad actividad = new Actividad(iIdActividadEliminar);
            actividad.Delete();
            rs = con.createStatement().executeQuery("SELECT Codigo FROM actividad WHERE Codigo = " + iIdActividadEliminar + ";");

            assertEquals(false, rs.next());
            assertEquals(true, actividad.getIsDeleted());
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null)
                rs.close();
            if (con != null)
                con.close();
        }
    }
}

