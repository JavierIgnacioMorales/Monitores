package Ejercicio7;

public class Visualizador extends Thread {

    private final Monitor buffer;
    private final char termina;

    public Visualizador (Monitor monitor,char fin){
        buffer = monitor;
        termina = fin;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
                char dato = buffer.extraer();
                buffer.insertarEnMemoria(dato);
                if(termina == dato){
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
