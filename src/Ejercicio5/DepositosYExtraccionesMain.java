package Ejercicio5;


public class DepositosYExtraccionesMain {

    public static void main(String[] args){
        MonitorCuentaAhorros monitor = new MonitorCuentaAhorros();
        for(int i=0; i<3; i++){
            Thread te = new PersonasQueExtraen(monitor);
            te.start();
        }
        Thread td = new PersonasQueDepositan(monitor);
        td.start();
    }
}
