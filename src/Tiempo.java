public class Tiempo {

    /**
     * Lo de los tiempos es:
     * Consulta TLB +2 ns
     * Consulta TP +30 ns
     * Si está en la RAM:
     * Carga RAM +30 ns
     * Si no está en la RAM:
     * Hay fallo:
     * Resuelve en la TP +30 ns
     * Busqueda en el disco +10000000 ns
     */

    private static long cargaDeDatos = 0;

    private static long traduccionDeDirecciones = 0;

    public static void sumarCargaDeDatos(int cantidadNS) {
        Tiempo.cargaDeDatos += cantidadNS;
    }

    public static void sumarTraduccionDeDirecciones(int cantidadNS) {
        Tiempo.traduccionDeDirecciones += cantidadNS;
    }

    public static long getCargaDeDatos() {
        return Tiempo.cargaDeDatos;
    }

    public static long getTraduccionDeDirecciones() {
        return Tiempo.traduccionDeDirecciones;
    }

}
