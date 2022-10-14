
public class RAM {

    /**
     * Arreglo que contiene la dirección virtual y la dirección física
     */
    private int[] marcosDirVirtual;

    /**
     * Arreglo que indica si un marco fue referenciado
     */
    private boolean[] fueReferenciado;

    /**
     * Arreglo que indica si un marco fue referenciado. Este se utiliza para saber
     * qué marco liberar.
     */
    private int[] referencias;

    /**
     * Constructor
     * 
     * @param numMarcos
     */
    public RAM(int numMarcos) {
        this.marcosDirVirtual = new int[numMarcos];
        this.fueReferenciado = new boolean[numMarcos];
        this.referencias = new int[numMarcos];

        for (int i = 0; i < marcosDirVirtual.length; i++) {
            marcosDirVirtual[i] = -1;
        }
    }

    public synchronized int agregarReferenciaVirtual(Integer referenciaVirtual) {

        int marcoDePaginaDisponible = this.ramEstaLlena();
        if (marcoDePaginaDisponible != -1) {
            marcosDirVirtual[marcoDePaginaDisponible] = referenciaVirtual;
            fueReferenciado[marcoDePaginaDisponible] = true;
            return marcoDePaginaDisponible;
        } else { // Si no hay marcos de página disponibles, se debe liberar uno
            int marcoDePagina = this.obtenerMarcoDePagina();
            marcosDirVirtual[marcoDePagina] = referenciaVirtual;
            fueReferenciado[marcoDePagina] = true;
            return marcoDePagina;
        }

    }

    private int obtenerMarcoDePagina() {
        int valorMasPequeno = Integer.MAX_VALUE;
        int marcoDePaginaRespectivo = -1;

        for (int i = 0; i < referencias.length; i++) {
            if (referencias[i] < valorMasPequeno) {
                valorMasPequeno = referencias[i];
                marcoDePaginaRespectivo = i;
            }
        }

        referencias[marcoDePaginaRespectivo] = 0;
        return marcoDePaginaRespectivo;
    }

    /**
     * Retorna el primer marco de página disponile en la RAM, -1 si no hay.
     * 
     * @return
     */
    private int ramEstaLlena() {
        for (int i = 0; i < marcosDirVirtual.length; i++) {
            if (marcosDirVirtual[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    public synchronized void envejecimiento() {

        for (int i = 0; i < fueReferenciado.length; i++) {
            if (fueReferenciado[i]) // Si fue referenciado, shift y agregas 1
            {
                referencias[i] = (referencias[i] << 1 ^ 1);
                fueReferenciado[i] = false;
            } else {
                referencias[i] = referencias[i] << 1 ^ 0;
            }
        }

    }

    /**
     * Indica que un marco ha sido referenciado
     * 
     * @param marco
     */
    public void fueReferenciado(Integer marco) {
        fueReferenciado[marco] = true;
    }
}
