package es.uca.gii.csi18.anakin.test;

import es.uca.gii.csi18.anakin.data.Data;
import es.uca.gii.csi18.anakin.data.Orientacion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrientacionTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Data.LoadDriver();
    }

    @Test
    void testConstructor() throws Exception {
        Orientacion orientacion = new Orientacion(1);
        assertEquals(1, orientacion.getId());
        assertEquals("Jedi", orientacion.getNombre());
    }

    @Test
    void testSelect() throws Exception {
        Orientacion orientacion = new Orientacion(1);
        ArrayList<Orientacion> aOrientacion = new ArrayList<Orientacion>();

        aOrientacion = orientacion.Select();
        orientacion = aOrientacion.get(0);
        assertEquals(1, orientacion.getId());
        assertEquals("Jedi", orientacion.getNombre());

        orientacion = aOrientacion.get(1);
        assertEquals(2, orientacion.getId());
        assertEquals("Sith", orientacion.getNombre());

    }

}

