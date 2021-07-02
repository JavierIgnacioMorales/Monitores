package Ejercicio7;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {

    private final Toolkit dimensiones = Toolkit.getDefaultToolkit();
    private final char[] buffer;
    private final char[][] memoria;
    private final int ancho_linea;
    public List<Character> listaImpresor;


    private int primero;
    private int ultimo;
    private int cuentaInsertar;

    private int memoriaPrimero;
    private int memoriaultimo;
    private int memoriacuenta;
    private int indice;
    private int memoriaInsertarLLena;
    private int memoriaExtraerLLena;

    private final Lock lock = new ReentrantLock();
    private final Lock memorialock = new ReentrantLock();

    private final Condition noLleno = lock.newCondition();
    private final Condition noVacio = lock.newCondition();
    private final Condition memoriaNoLleno = memorialock.newCondition();
    private final Condition memorianoVacio = memorialock.newCondition();
    private final Condition memoriaVisualizador = memorialock.newCondition();

    public Monitor(){
        super();
        this.ancho_linea = 10;//dimensiones.getScreenSize().width / 2;
        buffer = new char[ancho_linea];
        memoria = new char [ancho_linea][ancho_linea];
        listaImpresor = new ArrayList<>();
    }

    public void insertar(char dato) throws InterruptedException {
        lock.lock();
        try{
            while(cuentaInsertar==ancho_linea){
                noLleno.await();
            }
            buffer[ultimo]=dato;
            ultimo = (ultimo+1) % ancho_linea;
            cuentaInsertar++;
            noVacio.signal();
        }finally{
            lock.unlock();
        }
    }

    public char extraer() throws InterruptedException {
        lock.lock();
        try{
            while (cuentaInsertar==0){
                noVacio.await();
            }
            char resultado = buffer[primero];
            primero=(primero+1) % ancho_linea;
            cuentaInsertar--;
            noLleno.signal();
            return(resultado);
        }finally{
            lock.unlock();
        }
    }

    public void insertarEnMemoria(char dato) throws InterruptedException {
        memorialock.lock();
        try{
            while(memoriacuenta==ancho_linea){
                memoriaNoLleno.await();
            }
            memoria[indice][memoriaultimo]=dato;
            memoriaultimo = (memoriaultimo+1) % ancho_linea;
            memoriacuenta++;
            memoriaInsertarLLena++;
            if(memoriaInsertarLLena == ancho_linea){
                memoriaInsertarLLena = 0;
                memoriaVisualizador.await();
            }
            memorianoVacio.signal();
        }finally{
            memorialock.unlock();
        }
    }

    public char extraerDeMemoria() throws InterruptedException {
        memorialock.lock();
        try{
            while (memoriacuenta == 0){
                memorianoVacio.await();
            }
            char resultado = memoria[indice][memoriaPrimero];
            memoriaPrimero =(memoriaPrimero +1) % ancho_linea;
            memoriacuenta--;
            memoriaExtraerLLena ++;
            listaImpresor.add(resultado);
            if(memoriaExtraerLLena == ancho_linea){
                System.out.println(listaImpresor);
                listaImpresor.clear();
                indice++;
                memoriaVisualizador.signal();
                memoriaExtraerLLena = 0;
            }
            memoriaNoLleno.signal();
            return resultado;
        }finally{
            memorialock.unlock();
        }
    }
}
