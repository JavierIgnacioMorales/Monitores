from Contador import hilo_contador
from Monitor import monitor


count_mon = monitor()

hilos = []

for i in range(5):
    r = hilo_contador(count_mon)
    hilos.append(r)
    r.start()
    print("Arranca hilo ", i)


for i in hilos:
    i.join()

print("Valor final = ", count_mon.retorna_valor())
