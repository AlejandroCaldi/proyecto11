package es.santander.ascender.proyecto11;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileProcesor implements IFileProcesor {

    /**
     * Metodo para convertir un archivo a una variable String. El archivo no puede
     * ser binario.
     * 
     * @param filePath Archivo que se pretende leer.
     * @return contenido de archivo no binario como String.
     * 
     */
    @Override
    public String leerFile(String filePath) throws Exception {

        // Agregado para poder tomar más fácil el nombre del archivo. Asume que el
        // usuario siempre va a tomar archivos desde el subdirectorio del proyecto. Esta
        // presunción puede ser
        // inadecuada para el proyecto desplegado en producción, pero a los efectos de
        // ejercicio me pareció
        // adecuado.
        Path path = Paths.get(System.getProperty("user.dir"), filePath);

        byte[] bytes = Files.readAllBytes(path);
        String content = new String(bytes);

        return content;
    };

    /**
     * Mètodo para reemplazar todas las vocales de un valor String. Toma todas las
     * volacales del castellano
     * en miúsculas y mayúsculas, acentuadas o no. Esto podrìa desharcodeearse aun
     * más, pero a los efectos del ejercicio
     * deberìa esar bien.
     * 
     * @param input el String al que se le quiere sacar las vocales.
     * @vocalesRegex la expresión regular que se desea aplicar al Sring original
     * @return una String sin vocales.
     */
    @Override
    public String eliminarVocales(String input) {

        String vocalesRegex = "[aeiouAEIOUÁÉÍÓÚáéíóú]";

        String consonantes = "";

        // Decidì usar replaceAll para hacer un solo proceso pasandole una expresión
        // regular y evitar pases por loops de
        // array con todas las permutaciones posibles de vocales.
        consonantes = input.replaceAll(vocalesRegex, "");

        return consonantes;
    };

    /**
     * Método para escribir texto plano a archivo. No se limitan direcciones de
     * disco para grabar, de lo contrario
     * el test contra la carpeta /tmp/ no es permitido.
     * 
     * @param filePath         es la direcciòn, en un String, donde deseamos grabar
     *                         el fichero resultante.
     * @param cadenaCaracteres El String que queremos grabar en fichero.
     */
    @Override
    public void escribirAFile(String filePath, String cadenaCaracteres) throws Exception {

        Path path = Paths.get(filePath);

        if (Files.exists(path)) {
            Files.delete(path);
        }

        Files.write(path, cadenaCaracteres.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);

        System.out.println("Proceso de grabación de fichero " + filePath + " con el texto comenzando con '"
                + cadenaCaracteres.substring(0, 15) + "'..");

    };
}
