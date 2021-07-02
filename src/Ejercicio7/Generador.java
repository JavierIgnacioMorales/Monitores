package Ejercicio7;

import java.util.Random;

public class Generador extends Thread{

    private final Random random = new Random();
    private final Monitor buffer;
    private final char termina;

    public Generador (Monitor monitor, char fin){
        buffer = monitor;
        termina = fin;
    }


    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
                char letra = (char) Math.floor(Math.random()*(122-97+1)+97);
                buffer.insertar(letra);
                if(termina == letra){
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
