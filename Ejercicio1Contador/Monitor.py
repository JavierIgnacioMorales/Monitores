from threading import RLock


class monitor:

    def __init__(self):
        self.lock = RLock()
        self.i = 0

    def incrementar(self):
        self.lock.acquire()
        try:
            self.i += 1
        finally:
            self.lock.release()

    def retorna_valor(self):
        self.lock.acquire()
        try:
            return self.i
        finally:
            self.lock.release()
