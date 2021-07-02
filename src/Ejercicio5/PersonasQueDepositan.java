package Ejercicio5;

import java.util.Random;

public class PersonasQueDepositan extends Thread {

    private final MonitorCuentaAhorros monitorCuenta;
    private Random monto;

    public PersonasQueDepositan(MonitorCuentaAhorros monitor){
        super();
        monitorCuenta = monitor;
        monto = new Random();
    }

    @Override
    public void run() {
        while (true){
            try {
                double cantidad = Math.round(monto.nextDouble() * 1000);
                double saldo = monitorCuenta.depositar(cantidad);
                System.out.println(this.getName() + " Deposito : " + cantidad);
                System.out.println("El saldo disponible en la cuenta es: "+ saldo);
                Thread.sleep(monto.nextInt(6000));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
