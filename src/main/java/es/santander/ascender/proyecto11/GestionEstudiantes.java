package es.santander.ascender.proyecto11;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GestionEstudiantes implements IGestionEstudiantes{

         Map<String, Integer> padronAlumnos = new HashMap<>();

        /**
         * @param nombre
         * @param calificacion int, se admite nulo en caso de que listar alumno sin una nota por estar pendiente el exámen sea viable. 
         * @return verdadero si pudo resgistrarlo, falso si ya està registrado. 
         */
        @Override
        public boolean agregarEstudiante(String nombre, int calificacion) {
            
            boolean existe = this.existeEstudiante(nombre);

            if (!existe){

                padronAlumnos.put(nombre, calificacion);
                System.out.println("Alumno Agregado: " + nombre);
                System.out.println("Nota " + padronAlumnos.get(nombre));   
                
                return true;

            } else {

                System.out.println("El Alumno " + nombre + " ya está cargado y su nota es " + padronAlumnos.get(nombre));
                
                return false;
            
            }
        };

        /**
         * @param nombre nombra del alumno a obtener la nota
         * @return la nota. El valor de la clave @nombre en el hash map @padronAlumnos 
         * 
         */

        @Override
        public Integer obtenerCalificacion(String nombre) {

            boolean existe = this.existeEstudiante(nombre);
            Integer nota = 0;

            if (existe) {

                nota = padronAlumnos.get(nombre);
                return nota;
            
            } else {

                System.out.println("El alumno " + nombre  + " no está registrado");
                return null;
            }
            
        }


        /**
         * @return el mètodo devuelve el listado de alumnos con sus notas en su totalidad como un Hash_Map
         */
        @Override
        public Map<String, Integer> obtenerEstudiantesYCalificaciones(){

            return this.padronAlumnos;

        }


        /**
         * @param nombre El nombre del estudiante, que es la clave en el Hash Map, que debe ser chequeado como existente. 
         * @return true si existe, false si no.  
         *  */        
        @Override
         public boolean existeEstudiante(String nombre){

            if (!padronAlumnos.containsKey(nombre)){

                return false;
            } else {

                return true;
            }
     
        };

        /**
         * El método primero chequea si existe el nombre como clave. Si no existe devuelve falso. Si existe
         * Lo borra del Hash Map. Tras ello vuelve a chequear, si ya no lo encuentra devuelve true. Si no, false. 
         *  
         * @param nombre el nombre del alumno, que es clave, para ser eliminado de la lista. 
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

                    System.out.println("Borrado existoso");
                    return true;
                } else {
                    System.out.println("El Borrado no pudo realizarse.");
                    return false;
                }

            
            } else {

                System.out.println("El alumno no se encuentra registrado, operación no realizada.");

                return false;

            }
        };


        /**
         * 
         * Entiendo que el objetivo del método, pudiendo tomar los valores de nuevasCalificaciones. Independientemente 
         * de si existen. De existir previamente, entiendo, deberìa crearse un método donde solo se pasan las nuevas calificaciones. 
         * 
         * @param nuevosEstudiantes Lista de estudiantes nuevos a cargar. 
         * @param nuevasCalificaciones Lista con las nuevas calificaciones para cada estudiante. 
         * 
         */
        @Override
        public void agregarEstudiantes(Set<String> nuevosEstudiantes, Map<String, Integer> nuevasCalificaciones){
            
            if(nuevosEstudiantes.size()!=nuevasCalificaciones.size()) {

                System.out.println("Hay diferente cantidad de alumnos respecto de notas");
            
            } else {

                if (nuevosEstudiantes ==null || nuevasCalificaciones==null) {

                    System.out.println("Los Hash maps y/o sets pasados so nulos. No hay carga...");

                } else {

                    for (String alumno : nuevosEstudiantes) {

                        Integer nota = nuevasCalificaciones.get(alumno);

                        padronAlumnos.put(alumno,nota);

                        System.out.println(padronAlumnos.get(alumno));
                    }
                }
            }
        }

        /**
         * 
         * Método Sui Generis para listar en patalla todo el Hash Map a los efectos de darle una visualización.
         *  
         */
        public void imprimirAlumnosNotas(){
            
            System.out.println("Alumno" + "\t:\t" + "Nota");
            System.out.println("------------------------");
            for (Map.Entry<String, Integer> entry : padronAlumnos.entrySet()) {
                String nombre = entry.getKey();
                Integer nota = entry.getValue();
                System.out.println(nombre + "\t:\t" + nota);
            }
        }
}
