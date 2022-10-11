public class Envejecimiento extends Thread {

    private RAM ram;

    public Envejecimiento(RAM ram) {
        this.ram = ram;
    }

    @Override
    public void run() {
        while (!CargaReferencias.termino) {
            ram.envejecimiento();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

                e.printStackTrace();
            } // Cada milisegundo

        }
    }
}
