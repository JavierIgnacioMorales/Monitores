package Ejercicio4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class MonitorEntrada {

    private final Lock lock = new ReentrantLock();
    private final Condition condicion = lock.newCondition();
    int capacidad;

    public MonitorEntrada(){
        capacidad = 0;
    }

    public int entrada() throws InterruptedException{
        lock.lock();
        try {
            capacidad ++;
            while(capacidad % 3 != 0){
                condicion.await();
            }
            condicion.signalAll();
            return capacidad;
        }finally {
            lock.unlock();
        }
    }
}

class HiloParaEjecutar extends Thread{

    public HiloParaEjecutar(MonitorEntrada monitor){
        mimonitor = monitor;
        dato = 0;
    }

    @Override
    public void run() {
        while (true){
            try {
                dato = mimonitor.entrada();
                System.out.println(this.getName() + ": "+ dato);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final MonitorEntrada mimonitor;
    private int dato;
}

class Ejecucion{

    public static void main(String[] args){

        MonitorEntrada monitor = new MonitorEntrada();

        for(int h=0; h<3; h ++){
            Thread t = new HiloParaEjecutar(monitor);
            t.start();
        }

    }
}
