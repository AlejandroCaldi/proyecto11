package es.santander.ascender.proyecto11;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GestionEstudiantes implements IGestionEstudiantes {

    private Map<String, Integer> padronAlumnos = new HashMap<>();
    private final String REGEX_NOMBRES_LATINOS = "^[\\p{L} .'-]+$"; //Regex para filtrar detectar nombres no latinizados. 
    private int notaMaxima = 100; // Para el caso de que cabie el sistema de puntuación
    private int notaMinima = 0; // Para el caso de que cabie el sistema de puntuación

        public GestionEstudiantes(Map<String, Integer> alumnosyNotas){
        
            padronAlumnos = alumnosyNotas;
        }

        public GestionEstudiantes(Map<String, Integer> alumnosyNotas, int notaMininaArg, int notaMaximaArg){
        
            padronAlumnos = alumnosyNotas;
            notaMaxima = notaMaximaArg;
            notaMinima = notaMininaArg;
        }
        

        public GestionEstudiantes() {
        
        }
    /**
     * @param nombre nombre del estidiante a agregar
     * @param calificacion int, se admite nulo en caso de que listar alumno sin una
     *                     nota por estar pendiente el exámen sea viable.
     * @nombreRegex String regex para chequear contra caracteres no admitibles en
     *              una castellanización de un nombre
     *              o bien contra el uso literal del mismo en castellano.
     * @return verdadero si pudo resgistrarlo, falso si ya està registrado.
     */
    @Override
    public boolean agregarEstudiante(String nombre, int calificacion) {

        boolean existe = this.existeEstudiante(nombre);
        boolean esNombre = this.controlarNombreRegex(nombre);
        boolean esNotaCorrecta = this.controlNota(calificacion);

        if (!existe && esNotaCorrecta) {

            if (esNombre) {

                padronAlumnos.put(nombre, calificacion);
                return true;
            }

            //El nombre no es válido en el lenguaje latino, incluso siendo extranjero
            //O la nota es negativa o mayor que 100.

        } 

        return false;
    };

    /**
     * @param nombre nombra del alumno a obtener la nota
     * @return la nota. El valor de la clave @nombre en el hash map @padronAlumnos
     * 
     */
    @Override
    public Integer obtenerCalificacion(String nombre) {

        boolean existe = this.existeEstudiante(nombre);
        Integer nota = notaMinima;

        if (existe) {

            nota = padronAlumnos.get(nombre);
            return nota;

        } else {

            //El alumno no está registrado.
            return null;
        }

    }

    /**
     * @return el mètodo devuelve el listado de alumnos con sus notas en su
     *         totalidad como un Hash_Map
     */
    @Override
    public Map<String, Integer> obtenerEstudiantesYCalificaciones() {

        Map<String, Integer> padronAlumnosRetornar = padronAlumnos;
        return padronAlumnosRetornar;

    }

    /**
     * @param nombre El nombre del estudiante, que es la clave en el Hash Map, que
     *               debe ser chequeado como existente.
     * @return true si existe, false si no.
     */
    @Override
    public boolean existeEstudiante(String nombre) {

        if (!padronAlumnos.containsKey(nombre)) {

            return false;

        } else {

            return true;
        }

    };

    /**
     * El método primero chequea si existe el nombre como clave. Si no existe
     * devuelve falso. Si existe
     * Lo borra del Hash Map. Tras ello vuelve a chequear, si ya no lo encuentra
     * devuelve true. Si no, false.
     * 
     * @param nombre el nombre del alumno, que es clave, para ser eliminado de la
     *               lista.
     * @return true si pudo borrarlo, false si no existe.
     * 
     */
    @Override
    public boolean eliminarEstudiante(String nombre) {

        boolean existe = this.existeEstudiante(nombre);

        if (existe) {

            padronAlumnos.remove(nombre);
            existe = this.existeEstudiante(nombre);
            if (!existe) {

                //Borrado existoso
                return true;
            } else {
                //El Borrado no pudo realizarse.
                return false;
            }

        } else {

           //El alumno no se encuentra registrado, operación no realizada.

            return false;

        }
    };

    /**
     * 
     * Entiendo que el objetivo del método, pudiendo tomar los valores de
     * nuevasCalificaciones. Independientemente
     * de si existen. De existir previamente, entiendo, debería crearse un método
     * donde solo se pasan las nuevas calificaciones.
     * 
     * @param nuevosEstudiantes    Lista de estudiantes nuevos a cargar.
     * @param nuevasCalificaciones Lista con las nuevas calificaciones para cada
     *                             estudiante.
     * @sonNombresEstudiantes boolean establecido para determinar si los al menos un
     *                        nombre no està latinizado.
     *                        Si al menos uno no lo está entonces su valor es false.
     * 
     * 
     */
    @Override
    public void agregarEstudiantes(Set<String> nuevosEstudiantes, Map<String, Integer> nuevasCalificaciones) {

        boolean igualesNombres = this.controlListadoNombres(nuevosEstudiantes, nuevasCalificaciones);
        boolean sonNombresEstudiantes = this.controlarNombreRegex(nuevosEstudiantes);
        boolean sonNotasCorrectas = this.controlNota(nuevasCalificaciones);

        if (!sonNombresEstudiantes || !igualesNombres || !sonNotasCorrectas) {

            // O los nombres no están latinizados o los nombres no coinciden completamente
            // entre una lista y la otra, o al menos una nota no es consistente.

        } else {

            if (nuevosEstudiantes.size() != nuevasCalificaciones.size()) {

                // Hay diferente cantidad de alumnos respecto de notas                

            } else {

                for (String alumno : nuevosEstudiantes) {

                    Integer nota = nuevasCalificaciones.get(alumno);

                    padronAlumnos.put(alumno, nota);

                }
            }
        }

    }

    /**
     * @param nombre Nombre a testear para detectar si cumple con el estandar de
     *               nombres en alfabeto latino
     *               o si fueron completamente latinizados.
     * @return true si el nombre está latinizado, false si no lo está.
     */
    public boolean controlarNombreRegex(String nombre) {

        if (nombre.matches(REGEX_NOMBRES_LATINOS)) {

            return true;
        }

        return false;
    }

    /**
     * @param nombres Array de String con ombres a testear para detectar si cumple
     *                con el estandar de nombres en alfabeto latino
     *                o si fueron completamente latinizados.
     * @return True si todos los nombres están correctamente latonizados. False si
     *         al menos un caso no lo está.
     */
    public boolean controlarNombreRegex(Map<String, Integer> nombresCalificados) {

        for (String nombre : nombresCalificados.keySet()) {

            if (!nombre.matches(REGEX_NOMBRES_LATINOS)) {
                
                return false;
            }

        }

        return true;
    }

    /**
     * @param nombres Array de String con ombres a testear para detectar si cumple
     *                con el estandar de nombres en alfabeto latino
     *                o si fueron completamente latinizados.
     * @return true si todos los nombres están correctamente latonizados. False si
     *         al menos un caso no lo está.
     */
    public boolean controlarNombreRegex(Set<String> nombres) {

        for (String nombre : nombres) {

            if (!nombre.matches(REGEX_NOMBRES_LATINOS)) {

                return false;
            }

        }

        return true;
    }

    /**
     * Controla que un listado tenga todas notas consistentes, >= notaMinima o <= noaMaxima.  
     * @param nota el listado de notas a evaluar. 
     * @return true si todas las notas son consistentes. false si una no lo es. 
     */
    public boolean controlNota(Map<String, Integer> listaConNotas) {

        Collection<Integer> notas = listaConNotas.values();
        
        if (Collections.min(notas)<notaMinima || Collections.max(notas)>notaMaxima) {

            return false;
        } else {

            return true;
        }
    }

    /**
     * Control de consistencia de nota para todo un listado. No puede ser menor que 0 ni mayor que 100
     * @param nota la nota a evaluar. 
     * @return true si es consitente, false si no lo es. 
     */
    public boolean controlNota(int nota) {

        if (nota>=notaMinima && nota <= notaMaxima) {

            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica que ambos listados contengan los mismos nombres. 
     * @param nuevosEstudiantes lista de los nuevos estudiantes.
     * @param nuevasCalificaciones lista de las calificaciones de los mismos estudiantes. 
     * @return true si coinciden, false si no coinciden. 
     */
    public boolean controlListadoNombres(Set<String> nuevosEstudiantes, Map<String, Integer> nuevasCalificaciones) {

        // Controlar si ambas listas de nombres son iguales contienen los mismos nombres
        // y son iguales.
        Set<String> nombresCalificados = nuevasCalificaciones.keySet();

        for (String calificado : nombresCalificados){

            if(!nuevosEstudiantes.contains(calificado)) {

                return false;
            }
        }
        return true;
    }

    /**
     * 
     * Método Sui Generis para listar en patalla todo el Hash Map a los efectos de
     * darle una visualización.
     * 
     */
    public void imprimirAlumnosNotas() {

        System.out.println("Alumno" + "\t:\t" + "Nota");
        System.out.println("------------------------");
        for (Map.Entry<String, Integer> entry : padronAlumnos.entrySet()) {
            String nombre = entry.getKey();
            Integer nota = entry.getValue();
            System.out.println(nombre + "\t:\t" + nota);
        }
    }

    // getters and setters:

    public int getNotaMaxima() {
        return notaMaxima;
    }

    public void setNotaMaxima(int notaMaxima) {
        this.notaMaxima = notaMaxima;
    }

    public int getNotaMinima() {
        return notaMinima;
    }

    public void setNotaMinima(int notaMinima) {
        this.notaMinima = notaMinima;
    }

}
