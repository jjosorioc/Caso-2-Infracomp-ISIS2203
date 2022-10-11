public class Tiempo {

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
