package Ejercicio5;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import java.util.concurrent.

public class MonitorCuentaAhorros {

    private Lock lock = new ReentrantLock();
    private Condition condicion_Vacio = lock.newCondition();

    double saldo;

    public MonitorCuentaAhorros(){
        saldo = 0;
    }

    public double depositar(double cantidad){
        lock.lock();
        saldo += cantidad;
        condicion_Vacio.signalAll();
        lock.unlock();
        return saldo;
    }

    public synchronized double extraer(double cantidad) throws InterruptedException {
        lock.lock();
        try {
            while(saldo == 0 || cantidad > saldo){
                condicion_Vacio.await();
                //condicion_Vacio.;
            }
            saldo -= cantidad;
            return saldo;
        }finally {
            lock.unlock();
        }
    }
}
