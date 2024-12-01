package es.santander.ascender.proyecto11;

public class App {


    public static void main(String[] args) {
        FileProcesor procesosArchivo = new FileProcesor();
        String contenido = "";

        try {
            contenido = procesosArchivo.leerFile("/src/test/resources/test.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        procesosArchivo.eliminarVocales(contenido);

        try {
            procesosArchivo.escribirAFile("/tmp/pruebaEscritura.txt", "La última palabra del Quijote es 'vale'.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        GestionEstudiantes alumnado = new GestionEstudiantes();

        alumnado.agregarEstudiante("Ernesto", 10);
        alumnado.agregarEstudiante("Ernesto", 5);
    }
}
