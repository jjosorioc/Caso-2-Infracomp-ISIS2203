import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el número de entradas de la TLB: ");
        int numEntradas = Integer.parseInt(sc.nextLine());
        System.out.println("Ingrese el número de marcos de la RAM: ");
        int numMarcos = Integer.parseInt(sc.nextLine());
        System.out.println(
                "Ingrese el nombre del archivo de las referencias (este se debe encontrar en la carpeta ej_paginas: ");
        String nombreArchivo = sc.nextLine();

        sc.close();

        /**
         * Crear los objetos
         */
        RAM ram = new RAM(numMarcos);
        TP tp = new TP(numMarcos, ram);
        TLB tlb = new TLB(numEntradas, tp);
        CargaReferencias cargaReferencias = new CargaReferencias(nombreArchivo, tlb);
        cargaReferencias.start();
    }
}