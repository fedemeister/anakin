package es.uca.gii.csi18.anakin.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uca.gii.csi18.anakin.data.Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

class DataTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Data.LoadDriver();
    }

    @Disabled
    @Test
    void testTableAccess() throws Exception {

        Connection con = null;
        ResultSet rs = null;

        try {
            con = Data.Connection();
            rs = con.createStatement().executeQuery("SELECT Codigo,Nombre,Plazas FROM actividad;");

            while (rs.next()) {
                System.out.println(rs.getInt("Codigo") +
                        " " + rs.getString("Nombre") + " " + rs.getInt("Plazas"));
            }
            assertEquals(5, con.createStatement().executeQuery("SELECT COUNT(Codigo) FROM actividad;"));
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }

    @Test
    void testString2Sql() throws Exception {

        assertEquals("'%hola%'", Data.String2Sql("hola", true, true));
        assertEquals("'hola'", Data.String2Sql("hola", true, false));
        assertEquals("%hola%", Data.String2Sql("hola", false, true));
        assertEquals("hola", Data.String2Sql("hola", false, false));
        assertEquals("O''Connell", Data.String2Sql("O'Connell", false, false));
        assertEquals("'O''Connell'", Data.String2Sql("O'Connell", true, false));
        assertEquals("%''Smith ''%", Data.String2Sql("'Smith '", false, true));
        assertEquals("'''Smith '''", Data.String2Sql("'Smith '", true, false));
        assertEquals("'%''Smith ''%'", Data.String2Sql("'Smith '", true, true));
    }

    @Test
    void testBoolean2Sql() throws Exception {

        assertEquals(1, Data.Boolean2Sql(true));
        assertEquals(0, Data.Boolean2Sql(false));
    }
}