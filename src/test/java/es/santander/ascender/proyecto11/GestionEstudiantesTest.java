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

    // Testeo de nombres mal latiizados para un solo caso.
    @Test
    void testAgregarEstudianteMalNombre() {
        assertFalse(gestion.agregarEstudiante("Jua%n", 85));
    }

    // Testeo de calificación inconsistente para un solo caso.
    @Test
    void testAgregarEstudianteMalNota() {
        assertFalse(gestion.agregarEstudiante("Juan", 215));
    }


    // Test agregado para chequear nombres contra letras esperables en la
    // castellanización usual de nombres extranjeros. Estos
    // permitidos. No se permiten caracteres raros tales como @|\.
    @Test
    void testAgregarEstudianteRegexCorrecto() {

        String[] nombresCorrectos = { "John Doe", "Ángela Martín", "Renée O'Connor", "Jean-Luc Picard", "O'Conner",
                "Mário", "A", "João", "Chloé",
                "D'Artagnan", "Hélène", "Åsa", "Øyvind", "Ægir", "Çem", "Đorđe", "Šime", "Željko", "Þór", "Ðoković" };

        for (int i = 0; i <= nombresCorrectos.length - 1; i++) {

            assertTrue(gestion.agregarEstudiante(nombresCorrectos[i], 5));
        }

    }

    // Test agegado para chequear nombres contra letras esperables en la
    // castellanizaciòn usual de nombres extranjeros. Estos
    // permitidos. No se permiten caracteres raros tales como @|\.
    @Test
    void testAgregarEstudianteRegexFalso() {

        String[] nombresMal = { "123Juan", "Juan@email", "Jorge|pipa", "Ronaldo%porCienta", "Don?pregunton" };

        for (int j = 0; j <= nombresMal.length - 1; j++) {

            assertFalse(gestion.agregarEstudiante(nombresMal[j], 5));
        }

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


    // Test para probar tratamiento a listas de diferentes tamaños.
    @Test
    void testAgregarEstudiantesListasTrunctas() {
        Set<String> nuevosEstudiantes = new HashSet<>();
        nuevosEstudiantes.add("Ana");
        nuevosEstudiantes.add("Luis");

        Map<String, Integer> nuevasCalificaciones = new HashMap<>();
        nuevasCalificaciones.put("Ana", 90);
        nuevasCalificaciones.put("Luis", 80);
        nuevasCalificaciones.put("Wenceslao", 35);

        gestion.agregarEstudiantes(nuevosEstudiantes, nuevasCalificaciones);

        assertFalse(gestion.existeEstudiante("Ana"));
        assertFalse(gestion.existeEstudiante("Luis"));
        assertFalse(gestion.existeEstudiante("Wenceslao"));

    }

    // Testeo de carga de notas y alumnos con nombres mal latinizados 
    @Test
    void testAgregarEstudiantesMalNombres() {
        Set<String> nuevosEstudiantes = new HashSet<>();
        nuevosEstudiantes.add("An?");
        nuevosEstudiantes.add("Luis");

        Map<String, Integer> nuevasCalificaciones = new HashMap<>();
        nuevasCalificaciones.put("An?", 90);
        nuevasCalificaciones.put("Luis", 80);
        nuevasCalificaciones.put("Wenceslao", 35);

        gestion.agregarEstudiantes(nuevosEstudiantes, nuevasCalificaciones);

        assertFalse(gestion.existeEstudiante("An?"));
        assertFalse(gestion.existeEstudiante("Luis"));
        assertFalse(gestion.existeEstudiante("Wenceslao"));

    }

    // Test para chequear si en la carga por listas hay notas inconsistentes
    @Test
    void testAgregarEstudiantesMalNotas() {
        Set<String> nuevosEstudiantes = new HashSet<>();
        nuevosEstudiantes.add("Ana");
        nuevosEstudiantes.add("Luis");

        Map<String, Integer> nuevasCalificaciones = new HashMap<>();
        nuevasCalificaciones.put("Ana", 90);
        nuevasCalificaciones.put("Luis", -5);
        nuevasCalificaciones.put("Wenceslao", 35);

        gestion.agregarEstudiantes(nuevosEstudiantes, nuevasCalificaciones);

        assertFalse(gestion.existeEstudiante("Ana"));
        assertFalse(gestion.existeEstudiante("Luis"));
        assertFalse(gestion.existeEstudiante("Wenceslao"));

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
