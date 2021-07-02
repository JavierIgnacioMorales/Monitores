package ContadorMonitor;

public class HiloContador implements Runnable{

    public HiloContador(MonitorContadorDeA2 contadorMon){
        contador=contadorMon;
    }
    public void run(){
       // System.out.println("Aqui hilo");
        for(int i=0;i<1000000;i++){
            try {
                contador.incrementar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private final MonitorContadorDeA2 contador;
}
