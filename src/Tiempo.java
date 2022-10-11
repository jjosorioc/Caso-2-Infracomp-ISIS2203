public class Tiempo {

    /**
     * Lo de los tiempos es:
        Consulta TLB +2 ns
        Consulta TP +30 ns
        Si está en la RAM:
            Carga RAM +30 ns
        Si no está en la RAM:
            Hay fallo:
                Resuelve en la TP +30 ns
                Busqueda en el disco +10000000 ns
        */

    private long total;

    public Tiempo(long total) {
        this.total = total;
    }

    //El tiempo que se agrega está en ns
    public void agregarTiempo(long tiempo) {
        total += tiempo;
    }

    //Getter y setter
    public long getTotal() {
        return total;
    }
    
}
