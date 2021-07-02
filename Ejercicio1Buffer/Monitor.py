from threading import Lock, Condition
import numpy as np


class Buffer_Monitor:

    def __init__(self, capacidad):
        self.lock = Lock()
        self.no_lleno = Condition(self.lock)
        self.no_vacio = Condition(self.lock)
        self.capacidad = capacidad
        self.buffer = np.zeros(capacidad, int)
        self.primero = 0
        self.ultimo = 0
        self.cuenta = 0

    def insertar(self, dato):
        self.lock.acquire()
        try:
            while self.cuenta == self.capacidad:
                self.no_lleno.wait()

            self.buffer[self.ultimo] = dato
            self.ultimo = (self.ultimo+1) % self.capacidad
            self.cuenta += 1
            self.no_vacio.notifyAll()
        finally:
            self.lock.release()

    def extraer(self):
        self.lock.acquire()
        try:
            while self.cuenta == 0:
                self.no_vacio.wait()

            dato = self.buffer[self.primero]
            self.primero = (self.primero+1) % self.capacidad
            self.cuenta -= 1
            self.no_lleno.notifyAll()
            return dato
        finally:
            self.lock.release()
