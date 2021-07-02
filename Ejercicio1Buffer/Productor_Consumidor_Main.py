from Monitor import Buffer_Monitor
from Hilo_Productor import productor
from Hilo_Consumidor import consumidor
from threading import Thread
import numpy as np


bufer_monitor = Buffer_Monitor(10)

hilos = np.zeros(10, Thread)

for t in range(5):
    tc = consumidor(bufer_monitor)
    tp = productor(bufer_monitor)
    tc.start()
    tp.start()

#for h in hilos):