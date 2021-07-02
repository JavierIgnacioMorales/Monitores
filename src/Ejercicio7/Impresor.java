package Ejercicio7;

public class Impresor extends Thread {

    private final Monitor monitor;
    private final char termina;

    public Impresor (Monitor monitor,char fin){
        this.monitor = monitor;
        termina = fin;

    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
                char letra =monitor.extraerDeMemoria();
                if(termina == letra){
                    System.out.println(monitor.listaImpresor);
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
