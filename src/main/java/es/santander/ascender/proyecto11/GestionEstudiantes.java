package es.santander.ascender.proyecto11;

import java.util.Map;
import java.util.Set;

public class GestionEstudiantes implements IGestionEstudiantes{

        /**
         * @param nombre
         * @param calificacion
         * @return
         */
        @Override
        public boolean agregarEstudiante(String nombre, int calificacion) {
    
            throw new UnsupportedOperationException("Método todavía no implementado. ");

        };


        @Override
        public Integer obtenerCalificacion(String nombre) {

            throw new UnsupportedOperationException("Método todavía no implementado. ");

        }

        @Override
        public Map<String, Integer> obtenerEstudiantesYCalificaciones(){

            throw new UnsupportedOperationException("Método todavía no implementado. ");

        }

        @Override
         public boolean existeEstudiante(String nombre){

            throw new UnsupportedOperationException("Método todavía no implementado. ");

         }

        @Override
        public boolean eliminarEstudiante(String nombre) {

            throw new UnsupportedOperationException("Método todavía no implementado. ");

        };

        @Override
        public void agregarEstudiantes(Set<String> nuevosEstudiantes, Map<String, Integer> nuevasCalificaciones){
            
            throw new UnsupportedOperationException("Método todavía no implementado. ");

            
        }
}
