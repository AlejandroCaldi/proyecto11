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
    
            new UnsupportedOperationException();
            return false;
        };


        @Override
        public Integer obtenerCalificacion(String nombre) {

            new UnsupportedOperationException();
            return 5;
        }

        @Override
        public Map<String, Integer> obtenerEstudiantesYCalificaciones(){

            throw new UnsupportedOperationException("Method not implemented yet");

        }

        @Override
         public boolean existeEstudiante(String nombre){

            new UnsupportedOperationException();
            return false;
         }

        @Override
        public boolean eliminarEstudiante(String nombre) {

            return false;
        };

        @Override
        public void agregarEstudiantes(Set<String> nuevosEstudiantes, Map<String, Integer> nuevasCalificaciones){
            
            new UnsupportedOperationException();
            
        }
}
