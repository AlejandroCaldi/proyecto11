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
        String sinVocales = procesosArchivo.eliminarVocales(contenido);
        System.out.println(sinVocales);

    }
}
