import java.io.File;
import java.util.Scanner;

public class CargaReferencias extends Thread {

    /**
     * Nombre del archivo con las referencias que se van a cargar.
     */
    private String nombreArchivo;

    private TLB tlb;

    public CargaReferencias(String nombreArchivo, TLB tlb) {
        this.nombreArchivo = nombreArchivo;
        this.tlb = tlb;
    }

    @Override
    public void run() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src/ej_paginas/" + nombreArchivo));
            while (scanner.hasNextLine()) {
                // TODO: Agregar las funciones que realiza este Thread.

                Thread.sleep(2); // Debe correr cada 2 milisegundos.
            }
        } catch (Exception e) {
            System.err.println("No se encontró el archivo '" + nombreArchivo + "' en la carpeta 'ej_paginas'!");
        }

    }

}
