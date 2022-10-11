import java.util.LinkedList;
import java.util.Queue;

public class RAM {

    Queue<Integer> cola;

    private int[] marcos;
    private Tiempo tiempo;

    public RAM(int numMarcos, Tiempo tiempo) {
        this.marcos = new int[numMarcos];
        this.cola = new LinkedList<>();
        this.tiempo = tiempo;

        for (int i = 0; i < marcos.length; i++) {
            marcos[i] = -1;
        }
    }

    /**
     * Busca la referencia en la RAM.
     * 
     * @param referencia
     */
    public int buscarReferencia(Integer referencia) {
        /**
         * Si la referencia está en la RAM, se imprime el mensaje "Referencia encontrada
         * en la RAM" y se termina la ejecución del método.
         */
        //30ns
        for (int i = 0; i < marcos.length; i++) {
            if (marcos[i] == referencia) {
                System.out.println("Referencia " + referencia + " encontrada en la RAM");
                return i;
            }
        }

        /**
         * Si la referencia no está en la RAM, se imprime el mensaje "Referencia no
         * encontrada en la RAM" y se procede a buscarla en el disco.
         */
        return -1;
    }

    //10000000 ns
    public synchronized int agregarReferenciaVirtual(Integer referenciaVirtual) {
        tiempo.agregarTiempo(10000000); //TODORevisar
        System.out.println("Tiempo total (Resolución en RAM): " + tiempo.getTotal());
        while (cola.size() == marcos.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int marco = -1;
        for (int i = 0; i < marcos.length; i++) {
            if (marcos[i] == -1) {
                marcos[i] = referenciaVirtual;
                cola.add(i);
                marco = i;
                break;
            }
        }
        notify();

        return marco;

    }

    public synchronized void envejecimiento() {
        while (cola.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int referencia = cola.poll();
        marcos[referencia] = -1;
        notify();
    }
}
