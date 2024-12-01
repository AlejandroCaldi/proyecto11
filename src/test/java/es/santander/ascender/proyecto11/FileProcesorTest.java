package es.santander.ascender.proyecto11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileProcesorTest {

    private IFileProcesor procesor;

    @BeforeEach
    void setUp() {
        procesor = new FileProcesor();
    }

    @Test
    public void testLeerFile() throws Exception {
        String testFilePath = "src/test/resources/test.txt";
        String expectedContent = "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lantejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda. El resto della concluían sayo de velarte, calzas de velludo para las fiestas, con sus pantuflos de lo mesmo, y los días de entresemana se honraba con su vellorí de lo más fino. Tenía en su casa una ama que pasaba de los cuarenta, y una sobrina que no llegaba a los veinte, y un mozo de campo y plaza, que así ensillaba el rocín como tomaba la podadera. Frisaba la edad de nuestro hidalgo con los cincuenta años; era de complexión recia, seco de carnes, enjuto de rostro, gran madrugador y amigo de la caza. Quieren decir que tenía el sobrenombre de Quijada, o Quesada, que en esto hay alguna diferencia en los autores que deste caso escriben; aunque por conjeturas verosímiles se deja entender que se llamaba Quijana. Pero esto importa poco a nuestro cuento: basta que en la narración dél no se salga un punto de la verdad.";

        // Leer el contenido del archivo de prueba
        String fileContent = procesor.leerFile(testFilePath);

        assertEquals(expectedContent, fileContent);
    }

    @Test
    public void testEscribirAFile() throws Exception {
        String content = "Contenido de prueba";

        // Crear archivo temporal de prueba
        Path tempFile = Files.createTempFile("testEscribir", ".txt");

        // Escribir contenido en el archivo temporal
        procesor.escribirAFile(tempFile.toString(), content);

        // Leer el contenido del archivo temporal para verificar
        String fileContent = Files.readString(tempFile);

        assertEquals(content, fileContent);

        // Eliminar archivo temporal después de la prueba
        Files.delete(tempFile);
    }

    @Test
    public void testEliminarVocales() {
        String input = "Hola Mundo";
        String expected = "Hl Mnd";  // Sin vocales

        String result = procesor.eliminarVocales(input);

        assertEquals(expected, result, result);
    }
}