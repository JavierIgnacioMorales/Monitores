package Ejercicio5;

import java.util.Random;

public class PersonasQueExtraen extends Thread {

    private final MonitorCuentaAhorros monitorCuenta;
    private Random monto;

    public PersonasQueExtraen(MonitorCuentaAhorros monitor){
        super();
        monitorCuenta = monitor;
        monto = new Random();
    }

    @Override
    public void run() {
        while (true){
            try {
                double cantidad = Math.round(monto.nextDouble() * 1000);
                double saldo = monitorCuenta.extraer(cantidad);
                System.out.println(this.getName() + " Extrajo : " + cantidad);
                System.out.println("El saldo disponible en la cuenta es: "+ saldo);
                Thread.sleep(monto.nextInt(3000));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
