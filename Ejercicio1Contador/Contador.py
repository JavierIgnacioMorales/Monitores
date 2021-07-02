from threading import Thread


class hilo_contador(Thread):

    def __init__(self, monitor):
        super().__init__()
        self.monitor = monitor

    def run(self):
        for i in range(1000000):
            self.monitor.incrementar()
