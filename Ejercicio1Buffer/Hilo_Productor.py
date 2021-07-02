import random
import threading
import time
from threading import Thread


class productor(Thread):

    def __init__(self, buffer_monitor):
        super().__init__()
        self.buffer = buffer_monitor
        self.random = random


    def run(self):
        while True:
            valor = self.random.randint(0, 100)
            try:
                self.buffer.insertar(valor)
                print(self.getName(), " - produjo el valor = ", valor)
                time.sleep(self.random.randint(0, 2))
            finally:
                pass
