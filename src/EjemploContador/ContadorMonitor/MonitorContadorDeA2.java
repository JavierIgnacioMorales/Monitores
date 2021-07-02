package ContadorMonitor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorContadorDeA2 {
    private final Lock lock = new ReentrantLock();
    private final Semaphore semaforo = new Semaphore(2);

    public void incrementar() throws InterruptedException{
        //lock.lock();
        semaforo.acquire();
        try {
            i += 1;
        } finally {
            //lock.unlock();
            semaforo.release();
        }
    }

    public int getValor() {
        lock.lock();
        try {
            return i;
        } finally {
            lock.unlock();
        }
    }
    private static int i;
}

