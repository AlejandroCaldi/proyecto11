package es.santander.ascender.proyecto11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GestionEstudiantesTest {
    
    private IGestionEstudiantes gestion;
    
    @BeforeEach
    void setUp() {
        gestion = new GestionEstudiantes();
    }
    
    @Test
    void testAgregarEstudiante() {
        assertTrue(gestion.agregarEstudiante("Juan", 85));
        assertFalse(gestion.agregarEstudiante("Juan", 90)); // No se puede agregar el mismo estudiante dos veces
    }

    @Test
    void testAgregarEstudiantes() {
        Set<String> nuevosEstudiantes = new HashSet<>();
        nuevosEstudiantes.add("Ana");
        nuevosEstudiantes.add("Luis");

        Map<String, Integer> nuevasCalificaciones = new HashMap<>();
        nuevasCalificaciones.put("Ana", 90);
        nuevasCalificaciones.put("Luis", 80);

        gestion.agregarEstudiantes(nuevosEstudiantes, nuevasCalificaciones);

        assertTrue(gestion.existeEstudiante("Ana"));
        assertTrue(gestion.existeEstudiante("Luis"));
        assertEquals(90, gestion.obtenerCalificacion("Ana"));
        assertEquals(80, gestion.obtenerCalificacion("Luis"));
    }

    @Test
    void testEliminarEstudiante() {
        gestion.agregarEstudiante("Carlos", 75);

        assertTrue(gestion.eliminarEstudiante("Carlos"));
        assertFalse(gestion.existeEstudiante("Carlos"));
    }

    @Test
    void testExisteEstudiante() {
        gestion.agregarEstudiante("Maria", 95);

        assertTrue(gestion.existeEstudiante("Maria"));
        assertFalse(gestion.existeEstudiante("Pedro"));
    }

    @Test
    void testObtenerCalificacion() {
        gestion.agregarEstudiante("Lucia", 88);

        assertEquals(88, gestion.obtenerCalificacion("Lucia"));
        assertNull(gestion.obtenerCalificacion("Pedro")); // Estudiante no existente
    }

    @Test
    void testObtenerEstudiantesYCalificaciones() {
        gestion.agregarEstudiante("Sofia", 92);
        gestion.agregarEstudiante("Miguel", 85);

        Map<String, Integer> estudiantesYCalificaciones = gestion.obtenerEstudiantesYCalificaciones();
        assertEquals(2, estudiantesYCalificaciones.size());
        assertEquals(92, estudiantesYCalificaciones.get("Sofia"));
        assertEquals(85, estudiantesYCalificaciones.get("Miguel"));
    }
}
