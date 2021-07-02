package Ejercicio7;

public class GeneradorVisualizadorImpresorMain {

    public static void main(String[] args) {

        Monitor miMonitor = new Monitor();

        char paraTerminar = 'z';

        Generador generador = new Generador(miMonitor,paraTerminar);

        Visualizador visualizador = new Visualizador(miMonitor,paraTerminar);

        Impresor impresor = new Impresor(miMonitor,paraTerminar);

        generador.start();
        visualizador.start();
        impresor.start();

    }
}
