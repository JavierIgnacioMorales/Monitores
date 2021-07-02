import random
import threading
import time
from threading import Thread


class consumidor(Thread):

    def __init__(self, buffer_monitor):
        super().__init__()
        self.buffer = buffer_monitor
        self.random = random

    def run(self):
        while True:
            try:
                valor = self.buffer.extraer()
                print(self.getName(), " - consumió el valor = ", valor)
                time.sleep(self.random.randint(0, 3))
            finally:
                pass
