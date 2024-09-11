package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {
        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: El Scanner reader queda inicializado
     */
    public static void inicializarGlobales() {
        reader = new Scanner(System.in);
    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
     */
    public static void menu() {
        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;
                default:
                    break;
            }
        } while (!salir);
    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {
        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];
    }

    /**
     * Descripcion: Este metodo solicita al usuario el precio y la cantidad de cada referencia de producto vendida
     * pre: Los arreglos precios y unidades deben estar inicializados
     * pos: Los arreglos precios y unidades quedan llenos con los datos ingresados por el usuario
     */
    public static void solicitarDatos() {
        for (int i = 0; i < precios.length; i++) {
            System.out.println("Ingrese el precio de la referencia " + (i + 1) + ":");
            precios[i] = reader.nextDouble();
            System.out.println("Ingrese la cantidad vendida de la referencia " + (i + 1) + ":");
            unidades[i] = reader.nextInt();
        }
    }

    /**
     * Descripcion: Este metodo calcula la cantidad total de unidades vendidas durante el día
     * pre: El arreglo unidades debe estar inicializado y lleno
     * pos: Retorna la suma total de unidades vendidas
     * @return int - La cantidad total de unidades vendidas
     */
    public static int calcularTotalUnidadesVendidas() {
        int total = 0;
        for (int cantidad : unidades) {
            total += cantidad;
        }
        return total;
    }

    /**
     * Descripcion: Este metodo calcula el precio promedio de las referencias de producto vendidas
     * pre: El arreglo precios debe estar inicializado y lleno
     * pos: Retorna el precio promedio de las referencias
     * @return double - El precio promedio de las referencias de producto
     */
    public static double calcularPrecioPromedio() {
        double suma = 0;
        for (double precio : precios) {
            suma += precio;
        }
        return suma / precios.length;
    }

    /**
     * Descripcion: Este metodo calcula las ventas totales (dinero recaudado) durante el día
     * pre: Los arreglos precios y unidades deben estar inicializados y llenos
     * pos: Retorna el total de ventas en dinero
     * @return double - El total de ventas en dinero
     */
    public static double calcularVentasTotales() {
        double total = 0;
        for (int i = 0; i < precios.length; i++) {
            total += precios[i] * unidades[i];
        }
        return total;
    }

    /**
     * Descripcion: Este metodo consulta el número de referencias de productos que en el día hayan superado un límite mínimo de ventas
     * pre: Los arreglos precios y unidades deben estar inicializados y llenos
     * pos: Retorna el número de referencias que superaron el límite
     * @param limite - El límite mínimo de ventas a considerar
     * @return int - El número de referencias que superaron el límite
     */
    public static int consultarReferenciasSobreLimite(double limite) {
        int count = 0;
        for (int i = 0; i < precios.length; i++) {
            if (precios[i] * unidades[i] > limite) {
                count++;
            }
        }
        return count;
    }
}